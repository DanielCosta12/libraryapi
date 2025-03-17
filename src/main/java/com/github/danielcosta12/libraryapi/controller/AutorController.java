package com.github.danielcosta12.libraryapi.controller;

import com.github.danielcosta12.libraryapi.controller.dto.AutorDTO;
import com.github.danielcosta12.libraryapi.controller.dto.ErroResposta;
import com.github.danielcosta12.libraryapi.exceptions.RegistroDuplicadoException;
import com.github.danielcosta12.libraryapi.model.Autor;
import com.github.danielcosta12.libraryapi.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("autores")
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody AutorDTO autor) {
        try{
        Autor autorEntidade = autor.mapearParaAutor();
        service.salvar(autorEntidade);
        URI location =
                ServletUriComponentsBuilder.
                        fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(autorEntidade.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }catch (RegistroDuplicadoException e ){
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isPresent()){
            Autor autor = autorOptional.get();
            AutorDTO dto = new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isEmpty()){
            return ResponseEntity.notFound().build();

        }
        service.deletar(autorOptional.get());
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade) {

        List<AutorDTO> autores = service.pesquisar(nome, nacionalidade);
        return ResponseEntity.ok(autores);
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar( @PathVariable("id") String id, @RequestBody AutorDTO dto){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isEmpty()){
            return ResponseEntity.notFound().build();

        }
        var autor = autorOptional.get();
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        autor.setDataNascimento(dto.dataNascimento());
        service.atualizar(autor);
        return ResponseEntity.noContent().build();

    }
}

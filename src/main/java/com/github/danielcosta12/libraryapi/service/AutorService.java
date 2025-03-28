package com.github.danielcosta12.libraryapi.service;

import com.github.danielcosta12.libraryapi.controller.dto.AutorDTO;
import com.github.danielcosta12.libraryapi.model.Autor;
import com.github.danielcosta12.libraryapi.repository.AutorRepository;
import com.github.danielcosta12.libraryapi.validator.AutorValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AutorService {
    private final AutorRepository repository;
    private final AutorValidator validator;

    public AutorService(AutorRepository repository, AutorValidator validator){

        this.repository = repository;
        this.validator = validator;
    }
   public Autor salvar(Autor autor){
        validator.validar(autor);
        return repository.save(autor);
   }
    public void atualizar(Autor autor){
        if (autor.getId() == null){
            throw new IllegalArgumentException("É necessario que o autor já seja cadastrado");
        }
        validator.validar(autor);
      repository.save(autor);
    }
   public Optional<Autor> obterPorId(UUID id){
        return repository.findById(id);
   }
   public void deletar(Autor autor){
        repository.delete(autor);
   }
    public List<AutorDTO> pesquisar(String nome, String nacionalidade) {
        List<Autor> autores = repository.pesquisar(nome, nacionalidade);
        return autores.stream()
                .map(AutorDTO::fromEntity)
                .collect(Collectors.toList());
    }}
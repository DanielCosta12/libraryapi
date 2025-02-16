package com.github.danielcosta12.libraryapi.repository;

import com.github.danielcosta12.libraryapi.model.Autor;
import com.github.danielcosta12.libraryapi.model.GeneroLivro;
import com.github.danielcosta12.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;


    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Cam");
        autor.setNacionalidade("USA");
        autor.setDataNascimento(LocalDate.of(1999,11,23));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }
    @Test
    public void atualizarTest(){
        var id = UUID.fromString("373a01b0-699e-4509-bdf4-b15511cf5668");
       Optional<Autor> possivelAutor = repository.findById(id);
       if (possivelAutor.isPresent()){
           Autor autorEncontrado = possivelAutor.get();
           System.out.println("Dados do autor");
           System.out.println(possivelAutor.get());

           autorEncontrado.setNome("Aline");

           repository.save(autorEncontrado);


       }

        }
    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }
    @Test
    public void countAutores(){
        System.out.println("Contagem de Autores: " +repository.count());
    }
    @Test
    public void deletarPorIdTeste(){
        var id = UUID.fromString("373a01b0-699e-4509-bdf4-b15511cf5668");
        repository.deleteById(id);

    }
    @Test
    public void deletarPoreste(){
        var id = UUID.fromString("6f31b3a8-62ec-41c8-96e7-3fa62e43b4e2");
        var chama = repository.findById(id).get();
        repository.delete(chama);
    }
    @Test
    void salvarAutorComLivroTeste(){
        Autor autor = new Autor();
        autor.setNome("Luiz Negao");
        autor.setNacionalidade("Africano");
        autor.setDataNascimento(LocalDate.of(1987,02,07));


        Livro livro = new Livro();
        livro.setIsbn("9800-3020");
        livro.setPerco(BigDecimal.valueOf(420));
        livro.setGenero(GeneroLivro.AVENTURA);
        livro.setTitulo("NEGAO DA PIROCA 72CM");
        livro.setDataPublicacao(LocalDate.of(2001, 01, 07));
        livro.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);

        repository.save(autor);
        livroRepository.saveAll(autor.getLivros());;

    }
}

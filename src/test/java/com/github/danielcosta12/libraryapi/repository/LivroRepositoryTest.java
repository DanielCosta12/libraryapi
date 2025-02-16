package com.github.danielcosta12.libraryapi.repository;

import com.github.danielcosta12.libraryapi.model.Autor;
import com.github.danielcosta12.libraryapi.model.GeneroLivro;
import com.github.danielcosta12.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@SpringBootTest
class LivroRepositoryTest {
    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTeste(){
        Livro livro = new Livro();
        livro.setIsbn("8022-8922");
        livro.setPerco(BigDecimal.valueOf(230));
        livro.setGenero(GeneroLivro.HISTORIA);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 11, 23));

        Autor autor = autorRepository.findById(UUID.fromString("22c6e542-9486-47b3-a3ac-5515e198a9ef")).orElse(null);
        livro.setAutor(autor);

        repository.save(livro);
    }
    @Test
    void salvarCascadeTeste(){
        Livro livro = new Livro();
        livro.setIsbn("8022-8922");
        livro.setPerco(BigDecimal.valueOf(230));
        livro.setGenero(GeneroLivro.HISTORIA);
        livro.setTitulo("chama");
        livro.setDataPublicacao(LocalDate.of(1980, 11, 23));

        Autor autor = new Autor();
        autor.setNome("Chama");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1999,11,23));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void buscarLivrosTeste() {
        UUID id = UUID.fromString("cf78075b-717c-449f-bbcd-f13752513073");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());
        System.out.println("Autor");
        System.out.println(livro.getAutor().getNome());

    }
}
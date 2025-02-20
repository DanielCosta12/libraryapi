package com.github.danielcosta12.libraryapi.repository;

import com.github.danielcosta12.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
    @Query("SELECT a FROM Autor a WHERE " +
            "(:nome IS NULL OR a.nome LIKE %:nome%) AND " +
            "(:nacionalidade IS NULL OR a.nacionalidade LIKE %:nacionalidade%)")
    List<Autor> pesquisar(@Param("nome") String nome, @Param("nacionalidade") String nacionalidade);

}

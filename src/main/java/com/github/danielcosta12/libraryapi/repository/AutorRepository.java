package com.github.danielcosta12.libraryapi.repository;

import com.github.danielcosta12.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
}

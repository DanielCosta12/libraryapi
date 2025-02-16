package com.github.danielcosta12.libraryapi.repository;

import com.github.danielcosta12.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}

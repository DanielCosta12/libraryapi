package com.github.danielcosta12.libraryapi.service;

import com.github.danielcosta12.libraryapi.model.Autor;
import com.github.danielcosta12.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {
    private final AutorRepository repository;

    public AutorService(AutorRepository repository){
        this.repository = repository;
    }
   public Autor salvar(Autor autor){
        return repository.save(autor);
   }
   public Optional<Autor> obterPorId(UUID id){
        return repository.findById(id);
   }
}

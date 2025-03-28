package com.github.danielcosta12.libraryapi.validator;


import com.github.danielcosta12.libraryapi.exceptions.RegistroDuplicadoException;
import com.github.danielcosta12.libraryapi.model.Autor;
import com.github.danielcosta12.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {

    private AutorRepository repository;
    public AutorValidator(AutorRepository repository) {
        this.repository = repository;
    }
    public void validar(Autor autor){
        if (existeAutorCadastrado(autor)){
            throw new RegistroDuplicadoException("Autor já cadastrado!");
        }

    }

    private boolean existeAutorCadastrado(Autor autor){
        Optional<Autor> autorEncontrado = repository.findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade()
        );
        if (autor.getId() == null){
        return autorEncontrado.isPresent();
        }
        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();
    }
}

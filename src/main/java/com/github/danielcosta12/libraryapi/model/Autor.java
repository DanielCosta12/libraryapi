package com.github.danielcosta12.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Entity
@Data
@ToString
@Table(name = "autor", schema = "public")
public class Autor {
   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;

   @Column(name = "nome", length = 100, nullable = false)
   private String nome;

   @Column(name = "data_nascimento", nullable = false)
   private LocalDate dataNascimento;

   @Column(name = "nacionalidade", length = 50, nullable = false)
   private String nacionalidade;


   @OneToMany(mappedBy = "autor") //, cascade = CascadeType.ALL) //um autor para muitos livros
   //@Transient
   private List<Livro> livros;
}

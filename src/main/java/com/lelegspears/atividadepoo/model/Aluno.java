/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lelegspears.atividadepoo.model;

/**
 *
 * @author leleg
 */
public class Aluno {
    private Long id;
    private String nome;
    private Integer idade;
    private Curso curso;
    
    public Aluno(){}

    public Aluno(String nome, Integer idade, Curso curso) {
        this.nome = nome;
        this.idade = idade;
        this.curso = curso;
    }

    public Aluno(Long id, String nome, Integer idade, Curso curso) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}

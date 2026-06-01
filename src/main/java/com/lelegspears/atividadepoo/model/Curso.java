/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lelegspears.atividadepoo.model;

/**
 *
 * @author leleg
 */
public class Curso {
    private Long id;
    private String nome;
    private Double cargaHoraria;
    
    public Curso(){} // Construtor vazio para frameworks(caso adicione)
    
    public Curso(Long id, String nome, Double cargaHoraria){
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    public Curso(String nome, Double cargaHoraria) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
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

    public Double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
}

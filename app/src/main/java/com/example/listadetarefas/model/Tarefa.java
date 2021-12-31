package com.example.listadetarefas.model;

import java.io.Serializable;

public class Tarefa implements Serializable {
    private Long id;
    private String tarefa;

    public Tarefa() {
    }

    public Tarefa(Long id, String titulo) {
        this.id = id;
        this.tarefa = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }
}

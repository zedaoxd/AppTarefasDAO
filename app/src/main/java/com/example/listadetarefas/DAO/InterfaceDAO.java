package com.example.listadetarefas.DAO;

import java.util.List;

public interface InterfaceDAO<T> {

    public boolean salvar(T item);
    public boolean editar(T item);
    public boolean deletar(T item);
    public List<T> listar();
}

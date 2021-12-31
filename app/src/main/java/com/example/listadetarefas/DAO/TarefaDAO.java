package com.example.listadetarefas.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.listadetarefas.model.Tarefa;
import com.example.listadetarefas.utils.DataBase;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements InterfaceDAO<Tarefa> {

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public TarefaDAO(Context context) {
        DataBase db = new DataBase(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa item) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("nome", item.getTarefa());
            write.insert(DataBase.TABELA_TAREFAS, null, cv);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean editar(Tarefa item) {
        try {
            String[] args = {item.getId().toString()};
            ContentValues cv = new ContentValues();
            cv.put("nome", item.getTarefa());
            write.update(DataBase.TABELA_TAREFAS, cv, "id=?", args);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deletar(Tarefa item) {
        try {
            String[] args = {item.getId().toString()};
            write.delete(DataBase.TABELA_TAREFAS, "id=?", args);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Tarefa> listar() {
        List<Tarefa> tarefaList = new ArrayList<>();
        String sql = "SELECT * FROM " + DataBase.TABELA_TAREFAS + " ;";
        Cursor cursor = read.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Tarefa tarefa = new Tarefa();
            int indexId = cursor.getColumnIndex("id");
            int indexNome = cursor.getColumnIndex("nome");
            Long id = cursor.getLong(indexId);
            String nome = cursor.getString(indexNome);
            tarefa.setId(id);
            tarefa.setTarefa(nome);
            tarefaList.add(tarefa);
        }
        return tarefaList;
    }
}

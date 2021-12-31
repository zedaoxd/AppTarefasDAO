package com.example.listadetarefas.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String NOME_DB = "DB_TAREFAS";
    public static final String TABELA_TAREFAS = "tarefas";

    public DataBase(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL); ";

        try {
            sqLiteDatabase.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABELA_TAREFAS + ";";

        try {
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Álvaro Ferreira Alves
//******************************************************

package com.example.cpdbm03.projetofinal.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD extends SQLiteOpenHelper {

    public BD(Context context) {

        super(context, "banco", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        String sql = "CREATE TABLE Pessoas(" +
                "id_pessoa INTEGER PRIMARY KEY," +
                "nome TEXT," +
                "cpf TEXT," +
                "idade INTEGER," +
                "telefone TEXT," +
                "email TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Pessoas";
        db.execSQL(sql);

        onCreate(db);
    }
}

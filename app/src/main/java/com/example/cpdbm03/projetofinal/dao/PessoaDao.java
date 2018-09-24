//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Álvaro Ferreira Alves
//******************************************************

package com.example.cpdbm03.projetofinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.cpdbm03.projetofinal.modelo.Pessoa;

import java.util.ArrayList;
import java.util.List;


public class PessoaDao extends BD {


    public PessoaDao(Context context) {
        super(context);
    }

    public ContentValues insere(Pessoa pessoa) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDaViagem(pessoa);

        db.insert("Pessoas", null, dados);

        return dados;
    }

    public List<Pessoa> buscaPessoas() {
        String sql = "SELECT * FROM Pessoas";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Pessoa> pessoas = new ArrayList<>();

        while (c.moveToNext()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId_pessoa(c.getLong(c.getColumnIndex("id_pessoa")));
            pessoa.setNome(c.getString(c.getColumnIndex("nome")));
            pessoa.setCpf(c.getString(c.getColumnIndex("cpf")));
            pessoa.setIdade(Integer.parseInt(c.getString(c.getColumnIndex("idade"))));
            pessoa.setTelefone(c.getString(c.getColumnIndex("telefone")));
            pessoa.setEmail(c.getString(c.getColumnIndex("email")));


            pessoas.add(pessoa);
        }
        c.close();

        return pessoas;
    }


    @NonNull
    private ContentValues pegaDadosDaViagem(Pessoa pessoa) {
        ContentValues dados = new ContentValues();
        dados.put("nome", pessoa.getNome());
        dados.put("cpf", pessoa.getCpf());
        dados.put("idade", pessoa.getIdade());
        dados.put("telefone", pessoa.getTelefone());
        dados.put("email", pessoa.getEmail());

        return dados;
    }


}

package com.example.cpdbm03.projetofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.cpdbm03.projetofinal.dao.PessoaDao;
import com.example.cpdbm03.projetofinal.modelo.Pessoa;

import java.util.List;

public class ListaPessoaActivity extends AppCompatActivity {

    private ListView listaPessoas;
    public static long idemp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoa);


        listaPessoas = (ListView) findViewById(R.id.lista_pessoas);
        listaPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Pessoa pessoa = (Pessoa) listaPessoas.getItemAtPosition(position);

            }
        });

        Button novaEmpresa = (Button) findViewById(R.id.nova_pessoa);
        novaEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiProFormularioPessoa = new Intent(ListaPessoaActivity.this, FormularioPessoa.class);
                startActivity(vaiProFormularioPessoa);
            }
        });

        registerForContextMenu(listaPessoas);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        PessoaDao dao = new PessoaDao(this);
        List<Pessoa> pessoas = dao.buscaPessoas();
        dao.close();

        ArrayAdapter<Pessoa> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pessoas);
        listaPessoas.setAdapter(adapter);
    }


}

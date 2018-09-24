//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Álvaro Ferreira Alves
//******************************************************

package com.example.cpdbm03.projetofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cpdbm03.projetofinal.dao.PessoaDao;
import com.example.cpdbm03.projetofinal.modelo.Pessoa;


public class FormularioPessoa extends AppCompatActivity {


    private FormularioPessoaHelper helper;
    public Button btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_pessoa);


        helper = new FormularioPessoaHelper(this);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        Pessoa pessoa = (Pessoa) intent.getSerializableExtra("pessoa");
        if (pessoa != null) {
            helper.preencheFormularioPessoa(pessoa);
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltaListaPessoa = new Intent(FormularioPessoa.this, ListaPessoaActivity.class);
                startActivity(voltaListaPessoa);

                limparCampos();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Pessoa pessoa = helper.pegapPessoa();

                PessoaDao dao = new PessoaDao(this);
                if (pessoa.getId_pessoa() != null) {
                    // dao.alteraEmpresa(pessoa);
                    dao.close();

                    Toast.makeText(FormularioPessoa.this, "Pessoa salva!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                } else if (helper.campoNome.getText().toString().equals("") ||
                        helper.campoCpf.getText().toString().equals("") ||
                        helper.campoIdade.getText().toString().equals(null) ||
                        helper.campoTelefone.getText().toString().equals("") ||
                        helper.campoEmail.getText().toString().equals("")
                        ) {
                    helper.campoNome.setError("Campo obrigatório");
                    helper.campoCpf.setError("Campo obrigatório");
                    helper.campoIdade.setError("Campo obrigatório");
                    helper.campoTelefone.setError("Campo obrigatório");
                    helper.campoEmail.setError("Campo obrigatório");
                }else if(helper.campoCpf.getText().toString().length()!=14){
                    Toast.makeText(FormularioPessoa.this, "CPF Inválido",
                            Toast.LENGTH_SHORT).show();
                }else if(helper.campoTelefone.getText().toString().length()!=14){
                    Toast.makeText(FormularioPessoa.this, "Telefone Inválido",
                            Toast.LENGTH_SHORT).show();
                }


                else {
                    dao.insere(pessoa);
                    dao.close();

                    Toast.makeText(FormularioPessoa.this, "Pessoa salva!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                }

        }

        return super.onOptionsItemSelected(item);
    }

    public void limparCampos() {
                helper.campoCpf.setText("");
                helper.campoIdade.setText("");
                helper.campoTelefone.setText("");
                helper.campoEmail.setText("");
    }
}

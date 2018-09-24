//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Álvaro Ferreira Alves
//******************************************************

package com.example.cpdbm03.projetofinal;

import android.widget.EditText;

import com.example.cpdbm03.projetofinal.modelo.Pessoa;



public class FormularioPessoaHelper {

    public final EditText campoNome, campoCpf, campoIdade, campoTelefone, campoEmail;
    private Pessoa pessoa;


    public FormularioPessoaHelper(FormularioPessoa activity) {
        campoNome = (EditText) activity.findViewById(R.id.formulario_nomePessoa);
        campoCpf = (EditText) activity.findViewById(R.id.formulario_cpfPessoa);
        campoCpf.addTextChangedListener(Mask.insert("###.###.###-##", campoCpf));
        campoIdade = (EditText) activity.findViewById(R.id.formulario_idadePessoa);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefonePessoa);
        campoTelefone.addTextChangedListener(Mask.insert("(##)#####-####", campoTelefone));
        campoEmail = (EditText) activity.findViewById(R.id.formulario_emailPessoa);
        pessoa = new Pessoa();
    }


    public Pessoa pegapPessoa() {
        pessoa.setNome(campoNome.getText().toString());
        pessoa.setCpf(campoCpf.getText().toString());

        try {
            pessoa.setIdade(Integer.parseInt(campoIdade.getText().toString()));
        } catch (NumberFormatException e) {
            pessoa.setIdade(0);
        }

        pessoa.setTelefone(campoTelefone.getText().toString());
        pessoa.setEmail(campoEmail.getText().toString());
        return pessoa;
    }

    public void preencheFormularioPessoa(Pessoa pessoa) {
        campoNome.setText(pessoa.getNome());
        campoCpf.setText(pessoa.getCpf());
        campoIdade.setText(pessoa.getIdade());
        campoTelefone.setText(pessoa.getTelefone());
        campoEmail.setText(pessoa.getEmail());
        this.pessoa = pessoa;
    }


}

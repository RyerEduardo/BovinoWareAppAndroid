package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.text.TextUtilsCompat;


import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.conexao.Conexao;
import com.example.myapplication.model.Bovino;

public class CadastroBovinos extends AppCompatActivity implements View.OnClickListener{
    private EditText brinco, peso, raca, data, valor, nome, telefone;
    private Button cadastrar, fechar;
    String resultado="";
    Conexao con=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_bovinos);


        brinco = (EditText)findViewById(R.id.txtBrinco);
        peso = (EditText)findViewById(R.id.txtPeso);
        raca = (EditText)findViewById(R.id.txtRaca);
        data = (EditText)findViewById(R.id.txtData);
        valor = (EditText)findViewById(R.id.txtValor);
        nome = (EditText)findViewById(R.id.txtNome);
        telefone = (EditText)findViewById(R.id.txtTelefone);

        cadastrar = (Button) findViewById(R.id.btnCadastrar);
        fechar = (Button) findViewById(R.id.btnFechar);

        cadastrar.setOnClickListener(this);
        fechar.setOnClickListener(this);


    }
    public boolean validaCampos(){
        boolean res = false;

        String br = brinco.getText().toString();
        String pe = peso.getText().toString();
        String ra = raca.getText().toString();
        String da =  data.getText().toString();
        String va = valor.getText().toString();
        String no = nome.getText().toString();
        String te = telefone.getText().toString();

        if(res = estaVasio(br)){
           brinco.requestFocus();
        }
        else if(res = estaVasio(pe)){
            peso.requestFocus();
        }
        else if(res = estaVasio(ra)){
            raca.requestFocus();
        }
        else if(res = estaVasio(da)){
            data.requestFocus();
        }
        else if(res = estaVasio(va)){
            valor.requestFocus();
        }
        else if(res = estaVasio(no)){
            nome.requestFocus();
        }
        else if(res = estaVasio(te)){
            telefone.requestFocus();
        }

        if(res == true){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle(R.string.titulo_alerta);
            alerta.setMessage(R.string.mensagem_alerta);
            alerta.setNeutralButton("OK", null);
            alerta.show();
        }
        return res;
    }

    private boolean estaVasio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnCadastrar:
                boolean campoVasio = validaCampos();
                if(campoVasio == false) {
                   con = new Conexao(this);
                    Bovino b =  new Bovino();

                    b.setBrinco(brinco.getText().toString());
                    b.setPeso(Double.parseDouble(peso.getText().toString()));
                    b.setRaca(raca.getText().toString());
                    b.setData(data.getText().toString());
                    b.setValor(Double.parseDouble(valor.getText().toString()));
                    b.setNome(nome.getText().toString());
                    b.setTelefone(telefone.getText().toString());

                    resultado = con.create(b);
                    Toast.makeText(this,resultado,Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            case R.id.btnFechar:
                finish();
                break;
        }
    }




}

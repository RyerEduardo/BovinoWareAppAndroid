package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.myapplication.conexao.Conexao;
import com.example.myapplication.model.Bovino;

public class AlterarBovino extends AppCompatActivity implements View.OnClickListener {
    private EditText brinco, peso, raca, data, valor, nome, telefone;
    private Button alterar, voltar, excluir;
    private ListView lista;
    Conexao con = null;
    static int id = 0;
    String resultado = "";
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_bovino);



        brinco = (EditText) findViewById(R.id.txtBrinco);
        peso = (EditText) findViewById(R.id.txtPeso);
        raca = (EditText) findViewById(R.id.txtRaca);
        data = (EditText) findViewById(R.id.txtData);
        valor = (EditText) findViewById(R.id.txtValor);
        nome = (EditText) findViewById(R.id.txtNome);
        telefone = (EditText) findViewById(R.id.txtTelefone);
        alterar = (Button) findViewById(R.id.btnAlterar);
        excluir = (Button) findViewById(R.id.btnExcluir);
        voltar = (Button) findViewById(R.id.btnVoltar);

        Bundle b = getIntent().getExtras();
        if (b.containsKey("meuid")) {
            id = b.getInt("meuid");
        }

        con = new Conexao(this);
        Bovino bovino = con.exibir(id);

        brinco.setText(bovino.getBrinco());  //setando valores do banco nos campos
        peso.setText("" + bovino.getPeso());
        raca.setText(bovino.getRaca());
        data.setText(bovino.getData());
        valor.setText("" + bovino.getValor());
        nome.setText(bovino.getNome());
        telefone.setText(bovino.getTelefone());


        alterar.setOnClickListener(this);
        excluir.setOnClickListener(this);
        voltar.setOnClickListener(this);

    }

    public boolean validaCampos() {
        boolean res = false;

        String br = brinco.getText().toString();
        String pe = peso.getText().toString();
        String ra = raca.getText().toString();
        String da = data.getText().toString();
        String va = valor.getText().toString();
        String no = nome.getText().toString();
        String te = telefone.getText().toString();

        if (res = estaVasio(br)) {
            brinco.requestFocus();
        } else if (res = estaVasio(pe)) {
            peso.requestFocus();
        } else if (res = estaVasio(ra)) {
            raca.requestFocus();
        } else if (res = estaVasio(da)) {
            data.requestFocus();
        } else if (res = estaVasio(va)) {
            valor.requestFocus();
        } else if (res = estaVasio(no)) {
            nome.requestFocus();
        } else if (res = estaVasio(te)) {
            telefone.requestFocus();
        }

        if (res == true) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle(R.string.titulo_alerta);
            alerta.setMessage(R.string.mensagem_alerta);
            alerta.setNeutralButton("OK", null);
            alerta.show();
        }
        return res;
    }

    private boolean estaVasio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    Bovino b = new Bovino();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAlterar:
                boolean campoVasio = validaCampos();
                if (campoVasio == false) {
                    con = new Conexao(this);

                    b.setId(id);
                    b.setBrinco(brinco.getText().toString());
                    b.setPeso(Double.parseDouble(peso.getText().toString()));
                    b.setRaca(raca.getText().toString());
                    b.setData(data.getText().toString());
                    b.setValor(Double.parseDouble(valor.getText().toString()));
                    b.setNome(nome.getText().toString());
                    b.setTelefone(telefone.getText().toString());

                    resultado = con.update(b);
                    Toast.makeText(this, resultado, Toast.LENGTH_LONG).show();
                    lista = (ListView) findViewById(R.id.listaBovinos);
                    finish();
                }

                break;
            case R.id.btnExcluir:
                con = new Conexao(this);
                AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
                msgBox.setIcon(android.R.drawable.ic_menu_delete);
                msgBox.setTitle(getString(R.string.titulo_alerta_excluir));
                msgBox.setMessage(getString(R.string.mensagem_alerta_excluir));
                msgBox.setPositiveButton(getString(R.string.op_sim_excluir), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int x) {
                        resultado = con.delete(id);
                        Toast.makeText(AlterarBovino.this, resultado, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                msgBox.setNegativeButton(getString(R.string.op_nao_excluir), null);
                msgBox.show();
                break;
            case R.id.btnVoltar:
                finish();
                break;
        }
    }
}
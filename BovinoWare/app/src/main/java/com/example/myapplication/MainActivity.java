package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.conexao.Conexao;
import com.example.myapplication.model.Bovino;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView vendas;
    Conexao con = null;
    List<Bovino> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        vendas = (ListView)findViewById(R.id.listaBovinos);

        vendas.setOnItemClickListener(this);

        ImageButton fab = findViewById(R.id.btnNovaVenda);
        fab.setOnClickListener(this);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent it = new Intent(MainActivity.this, CadastroBovinos.class);
                startActivity(it);
            }
        });*/
    }

    public void cadastrar(View view){
        Intent it = new Intent(MainActivity.this, CadastroBovinos.class);
        startActivity(it);
    }

    @Override
    protected void onResume() {
        super.onResume();
        con = new Conexao(this);
        lista = con.getBovinos();

        ArrayAdapter<Bovino> adp = new ArrayAdapter<Bovino>(this,android.R.layout.simple_list_item_1,lista);
        vendas.setAdapter(adp);

    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this,CadastroBovinos.class);
        startActivity(it);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int posicao = (int)vendas.getItemIdAtPosition(position);
        int pegaid = lista.get(posicao).getId();
        Intent it = new Intent(this, AlterarBovino.class);
        it.putExtra("meuid",pegaid);
        startActivity(it);
    }
}

package com.example.myapplication.model;

public class Bovino {
    private int id;
    private String brinco;
    private double peso;
    private String raca;
    private String data;
    private double valor;
    private String nome;
    private String telefone;

    public Bovino() {
    }

    public Bovino(int id, String brinco, double peso, String raca, String data, double valor, String nome, String telefone) {
        this.id = id;
        this.brinco = brinco;
        this.peso = peso;
        this.raca = raca;
        this.data = data;
        this.valor = valor;
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  void setBrinco(String brinco){
        this.brinco = brinco;
    }

    public String getBrinco(){
        return brinco;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Brinco: "+brinco+"  Raça: "+raca+"  Data "+data;
    }
}

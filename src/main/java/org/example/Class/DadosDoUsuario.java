package org.example.Class;


public class DadosDoUsuario {

    // Dados que a API busca

    private String rua;
    private String localidade;
    private String estado;

    // Dados Preencidos pela API - ( get-set )

    public void setRua(String rua) {
        this.rua = rua;
    }
    public void setlocalidade(String localidade) {
        this.localidade = localidade;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRua() {
        return rua;
    }
    public String getlocalidade() {
        return localidade;
    }
    public String getEstado() {
        return estado;
    }

    // Dados que o usuario digita

    private String nome;
    private String senha;
    private String cep;

    // Dados pessoais - ( get-set )

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }
    public String getSenha() {
        return senha;
    }
    public  String getCep() {
        return cep;
    }
}

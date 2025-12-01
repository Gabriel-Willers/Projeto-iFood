package com.example;

public class DonoR extends User {
    private String local;
    private String nomeRest;
    private Restaurante restaurante; // associação

    public DonoR(){}

    public DonoR(String local, String nomeRest, String nome,String email, String senha){
        super(nome, email, senha);
        this.local = local;
        this.nomeRest = nomeRest;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public String toString() {
        return "DonoR [local=" + local + ", nomeRest=" + nomeRest + ", getNome()=" + getNome() + ", getEmail()="
                + getEmail() + ", getSenha()=" + getSenha() + "]";
    }
}

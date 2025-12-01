package com.example;
public abstract class User {
private String nome;
private String email;
private String senha;
private String local;
private String nomeRest;
public User(){}//talvez tirar
public User(String nome, String email, String senha){
    this.nome = nome;
    this.email = email;
    this.senha = senha;
}//talvez tirar
public void setNome(String nome){
    this.nome = nome;}
public String getNome(){
    return nome;}
public void setEmail(String email){
    this.email = email;}
public String getEmail(){
    return email;}
public void setSenha(String senha){
    this.senha = senha;
}
public String getSenha(){
    return senha;}
@Override
public String toString() {
    return "User [nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
}
public void setLocal(String local){
this.local = local;}
public String getLocal(){
    return local;}
public void setNomeRest(String nomeRest){
    this.nomeRest = nomeRest;}
public String getNomeRest(){
    return nomeRest;}




}
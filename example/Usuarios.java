package com.example;
import java.util.ArrayList;
import java.util.List;

public class Usuarios {

    private static final Usuarios INSTANCE = new Usuarios();
    public static Usuarios get() { return INSTANCE; }

    private final List<User> listaUsuarios = new ArrayList<>();

    private Usuarios() {}

    public static String normalizarEmail(String email) {
        if (email == null) return null;
        return email.trim().toLowerCase();
    }
    public void add(User user){
        listaUsuarios.add(user);
    }

    public boolean verificarConta(String email, String senha){
        for (User u : listaUsuarios){
            if (email.equals(u.getEmail()) && senha.equals(u.getSenha())){
                return true; 
            }
        }
        return false;
    }
    public boolean verificarPorEmail(String email){
        for (User u : listaUsuarios) {
            if (email.equals(u.getEmail())) {
                return true;
            }
        }
        return false;
    }
    public User buscarPorEmail(String email){
        for (User u : listaUsuarios) {
            if (email.equals(u.getEmail())) {
                return u;
            }
        }
        return null;
    }
}

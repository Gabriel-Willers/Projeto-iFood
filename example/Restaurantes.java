package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurantes {

    private static final Restaurantes INSTANCE = new Restaurantes();

    public static Restaurantes get() {
        return INSTANCE;
    }

    private final List<Restaurante> lista = new ArrayList<>();

    private Restaurantes() {}

    public void add(Restaurante r) {
        lista.add(r);
    }

    public List<Restaurante> listarTodos() {
        return Collections.unmodifiableList(lista);
    }
}

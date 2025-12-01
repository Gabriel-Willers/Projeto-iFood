package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedidos {

    private static final Pedidos INSTANCE = new Pedidos();
    public static Pedidos get() { return INSTANCE; }

    private final List<Pedido> lista = new ArrayList<>();

    private Pedidos() {}

    public void registrar(Pedido p) {
        lista.add(p);
    }

    public List<Pedido> listarTodos() {
        return Collections.unmodifiableList(lista);
    }
}

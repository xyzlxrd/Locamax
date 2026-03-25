package com.locamax.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho {

    private static Carrinho instance;

    private final List<Carro> itens = new ArrayList<>();

    private Carrinho() {}

    public static Carrinho getInstance() {
        if (instance == null) {
            instance = new Carrinho();
        }
        return instance;
    }

    public boolean adicionar(Carro carro) {
        if (itens.isEmpty()) {
            itens.add(carro);
            return true;
        }
        return false;
    }

    public List<Carro> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public boolean isEmpty() {
        return itens.isEmpty();
    }

    public void limpar() {
        itens.clear();
    }
}

package javafxtest;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private static Carrinho instance;
    private final List<CarroGetSet> lista = new ArrayList<>();

    private Carrinho() {}

    public static Carrinho getInstance() {
        if (instance == null) {
            instance = new Carrinho();
        }
        return instance;
    }

    public void adicionar(CarroGetSet carro) {
        lista.add(carro);
    }

    public List<CarroGetSet> getCarros() {
        return lista;
    }

    public void limpar() {
        lista.clear();
    }
}
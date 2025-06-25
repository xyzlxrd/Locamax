package javafxtest;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

public class Carrinho {
    private static Carrinho instance;
    private final List<CarroGetSet> lista = new ArrayList<>();
    
    public int add = 0;
    private Carrinho() {}

    public static Carrinho getInstance() {
        if (instance == null) {
            instance = new Carrinho();
        }
        return instance;
    }

    public void adicionar(CarroGetSet carro) {
        if(add == 0){
            lista.add(carro);
            add++;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro na compra!");
            alert.setHeaderText(null);
            alert.setContentText("Você só pode reservar um carro por vez!");
            alert.showAndWait();
        }
    }

    public List<CarroGetSet> getCarros() {
        return lista;
    }

    public void limpar() {
        lista.clear();
    }
}
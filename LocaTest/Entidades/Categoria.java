package Entidades;

public class Categoria {

    private int idCategoria;
    private String nomeCategoria;
    private double valorDiaria;

    public Categoria(String nomeCategoria, double valorDiaria) {
        this.nomeCategoria = nomeCategoria;
        this.valorDiaria = valorDiaria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
    public void setNomeCategoria(String novoNomeCategoria) {
        this.nomeCategoria = novoNomeCategoria;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }
    public void setValorDiaria(double novoValorDiaria) {
        this.valorDiaria = novoValorDiaria;
    }
}

package Entidades;

import java.time.Year;

public class Modelo {

    private int idModelo;
    private String nomeModelo;
    private String fabricante;
    private Year anoFabricacao;
    
    public Modelo(String nomeModelo, String fabricante, Year anoFabricacao) {
        this.nomeModelo = nomeModelo;
        this.fabricante = fabricante;
        this.anoFabricacao = anoFabricacao;
    }

    public Modelo() {} //Construtor sem Parâmetros (Criar objeto vazio e implementar os dados depois com setters)

    @Override
    public String toString() {
    return "ID: "+idModelo+" | Nome do Modelo: " + nomeModelo + " | Fabricante: "+fabricante+" | Ano de Fabricação: "+anoFabricacao;
    }

    public int getIdModelo() {
        return idModelo;
    }
    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getNomeModelo() {
        return nomeModelo;
    }
    public void setNomeModelo(String newModelo) {
        this.nomeModelo = newModelo;
    }
    
    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String newFabricante) {
        this.fabricante = newFabricante;
    }
    
    public Year getAnoFabricacao() {
        return anoFabricacao;
    }
    public void setAnoFabricacao(Year newAnoFabricacao) {
        this.anoFabricacao = newAnoFabricacao;
    }
}

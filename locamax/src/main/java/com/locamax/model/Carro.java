package com.locamax.model;

import java.time.LocalDate;

public class Carro {

    private int id;
    private int idCategoria;
    private int idModelo;
    private String nome;
    private String placa;
    private int quantidadeAssentos;
    private int quantidadePortas;
    private double quilometragem;
    private String tipoCombustivel;
    private String potenciaMotor;
    private String cambio;
    private double capacidadeTanque;
    private boolean arCondicionado;
    private boolean airbag;
    private String cor;
    private String disponibilidade;
    private double valorDiaria;
    private String descricao;
    private String nomeCategoria;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public int getIdModelo() { return idModelo; }
    public void setIdModelo(int idModelo) { this.idModelo = idModelo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public int getQuantidadeAssentos() { return quantidadeAssentos; }
    public void setQuantidadeAssentos(int quantidadeAssentos) { this.quantidadeAssentos = quantidadeAssentos; }

    public int getQuantidadePortas() { return quantidadePortas; }
    public void setQuantidadePortas(int quantidadePortas) { this.quantidadePortas = quantidadePortas; }

    public double getQuilometragem() { return quilometragem; }
    public void setQuilometragem(double quilometragem) { this.quilometragem = quilometragem; }

    public String getTipoCombustivel() { return tipoCombustivel; }
    public void setTipoCombustivel(String tipoCombustivel) { this.tipoCombustivel = tipoCombustivel; }

    public String getPotenciaMotor() { return potenciaMotor; }
    public void setPotenciaMotor(String potenciaMotor) { this.potenciaMotor = potenciaMotor; }

    public String getCambio() { return cambio; }
    public void setCambio(String cambio) { this.cambio = cambio; }

    public double getCapacidadeTanque() { return capacidadeTanque; }
    public void setCapacidadeTanque(double capacidadeTanque) { this.capacidadeTanque = capacidadeTanque; }

    public boolean isArCondicionado() { return arCondicionado; }
    public void setArCondicionado(boolean arCondicionado) { this.arCondicionado = arCondicionado; }

    public boolean isAirbag() { return airbag; }
    public void setAirbag(boolean airbag) { this.airbag = airbag; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public String getDisponibilidade() { return disponibilidade; }
    public void setDisponibilidade(String disponibilidade) { this.disponibilidade = disponibilidade; }

    public double getValorDiaria() { return valorDiaria; }
    public void setValorDiaria(double valorDiaria) { this.valorDiaria = valorDiaria; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getNomeCategoria() { return nomeCategoria; }
    public void setNomeCategoria(String nomeCategoria) { this.nomeCategoria = nomeCategoria; }

    public LocalDate getDataRetirada() { return dataRetirada; }
    public void setDataRetirada(LocalDate dataRetirada) { this.dataRetirada = dataRetirada; }

    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }
}

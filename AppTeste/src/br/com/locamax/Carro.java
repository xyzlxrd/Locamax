package br.com.locamax;

public class Carro {

    public enum TipoCombustivel {
        Gasolina("Gasolina"),
        Etanol("Etanol"),
        Diesel("Diesel"),
        Hibrido("Híbrido"),
        Flex("Flex"),
        Eletrico("Elétrico");

        private String descricao;

        TipoCombustivel(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }
    public enum PotenciaMotor {
        Potencia1_0("1.0"),
        Potencia1_3("1.3"),
        Potencia1_4("1.4"),
        Potencia1_5("1.5"),
        Potencia1_6("1.6"),
        Potencia1_8("1.8"),
        Potencia2_0("2.0"),
        Potencia2_0a2_9("2.0 - 2.9");

        private String descricao;

        PotenciaMotor(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }
    public enum Cambio {
        Manual("Manual"),
        Automatico("Automático"),
        CVT("CVT");

        private String descricao;

        Cambio(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }
    public enum Disponibilidade {
        Disponivel("Disponível"),
        Alugado("Alugado"),
        Manutencao("Em manutenção");

        private String descricao;

        Disponibilidade(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    private int idCarro;
    private int idCategoria;
    private int idModelo;

    private Categoria categoria;
    private Modelo modelo;

    private String nomeCarro;
    private String placa;
    private int qntAssentos;
    private int qntPortas;
    private int quilometragem;
    private TipoCombustivel tipoCombustivel;
    private PotenciaMotor potenciaMotor;
    private Cambio cambio;
    private int capacidadeTanque;
    private boolean arCondicionado;
    private boolean airbag;
    private String cor;
    private Disponibilidade disponibilidade;

    public Carro(Categoria categoria, Modelo modelo, String nomeCarro, String placa, int qntAssentos, int qntPortas, int quilometragem, TipoCombustivel tipoCombustivel,
    PotenciaMotor potenciaMotor, Cambio cambio, int capacidadeTanque, boolean arCondicionado, boolean airbag, String cor, Disponibilidade disponibilidade) {

        this.categoria = categoria;
        this.modelo = modelo;

        this.nomeCarro = nomeCarro;
        this.placa = placa;
        this.qntAssentos = qntAssentos;
        this.qntPortas = qntPortas;
        this.quilometragem = quilometragem;
        this.tipoCombustivel = tipoCombustivel;
        this.potenciaMotor = potenciaMotor;
        this.cambio = cambio;
        this.capacidadeTanque = capacidadeTanque;
        this.arCondicionado = arCondicionado;
        this.airbag = airbag;
        this.cor = cor;
        this.disponibilidade = disponibilidade;

    }

    //GETTERS & SETTERS DOS IDs
    public int getIdCarro() { return idCarro; }
    public void setIdCarro(int idCarro) { this.idCarro = idCarro; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public int getIdModelo() { return idModelo; }
    public void setIdModelo(int idModelo) { this.idModelo = idModelo; }


    //GETTERS & SETTERS DA CATEGORIA E MODELO
    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}

    public Modelo getModelo() {return modelo;}
    public void setModelo(Modelo modelo) {this.modelo = modelo;}


    //GETTERS & SETTERS DOS DEMAIS ATRIBUTOS DE CARRO
    public String getNomeCarro() { return nomeCarro;}
    public void setNomeCarro(String nomeCarro) { this.nomeCarro = nomeCarro;}
    
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public int getQntAssentos() { return qntAssentos; }
    public void setQntAssentos(int qntAssentos) { this.qntAssentos = qntAssentos; }

    public int getQntPortas() { return qntPortas; }
    public void setQntPortas(int qntPortas) { this.qntPortas = qntPortas; }

    public int getQuilometragem() { return quilometragem; }
    public void setQuilometragem(int quilometragem) { this.quilometragem = quilometragem; }

    public TipoCombustivel getTipoCombustivel() { return tipoCombustivel; }
    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) { this.tipoCombustivel = tipoCombustivel; }

    public PotenciaMotor getPotenciaMotor() { return potenciaMotor; }
    public void setPotenciaMotor(PotenciaMotor potenciaMotor) { this.potenciaMotor = potenciaMotor; }

    public Cambio getCambio() { return cambio; }
    public void setCambio(Cambio cambio) { this.cambio = cambio; }

    public int getCapacidadeTanque() { return capacidadeTanque; }
    public void setCapacidadeTanque(int capacidadeTanque) { this.capacidadeTanque = capacidadeTanque; }

    public boolean isArCondicionado() { return arCondicionado; }
    public void setArCondicionado(boolean arCondicionado) { this.arCondicionado = arCondicionado; }

    public boolean isAirbag() { return airbag; }
    public void setAirbag(boolean airbag) { this.airbag = airbag; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public Disponibilidade getDisponibilidade() { return disponibilidade; }
    public void setDisponibilidade(Disponibilidade disponibilidade) { this.disponibilidade = disponibilidade; }
}

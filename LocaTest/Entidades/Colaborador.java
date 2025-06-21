package Entidades;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

import Entidades.Carro.Cambio;
import Entidades.Carro.Disponibilidade;
import Entidades.Carro.PotenciaMotor;
import Entidades.Carro.TipoCombustivel;

public class Colaborador extends Pessoa {
        ArrayList<Categoria> categorias = new ArrayList<>();
        ArrayList<Modelo> modelos = new ArrayList<>();
        ArrayList<Carro> carros = new ArrayList<>();
    
    private int idColaborador;
    private int idPessoa;
    private String matricula;
    private String senha;
    
    public Colaborador(String cpf, String cnh, String nome, LocalDate dataNascimento, String telefone, String email,
    String sexo, Endereco endereco, String matricula, String senha) {
        
        super(cpf, cnh, nome, dataNascimento, telefone, email, endereco, sexo);
        this.matricula = matricula;
        this.senha = senha;   
    }

    //--- MÉTODOS PARA ACESSAR, ADICIONAR, E/OU REMOVER OS MODELOS DOS CARROS ABAIXO:

    public Modelo criarModelo(String nomeModelo, String fabricante, Year anoFabricacao) {
        Modelo novoModelo = new Modelo(nomeModelo, fabricante, anoFabricacao);

        System.out.println("Nome do Modelo criado(a): "+novoModelo.getNomeModelo());
        System.out.println("Fabricante do Modelo criado(a): "+novoModelo.getFabricante());
        System.out.println("Ano de Fabricação do Modelo criado(a): "+novoModelo.getAnoFabricacao());
        return novoModelo;
    }

    public void listaModelo() {
        if (modelos.isEmpty()) {
            System.out.println("Não há nenhum modelo registrado.");
        } else {
            System.out.println("\n--- Modelos Registrados ---");
            for (Modelo modelo : modelos) {
                System.out.println(modelo);
            }
            System.out.println("\n---------------------------");
        }
    }

    public void adicionarModelo(Modelo modelo) {
        if (modelo!=null) {
            modelos.add(modelo);
            System.out.println("Modelo Adicionado(a): "+modelo.getNomeModelo());
        } else {
            System.out.println("Não é possível adicionar um modelo nulo.");
        }
    }

    public boolean removerModelo(String nomeModelo) {
        for (int i=0; i<modelos.size(); i++) {
            if (modelos.get(i).getNomeModelo().equalsIgnoreCase(nomeModelo)) {
                modelos.remove(i);
                System.out.println("Modelo "+nomeModelo+" removido(a).");
                return true;
            }
        }
        System.out.println("Modelo "+nomeModelo+" não foi encontrado(a).");
        return false;
    }

    //--- MÉTODOS PARA ACESSAR, ADICIONAR, E/OU REMOVER AS CATEGORIAS DOS CARROS ABAIXO:

    public Categoria criarCategoria(String nomeCategoria, double valorDiaria) {
        Categoria novaCategoria = new Categoria(nomeCategoria, valorDiaria);

        System.out.println("Nome da Categoria criada: "+novaCategoria.getNomeCategoria());
        System.out.println("Valor da Diaria da Categoria criada: "+novaCategoria.getValorDiaria());
        return novaCategoria;
    }

    public void listaCategoria() {
        if (categorias.isEmpty()) {
            System.out.println("\nNão há nenhuma categoria registrada.");
        } else {
            System.out.println("\n--- Categorias Registradas ---");
            for (Categoria categoria : categorias) {
                System.out.println(categoria);
            }
            System.out.println("\n------------------------------");
        }
    }

    public void adicionarCategoria(Categoria categoria) {
        if (categoria!=null) {
            categorias.add(categoria);
            System.out.println("Categoria Adicionado(a): "+categoria.getNomeCategoria());
        } else {
            System.out.println("Não é possível adicionar uma categoria nula.");
        }
    }

    public boolean removerCategoria(String nomeCategoria) {
        for (int i=0; i<categorias.size(); i++) {
            if (categorias.get(i).getNomeCategoria().equalsIgnoreCase(nomeCategoria)) {
                categorias.remove(i);
                System.out.println("Categoria Removida: "+nomeCategoria);
                return true;
            }
        }
            System.out.println("Categoria "+nomeCategoria+" não foi encontrado(a).");
            return false;
    }

    public ArrayList<Categoria> getCategoria() {
        return new ArrayList<>(this.categorias);
    }

    //--- MÉTODOS PARA ACESSAR, ADICIONAR, E/OU REMOVER OS CARROS ABAIXO:

    public Carro criarCarro(Categoria categoria, Modelo modelo, String nomeCarro, String placa, int qntAssentos, int qntPortas, int quilometragem, TipoCombustivel tipoCombustivel,
    PotenciaMotor potenciaMotor, Cambio cambio, int capacidadeTanque, boolean arCondicionado, boolean airbag, String cor, Disponibilidade disponibilidade) {

        Carro novoCarro = new Carro(categoria, modelo, nomeCarro, placa, qntAssentos, qntPortas, quilometragem, tipoCombustivel, potenciaMotor, cambio, capacidadeTanque, arCondicionado, airbag, cor, disponibilidade);

        System.out.println("Modelo do Carro: "+novoCarro.getModelo());
        System.out.println("Categoria do Carro: "+novoCarro.getCategoria());

        System.out.println("Nome do Carro: "+novoCarro.getNomeCarro());
        System.out.println("Placa do Carro: "+novoCarro.getPlaca());
        System.out.println("Quantidade de Assentos do Carro: "+novoCarro.getQntAssentos());
        System.out.println("Quantidade de Portas do Carro: "+novoCarro.getQntPortas());
        System.out.println("Quilometragem do Carro: "+novoCarro.getQuilometragem());
        System.out.println("Tipo de Combustível do Carro: "+novoCarro.getTipoCombustivel());
        System.out.println("Potencia Motor do Carro: "+novoCarro.getPotenciaMotor());
        System.out.println("Cambio do Carro: "+novoCarro.getCambio());
        System.out.println("Capacidade de Combustível do Carro: "+novoCarro.getCapacidadeTanque());
        System.out.println("Ar Condicionado: "+novoCarro.getArCondicionado());
        System.out.println("Air Bag: "+novoCarro.getAirbag());
        System.out.println("Cor do Carro: "+novoCarro.getCor());
        System.out.println("Disponibilidade do Carro: "+novoCarro.getDisponibilidade());

        return novoCarro;
    }

    public void listaCarro() {
        if (carros.isEmpty()) {
            System.out.println("\nNão há nenhum carro registrada.");
        } else {
            System.out.println("\n--- Carros Registradas ---");
            for (Carro carro : carros) {
                System.out.println(carro);
            }
            System.out.println("\n------------------------------");
        }
    }

    public void adicionarCarro(Carro carro) {
        if (carro!=null) {
            carros.add(carro);
            System.out.println("Nome do Carro Adicionado(a): "+carro.getNomeCarro());
            System.out.println("Placa do Carro Adicionado(a): "+carro.getPlaca());
        } else {
            System.out.println("Não é possível adicionar um carro nulo.");
        }
    }

    public boolean removerCarro(String placaCarro) {
        for (int i=0; i<carros.size(); i++) {
            if (carros.get(i).getPlaca().equalsIgnoreCase(placaCarro)) {
                carros.remove(i);
                System.out.println("Carro Removido: "+placaCarro);
                return true;
            }
        }
            System.out.println("Carro de placa '"+placaCarro+"' não foi encontrado(a).");
            return false;
    }
    
    //--- GETTERS & SETTERS DA CLASSE ABAIXO:

    public int getIdColaborador() {
        return idColaborador;
    }
    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public int getIdPessoa() {
        return idPessoa;
    }
    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getMatricula () {
        return matricula;
    }
    public void setMatricula (String novaMatricula) {
        this.matricula = novaMatricula;
    }
    
    public String getSenha () {
        return senha;
    }
    public void setSenha (String novaSenha) {
        this.senha = novaSenha;
    }

}

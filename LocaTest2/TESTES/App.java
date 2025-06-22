package TESTES;

import java.time.Year;
import java.util.Scanner;

import DataAcessObject.DAOCliente;
import DataAcessObject.DAOColaborador;
import Entidades.Categoria;
import Entidades.Colaborador;
import Entidades.Modelo;

public class App {

        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

            String nomeUsuario = "00000000001";
            String senha = "12345";
        
        Colaborador colaboradorLogado = null;

        colaboradorLogado = DAOColaborador.efetuarLogin(nomeUsuario, senha);

        int respostaColaborador =0;

        while (respostaColaborador!=10) {
        System.out.println("--- COMANDOS DO COLABORADOR ---");
        System.out.println("1) VISUALIZAR CATEGORIAS REGISTRADAS");
        System.out.println("2) ADICIONAR NOVAS CATEGORIAS");
        System.out.println("3) REMOVER CATEGORIAS");
        System.out.println("");
        System.out.println("4) VISUALIZAR MODELOS REGISTRADAS");
        System.out.println("5) ADICIONAR NOVOS MODELOS");
        System.out.println("6) REMOVER MODELOS");
        System.out.println("");
        System.out.println("7) VISUALIZAR CARROS REGISTRADOS");
        System.out.println("8) ADICIONAR NOVOS CARROS");
        System.out.println("9) REMOVER CARROS");
        System.out.println("10) SAIR.");
        System.out.print("OPÇÃO: ");
        respostaColaborador = scanner.nextInt();
        scanner.nextLine();

        switch (respostaColaborador) {
            case 1:
                colaboradorLogado.listaCategoria();
                break;
            
            case 2:
                Categoria novaCategoria = new Categoria();
                System.out.print("NOME DA CATEGORIA: ");
                String nomeNovaCategoria = scanner.nextLine();
                System.out.print("VALOR DA DIÁRIA: ");
                double diariaNovaCategoria = scanner.nextDouble();

                novaCategoria.setNomeCategoria(nomeNovaCategoria);
                novaCategoria.setValorDiaria(diariaNovaCategoria);

                DAOColaborador.cadastrarCategoria(novaCategoria, colaboradorLogado);
                break;

            case 3:
                colaboradorLogado.listaCategoria();

                System.out.print("Digite o ID da Categoria a ser removido(a): ");
                int idCategoria = scanner.nextInt();

                DAOColaborador.removerCategoria(idCategoria);
                break;

            case 4:
                colaboradorLogado.listaModelo();
                break;

            case 5:
                Modelo novoModelo = new Modelo();
                System.out.print("NOME DO MODELO: ");
                String nomeNovoModelo = scanner.nextLine();

                System.out.print("FABRICANTE DO MODELO: ");
                String fabricanteNovoModelo = scanner.nextLine();

                System.out.print("ANO DE FABRICAÇÃO: ");
                String anoSTRNovoModelo = scanner.nextLine();

                Year anoFabricacaoNovoModelo = Year.parse(anoSTRNovoModelo);

                novoModelo.setNomeModelo(nomeNovoModelo);
                novoModelo.setFabricante(fabricanteNovoModelo);
                novoModelo.setAnoFabricacao(anoFabricacaoNovoModelo);

                DAOColaborador.cadastrarModelo(novoModelo, colaboradorLogado);
                break;

            case 6:
                colaboradorLogado.listaModelo();

                System.out.print("Digite o ID do modelo a ser removido(a): ");
                int idModeloRemovido = scanner.nextInt();

                DAOColaborador.removerModelo(idModeloRemovido);
                break;

            case 7:
                colaboradorLogado.listaCarro();
                break;
            
            case 8:

            System.out.println("teste");

            case 9:
                colaboradorLogado.listaCarro();

                System.out.print("Digite a placa do carro a ser removido: ");
                String placaCarroRemovido = scanner.nextLine();

                colaboradorLogado.removerCarro(placaCarroRemovido);
                break;

            case 10:
                System.out.println("\nEncerrando...");
                break;

            default:
                System.out.println("\nInválido(a).");
                break;
        }

        }
        scanner.close();
    }
}
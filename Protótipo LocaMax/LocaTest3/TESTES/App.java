package TESTES;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import DataAcessObject.DAOCategoria;
import DataAcessObject.DAOCliente;
import DataAcessObject.DAOColaborador;
import DataAcessObject.DAOEndereco;
import DataAcessObject.DAOModelo;
import DataAcessObject.DAOPessoa;
import Entidades.Carro;
import Entidades.Categoria;
import Entidades.Cliente;
import Entidades.Colaborador;
import Entidades.Endereco;
import Entidades.Modelo;
import Entidades.Pessoa;
import Entidades.Carro.Disponibilidade;

public class App {
    
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    Pessoa pessoa = new Pessoa();
    Endereco endereco = new Endereco();
    Cliente cliente = new Cliente();

    System.out.println("--- CADASTRO DE CLIENTE ---");
    System.out.print("DIGITE SEU NOME: ");
    String nome = scanner.nextLine();
    pessoa.setNome(nome);

    System.out.print("DIGITE SEU CPF: ");
    String cpf = scanner.nextLine();
    pessoa.setCpf(cpf);

    System.out.print("DIGITE SEU IDENTIFICADOR CNH: ");
    String cnh = scanner.nextLine();
    pessoa.setCnh(cnh);

    System.out.print("DIGITE SUA DATA DE NASCIMENTO (dd/MM/yyyy): ");
    String dataNascimento = scanner.nextLine();
    pessoa.setDataNascimento(dataNascimento);

    System.out.print("DIGITE SEU TELEFONE: ");
    String telefone = scanner.nextLine();
    pessoa.setTelefone(telefone);

    System.out.print("DIGITE SEU SEXO (M/F): ");
    String sexo = scanner.nextLine();
    pessoa.setSexo(sexo);

    System.out.println("");
    System.out.println("--- CREDENCIAIS DE ENDEREÇO ---");

    System.out.print("INFORME SEU CEP: ");
    String cep = scanner.nextLine();
    endereco.setCep(cep);

    System.out.print("INFORME SEU PAÍS: ");
    String pais = scanner.nextLine();
    endereco.setPais(pais);

    System.out.print("INFORME SEU ESTADO: ");
    String estado = scanner.nextLine();
    endereco.setEstado(estado);

    System.out.print("INFORME SUA CIDADE: ");
    String cidade = scanner.nextLine();
    endereco.setCidade(cidade);

    System.out.print("INFORME SEU BAIRRO: ");
    String bairro = scanner.nextLine();
    endereco.setBairro(bairro);

    System.out.print("INFORME SUA RUA: ");
    String rua = scanner.nextLine();
    endereco.setRua(rua);

    System.out.print("INFORME SEU NÚMERO: ");
    String numero = scanner.nextLine();
    endereco.setNumero(numero);

    endereco.setPessoa(pessoa);
    endereco.setIdPessoa(pessoa.getIdPessoa());

    System.out.println("");
    System.out.println("FINALIZAÇÃO DO CADASTRO");

    System.out.print("EMAIL: ");
    String email = scanner.nextLine();
    pessoa.setEmail(email);

    System.out.print("USUÁRIO: ");
    String nomeUsuario = scanner.nextLine();
    cliente.setUsuario(nomeUsuario);

    System.out.print("SENHA: ");
    String senha = scanner.nextLine();
    cliente.setSenha(senha);

    new DAOPessoa().cadastrarPessoa(pessoa);
    new DAOEndereco().cadastrarEndereco(endereco);

    cliente.setIdPessoa(pessoa.getIdPessoa());
    cliente.setNome(pessoa.getNome());
    cliente.setCpf(pessoa.getCpf());
    cliente.setCnh(pessoa.getCnh());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    cliente.setDataNascimento(pessoa.getDataNascimento().format(formatter));
    cliente.setTelefone(telefone);
    cliente.setSexo(sexo);
    cliente.setEndereco(endereco);

    new DAOCliente().cadastrarCliente(cliente);
    Cliente clienteLogado = null;

    System.out.println("");
    System.out.println("--- CADASTRO BEM SUCEDIDO ---");

    System.out.println("");
    System.out.print("LOGAR COMO COLABORADOR? (Y/N): ");
    String isColaborator = scanner.nextLine();
    Colaborador colaboradorLogado = null;

    if (isColaborator.equalsIgnoreCase("Y")) {

        while(colaboradorLogado == null) {
            System.out.print("MATRÍCULA: ");
            String matricula = scanner.nextLine();

            System.out.print("SENHA: ");
            String senhaColaborador = scanner.nextLine();

            colaboradorLogado = DAOColaborador.efetuarLogin(matricula, senhaColaborador);

            if (colaboradorLogado != null) {
                System.out.println("--- LOGIN EFETUADO COM SUCESSO ---");
                System.out.println("Bem-vindo(a): "+colaboradorLogado.getMatricula());
            } else {
                System.out.println("Matrícula e/ou Senha Incorretos. Tente novamente.");
            }
        }
    } else if (isColaborator.equalsIgnoreCase("N")) {

    while (clienteLogado ==null) {
    System.out.print("DIGITE SEU NOME DE USUÁRIO: ");
    nomeUsuario = scanner.nextLine();

    System.out.print("DIGITE SUA SENHA: ");
    senha = scanner.nextLine();

    clienteLogado = DAOCliente.efetuarLogin(nomeUsuario, senha);
    System.out.println("");
    
    if (clienteLogado != null) {
        System.out.println("--- LOGIN EFETUADO COM SUCESSO ---");
        System.out.println("Bem-vindo(a) "+clienteLogado.getUsuario());
    } else {
        System.out.println("Nome de Usuário e/ou Senha Incorretos. Tente novamente.");
        }
    }
        }

    if (isColaborator.equalsIgnoreCase("Y") && colaboradorLogado != null) {
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
                Carro novoCarro = new Carro();
                colaboradorLogado.listaCategoria();

                System.out.print("Digite o ID da Categoria: ");
                int idCarroCategoria = scanner.nextInt();

                Categoria categoriaSelecionada = DAOCategoria.buscarPorId(idCarroCategoria);
                if (categoriaSelecionada == null) {
                    System.out.println("Categoria não encontrada.");
                    break;
                } else {
                    novoCarro.setIdCategoria(idCarroCategoria);
                    novoCarro.setCategoria(categoriaSelecionada);
                }

                colaboradorLogado.listaModelo();
                System.out.print("Digite o ID do Modelo: ");
                int idCarroModelo = scanner.nextInt();
                scanner.nextLine();

                Modelo modeloSelecionado = DAOModelo.buscarPorId(idCarroModelo);
                if (modeloSelecionado == null) {
                    System.out.println("Modelo não encontrado.");
                    break;
                } else {
                    novoCarro.setIdModelo(idCarroModelo);
                    novoCarro.setModelo(modeloSelecionado);
                }

                System.out.print("NOME DO CARRO: ");
                novoCarro.setNomeCarro(scanner.nextLine());

                System.out.print("PLACA: ");
                novoCarro.setPlaca(scanner.nextLine());

                System.out.print("QUANTIDADE DE ASSENTOS: ");
                novoCarro.setQntAssentos(scanner.nextInt());

                System.out.print("QUANTIDADE DE PORTAS: ");
                novoCarro.setQntPortas(scanner.nextInt());

                System.out.print("QUILOMETRAGEM: ");
                novoCarro.setQuilometragem(scanner.nextInt());
                scanner.nextLine();

                System.out.print("TIPO DE COMBUSTÍVEL (Gasolina | Etanol | Diesel | Flex | Híbrido | Elétrico): ");
                String tipoCombustivelCarro = scanner.nextLine();
                novoCarro.setTipoCombustivel(DAOColaborador.converterTipoCombustivel(tipoCombustivelCarro));

                System.out.print("POTÊNCIA DO MOTOR (1.0 | 1.3 | 1.4 | 1.5 | 1.6 | 1.8 | 2.0 | 2.0 - 2.9): ");
                String potenciaCarro = scanner.nextLine();
                novoCarro.setPotenciaMotor(DAOColaborador.converterPotenciaMotor(potenciaCarro));

                System.out.print("CÂMBIO (Manual | Automático | CVT): ");
                String cambioCarro = scanner.nextLine();
                novoCarro.setCambio(DAOColaborador.converterCambio(cambioCarro));

                System.out.print("CAPACIDADE DO TANQUE: ");
                novoCarro.setCapacidadeTanque(scanner.nextInt());

                System.out.print("Possui ar-condicionado? (S/N): ");
                novoCarro.setArCondicionado(scanner.next().equalsIgnoreCase("S"));

                System.out.print("Possui airbag? (S/N): ");
                novoCarro.setAirbag(scanner.next().equalsIgnoreCase("S"));
                scanner.nextLine();

                System.out.print("COR DO CARRO: ");
                novoCarro.setCor(scanner.nextLine());

                System.out.print("DISPONIBILIDADE (Disponível | Alugado | Em manutenção): ");
                String carroDisponibilidade = scanner.nextLine();
                novoCarro.setDisponibilidade(DAOColaborador.converterDisponibilidade(carroDisponibilidade));

                DAOColaborador.cadastrarCarro(novoCarro, colaboradorLogado);

                System.out.println("Carro cadastrado com sucesso!");
                break;
            case 9:
                colaboradorLogado.listaCarro();
                System.out.print("Digite o ID do carro a ser removido: ");
                int idCarro = scanner.nextInt();
                scanner.nextLine();

                DAOColaborador.removerCarro(idCarro);
                break;

            case 10:
                System.out.println("Encerrando...");

            default:
                System.out.println("\nInválido(a).");
                break;
        }

        }

    }

    }
}

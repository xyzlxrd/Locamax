package LoginColaborador;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import DataAcessObject.DAOCliente;
import DataAcessObject.DAOColaborador;
import DataAcessObject.DAOEndereco;
import DataAcessObject.DAOPessoa;
import Entidades.Categoria;
import Entidades.Cliente;
import Entidades.Colaborador;
import Entidades.Endereco;
import Entidades.Modelo;
import Entidades.Pessoa;

public class LoginC { //Não está sendo utilizado, esqueci de estar removendo :D
    
public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    
    Pessoa pessoa = new Pessoa(null, null, null, null, null, null, null, null);
    Endereco endereco = new Endereco(null, null, null, null, null, null, null);
    Cliente cliente = new Cliente(null,null,null, null, null, null, null, null, null, null);
    Colaborador colaborador = new Colaborador(null, null, null, null, null, null, null, null, null, null);

    System.out.println("CADASTRO DE CLIENTE.");
    System.out.print("NOME: ");
    String nome = scanner.nextLine();
    pessoa.setNome(nome);

    System.out.print("CPF: ");
    String cpf = scanner.nextLine();
    pessoa.setCpf(cpf);

    System.out.print("CNH: ");
    String cnh = scanner.nextLine();
    pessoa.setCNH(cnh);

    System.out.print("DATA DE NASCIMENTO: ");
    String dataNascimento = scanner.nextLine();
    pessoa.setNascimento(dataNascimento);

    System.out.print("TELEFONE: ");
    String telefone = scanner.nextLine();
    pessoa.setTelefone(telefone);

    System.out.print("SEXO (M/F): ");
    String sexo = scanner.nextLine();
    pessoa.setSexo(sexo);

    System.out.println("");
    System.out.println("CREDENCIAIS DE ENDEREÇO.");

    System.out.print("CEP: ");
    String cep = scanner.nextLine();
    endereco.setCep(cep);

    System.out.print("PAÍS: ");
    String pais = scanner.nextLine();
    endereco.setPais(pais);

    System.out.print("ESTADO: ");
    String estado = scanner.nextLine();
    endereco.setEstado(estado);

    System.out.print("CIDADE: ");
    String cidade = scanner.nextLine();
    endereco.setCidade(cidade);

    System.out.print("BAIRRO: ");
    String bairro = scanner.nextLine();
    endereco.setBairro(bairro);

    System.out.print("RUA: ");
    String rua = scanner.nextLine();
    endereco.setRua(rua);

    System.out.print("NÚMERO: ");
    String numero = scanner.nextLine();
    endereco.setNumero(numero);

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
    endereco.setPessoa(pessoa);
    endereco.setIdPessoa(pessoa.getIdPessoa());
    new DAOEndereco().cadastrarEndereco(endereco);

    cliente.setIdPessoa(pessoa.getIdPessoa());
    cliente.setCpf(pessoa.getCpf());
    cliente.setCNH(pessoa.getCNH());
    cliente.setNome(pessoa.getNome());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    cliente.setNascimento(pessoa.getNascimento().format(formatter));

    cliente.setTelefone(pessoa.getTelefone());
    cliente.setEmail(pessoa.getEmail());
    cliente.setSexo(pessoa.getSexo());
    cliente.setEndereco(pessoa.getEndereco());

    DAOCliente.cadastrarCliente(cliente);
    Cliente clienteLogado = null;

    System.out.println("");
    System.out.println("CADASTRO BEM SUCEDIDO!");

    System.out.print("O CLIENTE É UM COLABORADOR? (Y/N): "); //Essa mensagem não apareceria, no caso está sendo usado para testes.
    String isColaborator = scanner.nextLine();
    Colaborador colaboradorLogado = null;

    if (isColaborator.equalsIgnoreCase("Y")) {

    colaborador.setMatricula(pessoa.getCpf());
    colaborador.setSenha(cliente.getSenha());

    colaborador.setIdPessoa(pessoa.getIdPessoa());
    colaborador.setCpf(pessoa.getCpf());
    colaborador.setCNH(pessoa.getCNH());
    colaborador.setNome(pessoa.getNome());

    DateTimeFormatter formatterStr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    colaborador.setNascimento(pessoa.getNascimento().format(formatterStr));

    colaborador.setTelefone(pessoa.getTelefone());
    colaborador.setEmail(pessoa.getEmail());
    colaborador.setSexo(pessoa.getSexo());
    colaborador.setEndereco(pessoa.getEndereco());

    DAOColaborador.cadastrarColaborador(colaborador);

        while(colaboradorLogado == null) {
            System.out.print("MATRÍCULA: ");
            String matricula = scanner.nextLine();

            System.out.print("SENHA: ");
            String senhaColaborador = scanner.nextLine();

            colaboradorLogado = DAOColaborador.efetuarLogin(matricula, senhaColaborador);

            if (colaboradorLogado != null) {
                System.out.println("LOGIN EFETUADO COM SUCESSO.");
                System.out.println("Bem-vindo(a): "+colaboradorLogado.getMatricula());
            } else {
                System.out.println("Matrícula e/ou Senha Incorretos. Tente novamente.");
            }
        }
    } else if (isColaborator.equalsIgnoreCase("N")) {

    while (clienteLogado ==null) {
    System.out.print("NOME DE USUÁRIO: ");
    nomeUsuario = scanner.nextLine();

    System.out.print("SENHA: ");
    senha = scanner.nextLine();

    clienteLogado = DAOCliente.efetuarLogin(nomeUsuario, senha);
    System.out.println("");
    
    if (clienteLogado != null) {
        System.out.println("LOGIN EFETUADO COM SUCESSO.");
        System.out.println("Bem-vindo(a) "+clienteLogado.getUsuario()+"!");
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

                System.out.print("Digite o nome da categoria a ser removida: ");
                String nomeCategoriaRemovida = scanner.nextLine();

                colaboradorLogado.removerCategoria(nomeCategoriaRemovida);
                break;

            case 4:
                colaborador.listaModelo();
                break;

            case 5:
                Modelo novoModelo = new Modelo();
                System.out.print("NOME DO MODELO: ");
                String nomeNovoModelo = scanner.nextLine();

                System.out.print("FABRICANTE DO MODELO: ");
                String fabricanteNovoModelo = scanner.nextLine();

                System.out.print("ANO DE FABRICAÇÃO: ");
                String anoFabricacaoNovoModelo = scanner.nextLine();

                novoModelo.setNomeModelo(nomeNovoModelo);
                novoModelo.setFabricante(fabricanteNovoModelo);
                novoModelo.setAnoFabricacao(anoFabricacaoNovoModelo);

                DAOColaborador.cadastrarModelo(novoModelo, colaboradorLogado);

            case 6:
                colaboradorLogado.listaModelo();

                System.out.print("Digite o nome do modelo a ser removida: ");
                String nomeModeloRemovido = scanner.nextLine();

                colaboradorLogado.removerModelo(nomeModeloRemovido);
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
                System.out.println("Encerrando...");

            default:
                System.out.println("Inválido(a).");
                break;
        }

        }
    }
    scanner.close();
    }
}

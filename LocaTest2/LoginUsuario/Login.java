package LoginUsuario;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import DataAcessObject.DAOCliente;
import DataAcessObject.DAOEndereco;
import DataAcessObject.DAOPessoa;
import Entidades.Cliente;
import Entidades.Endereco;
import Entidades.Pessoa;

public class Login {
    
public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    
    Pessoa pessoa = new Pessoa(null, null, null, null, null, null, null, null);
    Endereco endereco = new Endereco(null, null, null, null, null, null, null);
    Cliente cliente = new Cliente(null,null,null, null, null, null, null, null, null, null);

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

    new DAOPessoa().cadastrarPessoa(pessoa);
    endereco.setPessoa(pessoa);
    endereco.setIdPessoa(pessoa.getIdPessoa());

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

    new DAOEndereco().cadastrarEndereco(endereco);

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
    System.out.println("CADASTRO BEM SUCEDIDO! EFETUE O LOGIN.");

    while (clienteLogado ==null) {
    System.out.print("NOME DE USUÁRIO: ");
    nomeUsuario = scanner.nextLine();

    System.out.print("SENHA: ");
    senha = scanner.nextLine();

    clienteLogado = DAOCliente.efetuarLogin(nomeUsuario, senha);
    System.out.println("");
    
    if (clienteLogado != null) {
        System.out.println("Login Efetuado com Sucesso.");
        System.out.println("Bem-vindo(a) "+clienteLogado.getUsuario()+"!");
    } else {
        System.out.println("Nome de Usuário e/ou Senha Incorretos. Tente novamente.");
        }

    }
    scanner.close();
    }
}

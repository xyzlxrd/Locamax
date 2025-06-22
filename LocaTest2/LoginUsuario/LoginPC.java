package LoginUsuario;

import java.util.Scanner;

import DataAcessObject.DAOCliente;
import Entidades.Cliente;

public class LoginPC {

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Cliente clienteLogado = null;

    while (clienteLogado ==null) {
    System.out.print("NOME DE USUÁRIO: ");
    String nomeUsuario = scanner.nextLine();

    System.out.print("SENHA: ");
    String senha = scanner.nextLine();

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

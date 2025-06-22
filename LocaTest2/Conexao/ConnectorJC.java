package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorJC { //Criação da Conexão com o Banco de Dados
    
    private static final String url = "jdbc:mysql://localhost:3306/applocadora";
    private static final String user = "user";
    private static final String password = "locamax@projetouna";
    
    private static Connection conn;
    
    //Verificar se a conexão já foi instânciada
    public static Connection getConexao() {
        
        try { //Se a conexão for "null", ou seja, ainda não foi criada, ela será criada.
            if (conn == null) {
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            } else { //Caso contrário, se a conexão já haver sido criada então ele apenas a retorna.
            return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package br.com.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoDAO {
	public Connection conectaBD(){
		Connection conn = null;
		// TODO: handle exception	
		try {
			String URL = "jdbc:mysql//localhost:3606/locadora?user=root&passworld=Ru@Dezoit0";
			conn = DriverManager.getConnection(URL);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"ConexaoDAO:"+e.getMessage());
		}
		
		return conn;
	}
}

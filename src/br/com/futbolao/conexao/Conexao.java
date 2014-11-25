package br.com.futbolao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public static Connection connection;

	//m�todo de conex�o.
	public static Connection getConexao(int sistema) throws Exception {
		String conexao = "";
    	String usuario = "";
    	String senha = "";
    	
    	//condi��es para selecionar o tipo de banco.
    	if (sistema == DataBase.MYSQL) {
    		conexao = "jdbc:mysql://198.49.75.117:3306/weblinkt_futbolao";
    		usuario = "weblinkt_fut";
    		senha = "futbolao";
    	}else if (sistema == DataBase.SQLSERVER) {
    		conexao = "jdbc:sqlserver://localhost:1433;databaseName=aula08";
    		usuario = "root";
    		senha = "root";
    	}else if (sistema == DataBase.ORACLE) {
    		conexao = "jdbc:oracle:thin:@TOTVS11:1521:XE";
    		usuario = "system";
    		senha = "root";
    	}else if (sistema == DataBase.POSTGRESQL) {
    		conexao = "jdbc:postgresql://totvs11:5432/aula08";
    		usuario = "postgres";
    		senha = "root";
    	}else {
    		throw new IllegalArgumentException("Tipo de banco n�o suportado");
    	}
    	//se n�o houver uma conex�o, fa�o uma.
    	if (connection == null) {
            try {
            	connection = DriverManager.getConnection(conexao, usuario, senha);
	    	} catch(SQLException e) {
	    		throw new Exception("SQLException => ConnectionManager: " + e.getMessage());
	    	}
        }
    	return connection;
	}
	//met�do para fechar a conex�o.
	public static void close() throws Exception {
    	connection.close();
    }
}
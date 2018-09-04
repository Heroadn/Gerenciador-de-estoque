package Listas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Classes.DBUtils;

public class ListaConta extends ListaCliente{
	
	int id;
	int senha;
	String nome;
	
	/*Retorna ResultSet com todos os usuarios*/
	public ResultSet getUser(String nome, String senha) {
		//Inciando conexao com banco de dados
		Connection conexao = DBUtils.getConexao();
		
		//SQL a ser execultado
		String sql = "SELECT * FROM cliente where nome like '"+nome+"' and senha like '"+senha+"';";
		ResultSet rs = null;
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
		} catch (SQLException e1) {
			System.err.println("ListaCliente: "+e1.getMessage());
		}
		return rs;
}
}

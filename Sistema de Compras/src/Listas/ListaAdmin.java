package Listas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Classes.Coisa;
import Classes.DBUtils;

public class ListaAdmin {

	int id;
	int senha;
	String nome;
	
	public ResultSet getAdmins() {
		Connection conexao = DBUtils.getConexao();
		String sql = "SELECT * FROM admin";
		ResultSet rs = null;
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
		} catch (SQLException e1) {
			System.err.println("ListaAdmin: "+e1.getMessage());
		}
		return rs;
	}
	
}

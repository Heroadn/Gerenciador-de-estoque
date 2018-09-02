/*Classe Responsavel por conter a lista de Coisas*/
package Listas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Classes.Coisa;
import Classes.DBUtils;

public class ListaCoisas {

	//Adicionar coisas a lista
	public void addCoisa(Coisa e) {
		Connection conexao = DBUtils.getConexao();
		String sql = "insert into coisas " +
                "(nome, valor) " +
                "values (?,?)";
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, e.getNome());
			stmt.setString(2, String.valueOf(e.getValor()));
			stmt.execute();
		} catch (SQLException e1) {
			System.err.println("ListaCoisas: "+e1.getMessage());
		}
	}
	
	public ResultSet getCoisaSelect() {
		Connection conexao = DBUtils.getConexao();
		String sql = "SELECT * FROM coisas";
		ResultSet rs = null;
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
		} catch (SQLException e1) {
			System.err.println("ListaCoisas: "+e1.getMessage());
		}
		return rs;
	}
	
}

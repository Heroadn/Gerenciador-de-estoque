/*Classe Responsavel por conter a lista de pessoas*/
package Listas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Classes.Coisa;
import Classes.DBUtils;
import Classes.Pessoa;

public class ListaPessoas {

	//Adicionar pessoas na lista
	public void addPessoa(Pessoa e) {
		Connection conexao = DBUtils.getConexao();
		String sql = "insert into pessoas " +
                "(nome, idade) " +
                "values (?,?)";
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, e.getNome());
			stmt.setString(2, String.valueOf(e.getIdade()));
			stmt.execute();
			stmt.close();
		} catch (SQLException e1) {
			System.err.println("ListaPessoas: "+e1.getMessage());
		}
		
	}
	
	public ResultSet getPessoaSelect() {
		Connection conexao = DBUtils.getConexao();
		String sql = "SELECT * FROM pessoas ";
		ResultSet rs = null;
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
		} catch (SQLException e1) {
			System.err.println("ListaPessoas: "+e1.getMessage());
		}
		return rs;
	}
	
}

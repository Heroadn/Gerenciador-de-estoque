/*Classe Responsavel por conter a lista de Pessoas Cadastradas*/
package Listas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Classes.DBUtils;
import Classes.Pessoa;

public class ListaPessoa {

	//Adicionar pessoas na lista
	public void addPessoa(Pessoa e) {
		Connection conexao = DBUtils.getConexao();
		String sql = "insert into pessoa " +
                "(nome, idade) " +
                "values (?,?)";
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, e.getNome());
			stmt.setString(2, String.valueOf(e.getIdade()));
			stmt.execute();
			stmt.close();
		} catch (SQLException e1) {
			System.err.println("ListaPessoa: "+e1.getMessage());
		}
		
	}
	
	/*Retorna ResultSet com todos os usuarios*/
	public ResultSet getPessoaSelect() {
		//Inicia a conexao com o banco de dados
		Connection conexao = DBUtils.getConexao();
		//O SQL Desejado
		String sql = "SELECT * FROM pessoa ";
		ResultSet rs = null;
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
		} catch (SQLException e1) {
			System.err.println("ListaPessoa: "+e1.getMessage());
		}
		return rs;
	}
	
}

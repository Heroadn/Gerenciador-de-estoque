/*Classe Responsavel por conter a lista de Produtos Cadastrados*/
package Listas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Classes.Produto;
import Classes.DBUtils;

public class ListaProduto {

	//Adicionar coisas a lista
	public void addProduto(Produto e) {
		//Inicia a conexao com banco de dados
		Connection conexao = DBUtils.getConexao();
		String sql = "insert into produto " +
                "(nome, valor) " +
                "values (?,?)";
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, e.getNome());
			stmt.setString(2, String.valueOf(e.getValor()));
			stmt.execute();
		} catch (SQLException e1) {
			System.err.println("ListaProduto: "+e1.getMessage());
		}
	}
	
	/*Retorna ResultSet com todos os produtos*/
	public ResultSet getProdutoSelect() {
		//Inicializando conexao com banco de dados
		Connection conexao = DBUtils.getConexao();
		
		//Selecionar todos os produtos
		String sql = "SELECT * FROM produto";
		ResultSet rs = null;
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
		} catch (SQLException e1) {
			System.err.println("ListaProduto: "+e1.getMessage());
		}
		return rs;
	}
	
}

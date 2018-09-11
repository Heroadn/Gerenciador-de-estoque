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
	public static void addProduto(Produto e) {
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
	public static ResultSet getProdutoSelect() {
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
	
	/*Retorna ResultSet com todos os produtos comprados pelo cliente*/
	public static ResultSet getCompraCliente(String nome) {
		//Inicializando conexao com banco de dados
		Connection conexao = DBUtils.getConexao();
		
		//Selecionar todos os produtos
		String sql = "SELECT produto.* FROM `compra`,`produto`,`cliente` " + 
				"where compra.id_produto = produto.id and compra.id_cliente = cliente.id and cliente.nome like '"+nome+"';";
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

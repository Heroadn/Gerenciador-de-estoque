/*Classe Responsavel por conter a lista de Pessoas Cadastradas*/
package Listas;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.Session;

import Classes.DBUtils;
import Classes.Sessao;
import Classes.Cliente;

public class ListaCliente {
	
	public void comprarProduto(Sessao s, ListaCliente lc, int prod_id, double valor) throws SQLException {
		Connection conexao = DBUtils.getConexao();
		String sql = "insert into compra " +
                "(id_cliente, id_produto) " +
                "values (?,?)";
		
		String nome_cliente = s.getName();//Nome do Cliente
		int id_cliente = s.getId();//Id do Cliente
		int id_produto = prod_id;//Codigo do produto
		
		descontar(nome_cliente, id_cliente, valor);
		
		//Adicionao item a lista de compras do cliente
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, String.valueOf(id_cliente));
		stmt.setString(2, String.valueOf(id_produto));
		stmt.execute();
		stmt.close();
	}
	
	//Adicionar pessoas na lista
	public void addCliente(Cliente e) {
		Connection conexao = DBUtils.getConexao();
		String sql = "insert into cliente " +
                "(nome, idade, senha,tipo) " +
                "values (?,?,?,?)";
		
		try {
			
			String nome  = e.getNome();
			String senha = e.getSenha();
			String idade = String.valueOf(e.getIdade());
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, idade);
			stmt.setString(3,criptografar(senha));
			stmt.setString(4,"0");//0 para o tipo de conta ser usuario comun
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e1) {
			System.err.println("ListaCliente: "+e1.getMessage());
		}
		
	}
	
	//Criptografar a senha em SHA-2
	public String criptografar(String senha) {
		MessageDigest m = null;
		String cript    = null;
		
		try {
			//Criptografando a senha do Usuario
			m = MessageDigest.getInstance("SHA-256");
			m.update(senha.getBytes("UTF-8"), 0 , senha.length());
			cript = new BigInteger(1,m.digest()).toString(16);
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			System.out.println("ListaCliente: "+"Erro: "+e.getMessage());
			
		}
		
		return cript;
	}
	
	/*Verifica se o login pelo nome ja existe no banco de dados*/
	public boolean checkNomeRepetido(String nome) {
		
		//Inicia a conexao com o banco de dados
		Connection conexao = DBUtils.getConexao();
		String nome_bd = null;
		
		//Procurar pelo nome
		String sql ="SELECT * FROM cliente where nome like '"+nome+"';";
		ResultSet rs = null;
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			//peguar o nome no banco de dados
			if(rs.first()) {
				nome_bd = rs.getString("nome");
			}
			
		} catch (SQLException e1) {
			System.err.println("ListaCliente: checkNomeRepetido: "+e1.getMessage());
		}
		
		//Verificar se os nomes sao iguais
		if(nome.equalsIgnoreCase(nome_bd)) {
			return true;
		}else {
			return false;
		}
	}
	
	/*Retorna ResultSet com todos os usuarios*/
	public ResultSet getPessoaSelect() {
		//Inicia a conexao com o banco de dados
		Connection conexao = DBUtils.getConexao();
		//O SQL Desejado
		String sql = "SELECT * FROM cliente";
		ResultSet rs = null;
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
		} catch (SQLException e1) {
			System.err.println("ListaCliente: "+e1.getMessage());
		}
		return rs;
	}
	
	public double getSaldo(String nome, int id) throws SQLException {
		//Inciando conexao com banco de dados
		Connection conexao = DBUtils.getConexao();
		
		//SQL a ser execultado
		String sql = "SELECT saldo FROM cliente where nome like '"+nome+"' and id ='"+id+"';";
		ResultSet rs = null;
		double saldo;
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		rs = stmt.executeQuery();
		
		//Peguar saldo atual
		if(rs.first()) {
			saldo = Double.valueOf(rs.getString("saldo"));
			return saldo;
		}else {
			return 0;
		}
		
	}
	
	public void descontar(String nome, int id,double valor) throws SQLException {
		//Inciando conexao com banco de dados
		Connection conexao = DBUtils.getConexao();
		
		//Atualizar o saldo do usuario, descontando o valor da compra
		double operation = (getSaldo(nome, id) - valor);
		System.out.println("Cliente: "+nome+" Saldo:"+operation);
		String sql = "UPDATE cliente set saldo ='"+operation+"' where nome like'"+nome+"' and id='"+id+"';";
		int rs;

		PreparedStatement stmt = conexao.prepareStatement(sql);
		rs = stmt.executeUpdate();

	}
	
}

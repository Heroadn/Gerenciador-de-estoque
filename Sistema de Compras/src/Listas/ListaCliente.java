/*Classe Responsavel por conter a lista de Pessoas Cadastradas*/
package Listas;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Classes.DBUtils;
import Classes.Cliente;

public class ListaCliente {

	//Adicionar pessoas na lista
	public void addPessoa(Cliente e) {
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
	
	//Criptografar a senha em MD5
	public String criptografar(String senha) {
		MessageDigest m = null;
		String cript    = null;
		
		try {
			m = MessageDigest.getInstance("MD5");//Substituir para sha-2
			m.update(senha.getBytes(),0,senha.length());
			cript = new BigInteger(1,m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return cript;
	}
	
	/*Verifica se o login ja existe no banco de dados*/
	public void checkRepetido() {
		
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
	
}

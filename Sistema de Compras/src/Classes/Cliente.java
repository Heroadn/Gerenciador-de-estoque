package Classes;

import java.util.LinkedList;

public class Cliente {
	
	int cod;
	
	int idade;
	
	int tipo;
	
	String nome;
	
	String senha;
	
	LinkedList<Produto> lista;		

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LinkedList<Produto> getLista() {
		return lista;
	}

	public void setLista(LinkedList<Produto> lista) {
		this.lista = lista;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}

package Classes;

import java.util.LinkedList;

public class Pessoa {
	
	int cod;
	
	int idade;
	
	String nome;
	
	LinkedList<Coisa> lista;		

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

	public LinkedList<Coisa> getLista() {
		return lista;
	}

	public void setLista(LinkedList<Coisa> lista) {
		this.lista = lista;
	}

}

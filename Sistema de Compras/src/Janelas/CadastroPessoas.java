/*Classe Responsavel por cadastrar pessoas e mandar para a 
 *lista de pessoas na classe JMenu*/
package Janelas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Pessoa;
import Listas.ListaPessoas;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroPessoas extends JFrame {

	private JPanel contentPane;
	private JTextField nome_campo;
	private JTextField idade_campo;

	/*Funçao que recebe uma lista de pessoas do JMenu
	 compartilhando com o resto das janelas*/
	public CadastroPessoas(ListaPessoas lp) {
		setAlwaysOnTop(true);
		setTitle("Cadastro de Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 387, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setFocusableWindowState(true);
		
		//Campo de texto para o usuario inserir o nome
		nome_campo = new JTextField();
		nome_campo.setBounds(145, 42, 135, 20);
		contentPane.add(nome_campo);
		nome_campo.setColumns(10);
		
		//Campo de texto para o usuario inserir a idade
		idade_campo = new JTextField();
		idade_campo.setBounds(145, 85, 135, 20);
		contentPane.add(idade_campo);
		idade_campo.setColumns(10);
		
		//Label Nome
		JLabel nome = new JLabel("Nome");
		nome.setBounds(89, 45, 46, 14);
		contentPane.add(nome);
		
		//Label Idade
		JLabel idade = new JLabel("Idade");
		idade.setBounds(89, 88, 46, 14);
		contentPane.add(idade);
		
		//Botao responsavel por enviar as informaçoes do cadastro
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pessoa p = new Pessoa();
				p.setNome(nome_campo.getText());
				p.setIdade(Integer.parseInt(idade_campo.getText()));
				
				lp.addPessoa(p);
				//Limpando os campos para proxima inserçao
				nome_campo.setText("");
				idade_campo.setText("");
			}
		});
		btnEnviar.setBounds(60, 153, 118, 43);
		contentPane.add(btnEnviar);
		
		//Botao responsavel por dar um dispose na janela("fechar ela, sem fechar o programa junto")
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(188, 153, 118, 43);
		contentPane.add(btnSair);
	}
}

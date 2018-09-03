package Janelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Listas.ListaUser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nome_campo;
	private JTextField senha_campo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		//Botao responsavel pelo login e redirecionamento para a listagem de pessoas
		JButton btnNewButton = new JButton("Acessar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Metodo de login
				login(nome_campo.getText(),senha_campo.getText());
			}
		});
		btnNewButton.setBounds(86, 193, 235, 38);
		contentPane.add(btnNewButton);
		
		//JTextFild
		nome_campo = new JTextField();
		nome_campo.setBounds(166, 85, 155, 27);
		contentPane.add(nome_campo);
		nome_campo.setColumns(10);
		
		//JPasswordField para a Senha
		senha_campo = new JPasswordField();
		senha_campo.setBounds(166, 137, 155, 27);
		contentPane.add(senha_campo);
		//
		
		//Criação dos labels
		JLabel nome = new JLabel("Nome:");
		nome.setBounds(86, 88, 52, 20);
		contentPane.add(nome);
		
		JLabel senha = new JLabel("Senha:");
		senha.setBounds(86, 144, 52, 20);
		contentPane.add(senha);
		
		JLabel login = new JLabel("Login");
		login.setFont(new Font("Tahoma", Font.PLAIN, 30));
		login.setBounds(86, 24, 122, 37);
		contentPane.add(login);
	}
	
	//Metodo que confere as informaçoes e redicreciona a JMenu
	public void login(String nome,	String senha) {
		try {
			//Peguando os Usuarios do Banco de Dados
			ListaUser user = new ListaUser();
			
			//Fazendo Busca no banco de dados
			ResultSet rs = user.getUser(nome, senha);
			
			if(rs.first()){
				if(nome.equalsIgnoreCase(rs.getString("nome")) && senha.equalsIgnoreCase(rs.getString("senha")) ){
					//Chamada do JMenu que contem os botes que levam a ListagemPessoas e ListagemCoisas
					EventQueue.invokeLater(new Runnable() {	
						public void run() {
							try {
								JMenu frame = new JMenu();
								frame.setVisible(true);
								dispose();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}else {
					JOptionPane.showMessageDialog(null, "Por favor verifique as informaçoes digitadas.", "Erro:",JOptionPane.WARNING_MESSAGE);
				}
			}
			
		} catch (HeadlessException | SQLException e1) {
			//Se for encontrado um erro com o banco de dados
			System.out.println("Error: "+e1.getMessage());
		}
	}
	
}

package Janelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Sessao;
import Listas.ListaCliente;
import Listas.ListaConta;
import Listas.ListaProduto;

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
	
	private ListaCliente lista_cliente = new ListaCliente(); 
	private ListaProduto lista_produto = new ListaProduto();
	private Sessao session;
	
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

	//Construtor
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
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
		
		//Botao responsavel pelo login e redirecionamento para a listagem de pessoas
		JButton acessar = new JButton("Acessar");
		acessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Metodo de login
				logar(nome_campo.getText(), senha_campo.getText());
				
			}
		});
		acessar.setBounds(86, 193, 122, 38);
		contentPane.add(acessar);
		
		//Botão responsvel por registart usuarios
		JButton reegistrar = new JButton("Registrar");
		reegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Pegua como paramentros lista de Cliente
				CadastroCliente frame = new CadastroCliente(lista_cliente);
				frame.setVisible(true);
				
			}
		});
		reegistrar.setBounds(218, 193, 122, 38);
		contentPane.add(reegistrar);
	}
	
	//Chama o Menu de Admin
	public void callAdminMenu() {
		EventQueue.invokeLater(new Runnable() {	
			public void run() {
				try {
					JMenu frame = new JMenu(lista_cliente, lista_produto);
					frame.setVisible(true);
					
					//Fechar a tela mas manter o programa ativo
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Chama o Menu de Usuario
		public void callUserMenu() {
			EventQueue.invokeLater(new Runnable() {	
				public void run() {
					try {
						JCompra frame = new JCompra(lista_cliente ,lista_produto ,session);
						frame.setVisible(true);
						
						//Fechar a tela mas manter o programa ativo
						dispose();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	
	//Metodo que confere as informaçoes e redicreciona a JMenu se o login existir no DB
	public void logar(String nome,	String senha) {
		//Peguando os Usuarios do Banco de Dados
		ListaConta user = new ListaConta();
		
		try {
			//Fazendo Busca no banco de dados
			ResultSet rs = user.verificarLogin(nome, user.criptografar(senha));
			
			//Peguar o  primeiro resultado do ResultSet
			if(rs.first()){
				if(nome.equalsIgnoreCase(rs.getString("nome")) && user.criptografar(senha).equalsIgnoreCase(rs.getString("senha")) ){
					
					//Inicializando a sessao de usuario
					session = new Sessao();
					session.setName(rs.getString("nome"));
					session.setId(Integer.parseInt(rs.getString("id")));
					session.setIdade(Integer.parseInt(rs.getString("idade")));
					
					//Verificar o tipo da Conta
					if(rs.getString("tipo").equalsIgnoreCase("0"))
					{
						//Usuario Comun 
						callUserMenu();
						
					}else {
						//Usuario Admin
						callAdminMenu();
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Por favor verifique as informaçoes digitadas.", "Erro:",JOptionPane.WARNING_MESSAGE);
			}
			
		} catch (HeadlessException | SQLException e1) {
			//Se for encontrado um erro com o banco de dados
			System.out.println("Login: "+"Error: "+e1.getMessage());
		}
	}
}

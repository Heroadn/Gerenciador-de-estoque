package Janelas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Listas.ListaAdmin;
import Listas.ListaCoisas;
import Listas.ListaPessoas;

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

public class Login extends JFrame {

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
		ListaAdmin ad = new ListaAdmin();
		
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
				ResultSet rs = ad.getAdmins();
				
				try {
					while(rs.next()){
						//verifica as credencias, se elas baterem com a do banco , redirecione a listagem de pessoas
						if(nome_campo.getText().equalsIgnoreCase(rs.getString("nome")) && senha_campo.getText().equalsIgnoreCase(rs.getString("senha"))){
							dispose();
							
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										JMenu frame = new JMenu();
										frame.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							
						}else {
							//se as credencias nao baterem com a do banco de dados
							if(rs.isLast()) {
								JOptionPane.showMessageDialog(null, "Por favor verifique as informaçoes digitadas.", "Erro:",JOptionPane.WARNING_MESSAGE);
							}
						}
					}
				} catch (HeadlessException | SQLException e1) {
					System.out.println("Error: "+e1.getMessage());
				}
			}
		});
		btnNewButton.setBounds(86, 193, 235, 38);
		contentPane.add(btnNewButton);
		
		nome_campo = new JTextField();
		nome_campo.setBounds(166, 85, 155, 27);
		contentPane.add(nome_campo);
		nome_campo.setColumns(10);
		
		senha_campo = new JTextField();
		senha_campo.setColumns(10);
		senha_campo.setBounds(166, 141, 155, 27);
		contentPane.add(senha_campo);
		
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
}

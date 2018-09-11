package Janelas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Sessao;
import Listas.ListaProduto;
import Listas.ListaCliente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Font;

public class JMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public JMenu(ListaCliente lista_cliente, ListaProduto lista_produto, Sessao session) {
		//Criação do JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);//para o frame ficar no meio da tela
		
		//JButton que leva a ListagemPessoa
		JButton btnPessoas = new JButton("Clientes");
		btnPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListagemCliente frame = new ListagemCliente(lista_cliente);
				frame.setVisible(true);
			}
		});
		btnPessoas.setBounds(189, 62, 134, 39);
		contentPane.add(btnPessoas);
		
		//JButton que leva a ListagemProduto
		JButton btnCoisas = new JButton("Produto");
		btnCoisas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListagemProduto frame = new ListagemProduto(lista_produto, session);
				frame.setVisible(true);
			}
		});
		btnCoisas.setBounds(189, 162, 134, 39);
		contentPane.add(btnCoisas);
		
		JLabel lblAdmin = new JLabel(session.getName());
		lblAdmin.setBounds(66, 121, 62, 14);
		contentPane.add(lblAdmin);
		
		JLabel lblId = new JLabel(String.valueOf(session.getId()));
		lblId.setBounds(82, 62, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblIdade = new JLabel(String.valueOf(session.getIdade()));
		lblIdade.setBounds(82, 187, 46, 14);
		contentPane.add(lblIdade);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(10, 121, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblId_1 = new JLabel("ID:");
		lblId_1.setBounds(10, 62, 46, 14);
		contentPane.add(lblId_1);
		
		JLabel lblIdade_1 = new JLabel("Idade");
		lblIdade_1.setBounds(10, 187, 46, 14);
		contentPane.add(lblIdade_1);
		
		JLabel lblBenvindoAdministrador = new JLabel("Benvindo Administrador");
		lblBenvindoAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBenvindoAdministrador.setBounds(10, 24, 179, 14);
		contentPane.add(lblBenvindoAdministrador);
	}
}

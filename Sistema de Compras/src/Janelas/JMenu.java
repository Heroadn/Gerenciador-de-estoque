package Janelas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Listas.ListaCoisas;
import Listas.ListaPessoas;

import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JMenu extends JFrame {

	private JPanel contentPane;
	//Lista de Pessoas
	private ListaPessoas lista_pessoas = new ListaPessoas();
	
	//Lista de Coisas
	private ListaCoisas  lista_coisas =  new ListaCoisas();


	public JMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnPessoas = new JButton("Pessoas");
		btnPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListagemPessoa frame = new ListagemPessoa(lista_pessoas);
				frame.setVisible(true);
				dispose();
			}
		});
		btnPessoas.setBounds(170, 74, 91, 23);
		contentPane.add(btnPessoas);
		
		JButton btnCoisas = new JButton("Coisas");
		btnCoisas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListagemCoisa frame = new ListagemCoisa(lista_coisas);
				frame.setVisible(true);
				dispose();
			}
		});
		btnCoisas.setBounds(170, 144, 91, 23);
		contentPane.add(btnCoisas);
	}
}

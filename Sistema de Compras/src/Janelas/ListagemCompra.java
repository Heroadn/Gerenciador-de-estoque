package Janelas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Classes.Sessao;
import Listas.ListaProduto;
import java.awt.Toolkit;

public class ListagemCompra extends JFrame {

		//JTable
		private JTable table;
		private DefaultTableModel tm;
		private JButton btnDelete;
		
		private JPanel contentPane;
		
		//Construtor
		public ListagemCompra(ListaProduto lp, Sessao session) {
			setResizable(false);
			setTitle("Produtos: "+session.getName());
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 941, 472);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			setLocationRelativeTo(null);//para o frame ficar no meio da tela
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(35, 54, 861, 263);
			contentPane.add(scrollPane);
			
			//Inicialização do DefaultTableModel
			tm = new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Codigo", "Nome", "Valor"
					}
				);
			table = new JTable();
			table.setModel(tm);
			scrollPane.setViewportView(table);
			
			//Inserido o conteudo na JTable, ele pegua uma lista de produtos e uma sessao
			insertRow(lp, session);
	}

	//Insere na tabela o conteudo do banco de dados referente a Produtos
	public void insertRow(ListaProduto lp, Sessao session) {
		ResultSet rs = lp.getCompraCliente(session.getName());
		try {
			while(rs.next()){
				tm.addRow(new String[]{String.valueOf(rs.getString("id")),
			              rs.getString("nome"),String.valueOf(rs.getString("valor"))});
			}
		} catch (SQLException e1) {System.out.println("Erro: "+e1.getMessage());}
	}
}

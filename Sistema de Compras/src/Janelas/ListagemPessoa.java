package Janelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Listas.ListaCliente;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ListagemPessoa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTable table;
	private DefaultTableModel tm;
	private JButton btnDelete;

	public ListagemPessoa(ListaCliente lp) {
		setTitle("Pessoas");
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
			
			tm = new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Codigo", "Nome", "Idade"
					}
				);
			
			table = new JTable();
			table.setModel(tm);
			scrollPane.setViewportView(table);
			
			//Inserido o conteudo na JTable
			insertRow(lp);
			
			//Adiciona a pessoa no banco de dados
			JButton btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Pegua como paramentros lista de pessoas
					CadastroCliente frame = new CadastroCliente(lp);
					frame.setVisible(true);
				}
			});
			btnAdd.setBounds(36, 328, 89, 23);
			contentPane.add(btnAdd);
			
			btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				//Deleta a Row --Mas atenção nao deleta do banco de dados
				public void actionPerformed(ActionEvent e) {
					if (tm.getRowCount()>0) {
						tm.removeRow(table.getSelectedRow());
					}
				}
			});
			btnDelete.setBounds(152, 328, 89, 23);
			contentPane.add(btnDelete);
			
			//Volta para o JMenu
			JButton btnVoltar = new JButton("Voltar");
			btnVoltar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//Cria a janela JMenu
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
				}
			});
			btnVoltar.setBounds(272, 328, 89, 23);
			contentPane.add(btnVoltar);
	}
	
	//Insere na tabela o conteudo do banco de dados referente a Pessoas
	public void insertRow(ListaCliente lp) {
		ResultSet rs = lp.getPessoaSelect();
		try {
			while(rs.next()){
				tm.addRow(new String[]{String.valueOf(rs.getString("cod")),
			              rs.getString("nome"),String.valueOf(rs.getString("idade"))});
			}
		} catch (SQLException e1) {System.out.println("Erro: "+e1.getMessage());}
	}
}

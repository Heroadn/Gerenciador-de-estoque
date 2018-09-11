package Janelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class ListagemCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTable table;
	private DefaultTableModel tm;
	private JButton btnDelete;

	public ListagemCliente(ListaCliente lc) {
			setTitle("Cliente");
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
			insertRow(lc, tm);
			
			//Adiciona a pessoa no banco de dados
			JButton btnAdd = new JButton("Add");
			btnAdd.setIcon(new ImageIcon(ListagemCliente.class.getResource("/com/jtattoo/plaf/icons/medium/tree_collapsed_11x11.png")));
			ListagemCliente ls = this;
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Pegua como paramentros lista de Cliente
					CadastroCliente frame = new CadastroCliente(lc,ls);
					frame.setVisible(true);
				}
			});
			btnAdd.setBounds(36, 328, 89, 23);
			contentPane.add(btnAdd);
			
			//Deleta o registro
			btnDelete = new JButton("Delete");
			btnDelete.setIcon(new ImageIcon(ListagemCliente.class.getResource("/com/jtattoo/plaf/icons/medium/tree_expanded_11x11.png")));
			btnDelete.addActionListener(new ActionListener() {
				//Deleta --Mas atenção nao remove do banco de dados
				public void actionPerformed(ActionEvent e) {
					if (tm.getRowCount() > 0) {
						
						//Garantir que algo foi selecionadao
						if(table.getSelectedRow() > 0) {
							tm.removeRow(table.getSelectedRow());
						}else {
							JOptionPane.showMessageDialog(null, "Selecione Algo.");
						}
					}
				}
			});
			btnDelete.setBounds(152, 328, 89, 23);
			contentPane.add(btnDelete);
	}
	
	public DefaultTableModel getTm() {
		return this.tm;
	}
	
	//Insere na tabela o conteudo do banco de dados referente a Pessoas
	public void insertRow(ListaCliente lc, DefaultTableModel tm) {
		tm.setRowCount(0);
		ResultSet rs = lc.getPessoaSelect();
		try {
			while(rs.next()){
				tm.addRow(new String[]{String.valueOf(rs.getString("id")),
			              rs.getString("nome"),String.valueOf(rs.getString("idade"))});
			}
		} catch (SQLException e1) {System.out.println("Erro: "+e1.getMessage());}
	}
}

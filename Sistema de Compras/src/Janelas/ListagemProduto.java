package Janelas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Classes.Sessao;
import Listas.ListaProduto;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class ListagemProduto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//JTable
	private JTable table;
	private DefaultTableModel tm;
	private JButton btnDelete;
	
	//Construtor
	public ListagemProduto(ListaProduto lc, Sessao session) {
		setTitle("Produtos");
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
		
		//Inserido o conteudo na JTable
		insertRow(lc,tm);
		
		//Adiciona  a pessoa no banco de dados
		ListagemProduto lp = this;
		JButton btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon(ListagemProduto.class.getResource("/com/jtattoo/plaf/icons/medium/tree_collapsed_11x11.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//pegua como paramentros lista de produtos
				CadastroProduto frame = new CadastroProduto(lc, lp);
				frame.setVisible(true);
			}
		});
		btnAdd.setBounds(36, 328, 89, 23);
		contentPane.add(btnAdd);
		
		//Deleta o registro
		btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(ListagemProduto.class.getResource("/com/jtattoo/plaf/icons/medium/tree_expanded_11x11.png")));
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

//Insere na tabela o conteudo do banco de dados referente a Produtos
public void insertRow(ListaProduto lc, DefaultTableModel tm) {
	tm.setRowCount(0);
	ResultSet rs = lc.getProdutoSelect();
	try {
		while(rs.next()){
			tm.addRow(new String[]{String.valueOf(rs.getString("id")),
		              rs.getString("nome"),String.valueOf(rs.getString("valor"))});
		}
	} catch (SQLException e1) {System.out.println("Erro: "+e1.getMessage());}
}

}

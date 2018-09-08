package Janelas;

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

import Listas.ListaProduto;

public class ListagemProduto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//JTable
	private JTable table;
	private DefaultTableModel tm;
	private JButton btnDelete;
	
	//Construtor
	public ListagemProduto(ListaProduto lc) {
		setTitle("Coisas");
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
		insertRow(lc);
		
		//Adiciona  a pessoa no banco de dados
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//pegua como paramentros lista de produtos
				CadastroProduto frame = new CadastroProduto(lc);
				frame.setVisible(true);
				dispose();
			}
		});
		btnAdd.setBounds(36, 328, 89, 23);
		contentPane.add(btnAdd);
		
		//Deleta o registro
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			//Deleta --Mas atenção nao remove do banco de dados
			public void actionPerformed(ActionEvent e) {
				if (tm.getRowCount()>0) {
					tm.removeRow(table.getSelectedRow());
				}
			}
		});
		btnDelete.setBounds(152, 328, 89, 23);
		contentPane.add(btnDelete);
}

//Insere na tabela o conteudo do banco de dados referente a Produtos
public void insertRow(ListaProduto lc) {
	ResultSet rs = lc.getProdutoSelect();
	try {
		while(rs.next()){
			tm.addRow(new String[]{String.valueOf(rs.getString("id")),
		              rs.getString("nome"),String.valueOf(rs.getString("valor"))});
		}
	} catch (SQLException e1) {System.out.println("Erro: "+e1.getMessage());}
}

}

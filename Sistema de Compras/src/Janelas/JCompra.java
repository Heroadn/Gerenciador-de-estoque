package Janelas;

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
import Listas.ListaCliente;
import Listas.ListaProduto;

public class JCompra extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	//JTable
	private JTable table;
	private DefaultTableModel tm;
	private JButton btnDelete;
	

	//Construtor
		public JCompra(ListaCliente lc ,ListaProduto lp ,Sessao session) {
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
			insertRow(lp);
			
			//Adiciona  a pessoa no banco de dados
			JButton btnAdd = new JButton("Comprar");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					if(session.getIdade() > 18) {
						
						//Seleciona o item na tabela e adiciona a lista de compras do usuario
						String id_prod = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
						String valor    = String.valueOf(table.getValueAt(table.getSelectedRow(), 2));
						
						try {
							lc.comprarProduto(session ,lc , Integer.parseInt(id_prod), Double.parseDouble(valor));
							JOptionPane.showMessageDialog(null, "Compra relizada com sucesso");
						} catch (NumberFormatException | SQLException e1) {
							e1.printStackTrace();
						}
							
					}else {
						JOptionPane.showMessageDialog(null, "Idade requerida para compra: mais de 18 anos");
					}
					
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
	public void insertRow(ListaProduto lp) {
		ResultSet rs = lp.getProdutoSelect();
		try {
			while(rs.next()){
				tm.addRow(new String[]{String.valueOf(rs.getString("id")),
			              rs.getString("nome"),String.valueOf(rs.getString("valor"))});
			}
		} catch (SQLException e1) {System.out.println("Erro: "+e1.getMessage());}
	}

}

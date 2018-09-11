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
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

public class JCompra extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	//JTable
	private JTable table;
	private DefaultTableModel tm;
	

	//Construtor
		public JCompra(ListaCliente lc ,ListaProduto lp ,Sessao session) {
			setResizable(false);
			setTitle("Compras");
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
			
			//Lista de Compras
			JButton btnListaDeCompras = new JButton("Lista de Compras");
			btnListaDeCompras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//janela que mostra a compra dos clientes
					ListagemCompra compra = new ListagemCompra(lp, session);
					compra.setVisible(true);
				}
			});
			btnListaDeCompras.setBounds(135, 328, 117, 23);
			contentPane.add(btnListaDeCompras);
			
			JLabel lblNewLabel = new JLabel("Produtos Disponiveis");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setBounds(35, 20, 185, 23);
			contentPane.add(lblNewLabel);
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

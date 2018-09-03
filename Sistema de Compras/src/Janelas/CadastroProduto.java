/*Classe Responsavel por cadastrar produtos e mandar para a 
 *lista de pessoas na classe JMenu*/
package Janelas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Produtos;
import Listas.ListaProduto;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroProduto extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=-18,549
	 */
	private final JTextField textField = new JTextField();
	private JTextField nome_campo;
	private JTextField valor_campo;
	private JButton btnEnviar;

	public CadastroProduto(ListaProduto lc) {
		textField.setColumns(10);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 387, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		nome_campo = new JTextField();
		nome_campo.setBounds(145, 42, 135, 20);
		contentPane.add(nome_campo);
		nome_campo.setColumns(10);
		
		valor_campo = new JTextField();
		valor_campo.setBounds(145, 85, 135, 20);
		contentPane.add(valor_campo);
		valor_campo.setColumns(10);
		
		JLabel nome = new JLabel("Nome");
		nome.setBounds(89, 45, 46, 14);
		contentPane.add(nome);
		
		JLabel valor = new JLabel("Valor");
		valor.setBounds(89, 88, 46, 14);
		contentPane.add(valor);
		
		//Botao que enviar as informaçoes para a lista no JMenu
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produtos c = new Produtos();
				c.setNome(nome_campo.getText());
				c.setValor(Integer.parseInt(valor_campo.getText()));
				//Adicionando na lista
				lc.addProduto(c);
				
				nome_campo.setText("");
				valor_campo.setText("");
			}
		});
		btnEnviar.setBounds(60, 153, 118, 43);
		contentPane.add(btnEnviar);
		
		//Botao responsavel por dar um dispose na janela("fechar ela, sem fechar o programa junto")
				JButton btnSair = new JButton("Sair");
				btnSair.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnSair.setBounds(188, 153, 118, 43);
				contentPane.add(btnSair);
	}
	
}

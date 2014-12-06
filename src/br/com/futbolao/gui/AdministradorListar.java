package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AdministradorListar extends JInternalFrame {
	private JTextField campoProcurar;
	private JTable tabelaADM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministradorListar frame = new AdministradorListar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdministradorListar() {
		setClosable(true);
		setTitle("Listar Administradores");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel painelTabela = new JPanel();
		painelTabela.setBackground(Color.WHITE);
		painelTabela.setBounds(10, 11, 524, 409);
		getContentPane().add(painelTabela);
		painelTabela.setLayout(null);
		
		JLabel lblDigiteONome = new JLabel("Digite o nome do administrador desejado:");
		lblDigiteONome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDigiteONome.setBounds(10, 10, 400, 19);
		painelTabela.add(lblDigiteONome);
		
		campoProcurar = new JTextField();
		campoProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoProcurar.setBounds(10, 40, 400, 20);
		painelTabela.add(campoProcurar);
		campoProcurar.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProcurar.setBounds(420, 39, 94, 23);
		painelTabela.add(btnProcurar);
		
		JScrollPane scrollPaneADM = new JScrollPane();
		scrollPaneADM.setBounds(10, 71, 504, 338);
		painelTabela.add(scrollPaneADM);
		
		tabelaADM = new JTable();
		tabelaADM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tabelaADM.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "CPF", "Ativo"
			}
		));
		tabelaADM.getColumnModel().getColumn(0).setPreferredWidth(52);
		tabelaADM.getColumnModel().getColumn(1).setPreferredWidth(206);
		tabelaADM.getColumnModel().getColumn(2).setPreferredWidth(140);
		tabelaADM.getColumnModel().getColumn(3).setPreferredWidth(38);
		scrollPaneADM.setViewportView(tabelaADM);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 419, 524, 40);
		getContentPane().add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 11, 89, 23);
		painelBotoes.add(btnAlterar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemover.setBounds(109, 12, 89, 23);
		painelBotoes.add(btnRemover);
		setBounds(100, 100, 560, 500);

	}
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}

}

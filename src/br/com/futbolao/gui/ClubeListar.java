package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class ClubeListar extends JInternalFrame {
	private JTextField campoProcurar;
	private JTable tabelaClube;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClubeListar frame = new ClubeListar();
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
	public ClubeListar() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Listar Clubes");
		setClosable(true);
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);
		
		JPanel painelTabela = new JPanel();
		painelTabela.setBackground(Color.WHITE);
		painelTabela.setBounds(10, 11, 764, 404);
		getContentPane().add(painelTabela);
		painelTabela.setLayout(null);
		
		campoProcurar = new JTextField();
		campoProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoProcurar.setBounds(10, 40, 645, 23);
		painelTabela.add(campoProcurar);
		campoProcurar.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProcurar.setBounds(665, 41, 89, 23);
		painelTabela.add(btnProcurar);
		
		JScrollPane scrollPaneClube = new JScrollPane();
		scrollPaneClube.setBounds(10, 74, 744, 330);
		painelTabela.add(scrollPaneClube);
		
		tabelaClube = new JTable();
		tabelaClube.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Nome Completo", "Sigla", "Ativo", "Estado", "Pa\u00EDs"
			}
		));
		tabelaClube.getColumnModel().getColumn(0).setPreferredWidth(59);
		tabelaClube.getColumnModel().getColumn(1).setPreferredWidth(163);
		tabelaClube.getColumnModel().getColumn(2).setPreferredWidth(226);
		tabelaClube.getColumnModel().getColumn(3).setPreferredWidth(32);
		tabelaClube.getColumnModel().getColumn(4).setPreferredWidth(37);
		tabelaClube.getColumnModel().getColumn(5).setPreferredWidth(102);
		tabelaClube.getColumnModel().getColumn(6).setPreferredWidth(91);
		scrollPaneClube.setViewportView(tabelaClube);
		
		JLabel lblTituloProcurar = new JLabel("Digite o nome do clube desejado:");
		lblTituloProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTituloProcurar.setBounds(10, 10, 400, 19);
		painelTabela.add(lblTituloProcurar);
		
		JPanel painelbotoes = new JPanel();
		painelbotoes.setBackground(Color.WHITE);
		painelbotoes.setBounds(10, 415, 764, 44);
		getContentPane().add(painelbotoes);
		painelbotoes.setLayout(null);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 11, 89, 23);
		painelbotoes.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeletar.setBounds(109, 12, 89, 23);
		painelbotoes.add(btnDeletar);

	}

	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
}

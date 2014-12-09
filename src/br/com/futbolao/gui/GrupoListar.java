package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class GrupoListar extends JInternalFrame {
	private JTextField campoCompeticao;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrupoListar frame = new GrupoListar();
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
	public GrupoListar() {
		setTitle("Listar Grupo");
		setClosable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel painelTabela = new JPanel();
		painelTabela.setBackground(Color.WHITE);
		painelTabela.setBounds(10, 11, 964, 346);
		getContentPane().add(painelTabela);
		painelTabela.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 78, 944, 268);
		painelTabela.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Valor aposta", "Limite apostas", "Apostas por apostador", "Lucro ADM%", "Encerramento aposta", "Competi\u00E7\u00E3o", "Rodada", "Pontua\u00E7\u00E3o resultado", "Pontua\u00E7\u00E3o placar"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(56);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(125);
		table.getColumnModel().getColumn(5).setPreferredWidth(122);
		table.getColumnModel().getColumn(6).setPreferredWidth(66);
		table.getColumnModel().getColumn(7).setPreferredWidth(54);
		table.getColumnModel().getColumn(8).setPreferredWidth(113);
		table.getColumnModel().getColumn(9).setPreferredWidth(96);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		
		campoCompeticao = new JTextField();
		campoCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCompeticao.setBounds(10, 47, 824, 20);
		painelTabela.add(campoCompeticao);
		campoCompeticao.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProcurar.setBounds(844, 44, 110, 23);
		painelTabela.add(btnProcurar);
		
		JLabel lblCompeticao = new JLabel("Digite a competi\u00E7\u00E3o:");
		lblCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCompeticao.setBounds(10, 16, 135, 20);
		painelTabela.add(lblCompeticao);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 357, 964, 52);
		getContentPane().add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 18, 89, 23);
		painelBotoes.add(btnAlterar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemover.setBounds(109, 19, 89, 23);
		painelBotoes.add(btnRemover);
		setBounds(100, 100, 1000, 450);

	}

	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	
}

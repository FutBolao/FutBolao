package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class CompeticaoListar extends JInternalFrame {
	private JTextField campoProcurar;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompeticaoListar frame = new CompeticaoListar();
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
	public CompeticaoListar() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 522, 402);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		campoProcurar = new JTextField();
		campoProcurar.setBounds(10, 32, 380, 20);
		panel.add(campoProcurar);
		campoProcurar.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProcurar.setBounds(400, 30, 110, 23);
		panel.add(btnProcurar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 500, 339);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Quantidade de Rodadas", "Ativo"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(244);
		table.getColumnModel().getColumn(2).setPreferredWidth(133);
		table.getColumnModel().getColumn(3).setPreferredWidth(46);
		scrollPane.setViewportView(table);
		
		JLabel lblTituloProcurar = new JLabel("Digite o nome da competi\u00E7\u00E3o ou deixe em branco para listar todas:");
		lblTituloProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTituloProcurar.setBounds(10, 7, 400, 19);
		panel.add(lblTituloProcurar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 414, 522, 45);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 11, 89, 23);
		panel_1.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeletar.setBounds(109, 12, 89, 23);
		panel_1.add(btnDeletar);
		setTitle("Listar Competi\u00E7\u00F5es");
		setClosable(true);
		setBounds(100, 100, 557, 500);

	}
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
}

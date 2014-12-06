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
	private JTable table;

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
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 764, 404);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		campoProcurar = new JTextField();
		campoProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoProcurar.setBounds(10, 40, 645, 23);
		panel.add(campoProcurar);
		campoProcurar.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setBounds(665, 41, 89, 23);
		panel.add(btnProcurar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 744, 330);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Nome Completo", "Sigla", "Ativo", "Estado", "Pa\u00EDs"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(59);
		table.getColumnModel().getColumn(1).setPreferredWidth(163);
		table.getColumnModel().getColumn(2).setPreferredWidth(226);
		table.getColumnModel().getColumn(3).setPreferredWidth(32);
		table.getColumnModel().getColumn(4).setPreferredWidth(37);
		table.getColumnModel().getColumn(5).setPreferredWidth(102);
		table.getColumnModel().getColumn(6).setPreferredWidth(91);
		scrollPane.setViewportView(table);
		
		JLabel lblTituloProcurar = new JLabel("Digite o nome do clube desejado:");
		lblTituloProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTituloProcurar.setBounds(10, 10, 400, 19);
		panel.add(lblTituloProcurar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 415, 764, 44);
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

	}

	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
}

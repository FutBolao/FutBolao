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
	private JTable table;

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
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 524, 409);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDigiteONome = new JLabel("Digite o nome do administrador desejado:");
		lblDigiteONome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDigiteONome.setBounds(10, 10, 400, 19);
		panel.add(lblDigiteONome);
		
		campoProcurar = new JTextField();
		campoProcurar.setBounds(10, 40, 400, 20);
		panel.add(campoProcurar);
		campoProcurar.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setBounds(420, 39, 94, 23);
		panel.add(btnProcurar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 504, 338);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "CPF", "Ativo"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(52);
		table.getColumnModel().getColumn(1).setPreferredWidth(206);
		table.getColumnModel().getColumn(2).setPreferredWidth(140);
		table.getColumnModel().getColumn(3).setPreferredWidth(38);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 419, 524, 40);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 11, 89, 23);
		panel_1.add(btnAlterar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(109, 12, 89, 23);
		panel_1.add(btnRemover);
		setBounds(100, 100, 560, 500);

	}
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}

}

package br.com.futbolao.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ApostadorListar extends JInternalFrame {
	private JTextField campoProcurar;
	private JTable tabelalistarApostador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApostadorListar frame = new ApostadorListar();
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
	public ApostadorListar() {
		setTitle("Listar Apostador");
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 560, 470);
		getContentPane().setLayout(null);
		
		JPanel painelTabela = new JPanel();
		painelTabela.setBackground(Color.WHITE);
		painelTabela.setBounds(10, 11, 530, 419);
		getContentPane().add(painelTabela);
		painelTabela.setLayout(null);
		
		JLabel lblDigitenome = new JLabel("Digite o nome do Apostador Desejado :");
		lblDigitenome.setBounds(10, 11, 223, 14);
		lblDigitenome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		painelTabela.add(lblDigitenome);
		
		campoProcurar = new JTextField();
		campoProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoProcurar.setBounds(10, 36, 411, 20);
		painelTabela.add(campoProcurar);
		campoProcurar.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProcurar.setBounds(431, 35, 89, 23);
		painelTabela.add(btnProcurar);
		
		JScrollPane scrollPaneListarApostador = new JScrollPane();
		scrollPaneListarApostador.setBounds(10, 67, 510, 292);
		painelTabela.add(scrollPaneListarApostador);
		
		tabelalistarApostador = new JTable();
		tabelalistarApostador.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "CPF", "Nome", "Telefone"
			}
		));
		tabelalistarApostador.getColumnModel().getColumn(0).setPreferredWidth(57);
		tabelalistarApostador.getColumnModel().getColumn(1).setPreferredWidth(155);
		tabelalistarApostador.getColumnModel().getColumn(2).setPreferredWidth(268);
		tabelalistarApostador.getColumnModel().getColumn(3).setPreferredWidth(127);
		scrollPaneListarApostador.setViewportView(tabelalistarApostador);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 370, 510, 41);
		painelTabela.add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(0, 11, 101, 23);
		painelBotoes.add(btnAlterar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemover.setBounds(111, 11, 104, 23);
		painelBotoes.add(btnRemover);

	}
}

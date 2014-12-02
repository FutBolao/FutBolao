package br.com.futbolao.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CompeticaoAlterar extends JInternalFrame {
	private JTextField campoNome;
	private JTextField campoQntdeCompeticoes;
	private JTextField campoID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompeticaoAlterar frame = new CompeticaoAlterar();
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
	public CompeticaoAlterar() {
		setTitle("Alterar Competi\u00E7\u00E3o");
		setClosable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 364, 228);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 67, 46, 14);
		panel.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNome.setBounds(10, 92, 344, 20);
		panel.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel lblQuantidadeDeCompeties = new JLabel("Quantidade de Competi\u00E7\u00F5es:");
		lblQuantidadeDeCompeties.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantidadeDeCompeties.setBounds(10, 123, 170, 14);
		panel.add(lblQuantidadeDeCompeties);
		
		campoQntdeCompeticoes = new JTextField();
		campoQntdeCompeticoes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoQntdeCompeticoes.setBounds(10, 148, 344, 20);
		panel.add(campoQntdeCompeticoes);
		campoQntdeCompeticoes.setColumns(10);
		
		JButton btnNewButton = new JButton("Alterar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(10, 197, 89, 20);
		panel.add(btnNewButton);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(10, 11, 46, 14);
		panel.add(lblId);
		
		campoID = new JTextField();
		campoID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoID.setBounds(10, 36, 86, 20);
		panel.add(campoID);
		campoID.setColumns(10);
		setBounds(100, 100, 400, 280);

	}

}

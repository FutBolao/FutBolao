package br.com.futbolao.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CompeticaoCadastrar extends JInternalFrame {
	private JTextField campoNome;
	private JTextField campoQntdeCompeticoes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompeticaoCadastrar frame = new CompeticaoCadastrar();
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
	public CompeticaoCadastrar() {
		setTitle("Cadastrar Competi\u00E7\u00E3o");
		setClosable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 364, 198);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 11, 46, 14);
		panel.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNome.setBounds(10, 36, 344, 20);
		panel.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel lblQuantidadeDeCompeties = new JLabel("Quantidade de Competi\u00E7\u00F5es:");
		lblQuantidadeDeCompeties.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantidadeDeCompeties.setBounds(10, 67, 170, 14);
		panel.add(lblQuantidadeDeCompeties);
		
		campoQntdeCompeticoes = new JTextField();
		campoQntdeCompeticoes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoQntdeCompeticoes.setBounds(10, 92, 344, 20);
		panel.add(campoQntdeCompeticoes);
		campoQntdeCompeticoes.setColumns(10);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(10, 167, 89, 20);
		panel.add(btnNewButton);
		setBounds(100, 100, 400, 250);

	}

}

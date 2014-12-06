package br.com.futbolao.gui;

import java.awt.Dimension;
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
		
		JPanel painelForm = new JPanel();
		painelForm.setBackground(Color.WHITE);
		painelForm.setBounds(10, 11, 364, 228);
		getContentPane().add(painelForm);
		painelForm.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 67, 46, 14);
		painelForm.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNome.setBounds(10, 92, 344, 20);
		painelForm.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel lblQuantidadeDeCompeties = new JLabel("Quantidade de Competi\u00E7\u00F5es:");
		lblQuantidadeDeCompeties.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantidadeDeCompeties.setBounds(10, 123, 170, 14);
		painelForm.add(lblQuantidadeDeCompeties);
		
		campoQntdeCompeticoes = new JTextField();
		campoQntdeCompeticoes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoQntdeCompeticoes.setBounds(10, 148, 344, 20);
		painelForm.add(campoQntdeCompeticoes);
		campoQntdeCompeticoes.setColumns(10);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 197, 89, 20);
		painelForm.add(btnAlterar);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(10, 11, 46, 14);
		painelForm.add(lblId);
		
		campoID = new JTextField();
		campoID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoID.setBounds(10, 36, 86, 20);
		painelForm.add(campoID);
		campoID.setColumns(10);
		setBounds(100, 100, 400, 280);

	}

	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	
}

package br.com.futbolao.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.Color;

@SuppressWarnings("serial")
public class ClubeAlterar extends JInternalFrame {
	private JTextField campoNomeCompleto;
	private JTextField campoNome;
	private JTextField campoSigla;
	private JTextField campoID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClubeAlterar frame = new ClubeAlterar();
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
	public ClubeAlterar() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setForeground(Color.WHITE);
		setTitle("Alterar Clube");
		setClosable(true);
		setBounds(100, 100, 450, 264);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setBackground(Color.WHITE);
		painelForm.setForeground(Color.WHITE);
		painelForm.setBounds(10, 11, 414, 214);
		getContentPane().add(painelForm);
		painelForm.setLayout(null);
		
		JLabel lblNomeCompleto = new JLabel("Nome Completo:");
		lblNomeCompleto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNomeCompleto.setBounds(98, 11, 92, 14);
		painelForm.add(lblNomeCompleto);
		
		campoNomeCompleto = new JTextField();
		campoNomeCompleto.setEditable(false);
		campoNomeCompleto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNomeCompleto.setBounds(90, 36, 314, 20);
		painelForm.add(campoNomeCompleto);
		campoNomeCompleto.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 67, 92, 14);
		painelForm.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setEditable(false);
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNome.setColumns(10);
		campoNome.setBounds(10, 92, 306, 20);
		painelForm.add(campoNome);
		
		JLabel lblSigla = new JLabel("Sigla:");
		lblSigla.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSigla.setBounds(326, 67, 28, 14);
		painelForm.add(lblSigla);
		
		campoSigla = new JTextField();
		campoSigla.setEditable(false);
		campoSigla.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSigla.setColumns(10);
		campoSigla.setBounds(326, 92, 78, 20);
		painelForm.add(campoSigla);
		
		JLabel lblPais = new JLabel("Pa\u00EDs:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(10, 123, 28, 14);
		painelForm.add(lblPais);
		
		JComboBox campoPais = new JComboBox();
		campoPais.setEnabled(false);
		campoPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPais.setBounds(10, 148, 170, 20);
		painelForm.add(campoPais);
		
		JComboBox campoEstado = new JComboBox();
		campoEstado.setEnabled(false);
		campoEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEstado.setBounds(190, 148, 214, 20);
		painelForm.add(campoEstado);
		
		JLabel lblEstadp = new JLabel("Estado:");
		lblEstadp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstadp.setBounds(190, 123, 78, 14);
		painelForm.add(lblEstadp);
		
		JButton btnCadastrar = new JButton("Alterar");
		btnCadastrar.setEnabled(false);
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrar.setBounds(10, 179, 89, 23);
		painelForm.add(btnCadastrar);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(10, 11, 46, 14);
		painelForm.add(lblId);
		
		campoID = new JTextField();
		campoID.setEditable(false);
		campoID.setBounds(10, 36, 70, 20);
		painelForm.add(campoID);
		campoID.setColumns(10);

	}
}

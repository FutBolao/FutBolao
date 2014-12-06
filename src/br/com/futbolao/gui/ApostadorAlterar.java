package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ApostadorAlterar extends JInternalFrame {
	private JTextField campoNome;
	private JTextField campoCPF;
	private JTextField campoDataNasc;
	private JTextField campoTelefone;
	private JTextField campoEmail;
	private JTextField campoRua;
	private JTextField campoNumero;
	private JTextField campoBairro;
	private JTextField campoCidade;
	private JTextField campoEstado;
	private JTextField campoPais;
	private JTextField campoClube;
	private JTextField campoUsuario;
	private JTextField campoSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApostadorAlterar frame = new ApostadorAlterar();
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
	public ApostadorAlterar() {
		setTitle("Alterar Apostador");
		setClosable(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 444, 525);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.BLACK);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 407, 464);
		getContentPane().add(panel);
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 11, 51, 23);
		panel.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNome.setColumns(10);
		campoNome.setBounds(10, 45, 385, 20);
		panel.add(campoNome);
		
		JLabel lblCPF = new JLabel("CPF :");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCPF.setBounds(10, 76, 46, 14);
		panel.add(lblCPF);
		
		campoCPF = new JTextField();
		campoCPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCPF.setColumns(10);
		campoCPF.setBounds(10, 101, 121, 20);
		panel.add(campoCPF);
		
		JLabel lblDataNasc = new JLabel("Data de Nascimento :");
		lblDataNasc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDataNasc.setBounds(141, 74, 121, 19);
		panel.add(lblDataNasc);
		
		campoDataNasc = new JTextField();
		campoDataNasc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoDataNasc.setColumns(10);
		campoDataNasc.setBounds(141, 101, 131, 20);
		panel.add(campoDataNasc);
		
		JLabel lblSexo = new JLabel("Sexo :");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(282, 76, 46, 14);
		panel.add(lblSexo);
		
		JComboBox campoSexo = new JComboBox();
		campoSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSexo.setBounds(282, 101, 113, 20);
		panel.add(campoSexo);
		
		JLabel lblTelefone = new JLabel("Telefone :");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefone.setBounds(10, 132, 58, 14);
		panel.add(lblTelefone);
		
		campoTelefone = new JTextField();
		campoTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoTelefone.setColumns(10);
		campoTelefone.setBounds(10, 160, 121, 20);
		panel.add(campoTelefone);
		
		JLabel lblEmail = new JLabel("Email : ");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(141, 132, 46, 14);
		panel.add(lblEmail);
		
		campoEmail = new JTextField();
		campoEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEmail.setColumns(10);
		campoEmail.setBounds(141, 160, 254, 20);
		panel.add(campoEmail);
		
		JLabel lblRua = new JLabel("Rua : ");
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRua.setBounds(10, 191, 67, 20);
		panel.add(lblRua);
		
		campoRua = new JTextField();
		campoRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoRua.setColumns(10);
		campoRua.setBounds(10, 222, 332, 20);
		panel.add(campoRua);
		
		JLabel lblNumero = new JLabel("N\u00BA:");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumero.setBounds(344, 194, 51, 14);
		panel.add(lblNumero);
		
		campoNumero = new JTextField();
		campoNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNumero.setColumns(10);
		campoNumero.setBounds(344, 222, 51, 20);
		panel.add(campoNumero);
		
		JLabel lblBairro = new JLabel("Bairro :");
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBairro.setBounds(10, 253, 51, 14);
		panel.add(lblBairro);
		
		campoBairro = new JTextField();
		campoBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoBairro.setColumns(10);
		campoBairro.setBounds(10, 278, 190, 20);
		panel.add(campoBairro);
		
		JLabel lblCidade = new JLabel("Cidade : ");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCidade.setBounds(210, 253, 51, 14);
		panel.add(lblCidade);
		
		campoCidade = new JTextField();
		campoCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCidade.setColumns(10);
		campoCidade.setBounds(210, 278, 185, 20);
		panel.add(campoCidade);
		
		JLabel lblEstado = new JLabel("Estado :");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstado.setBounds(10, 309, 67, 14);
		panel.add(lblEstado);
		
		campoEstado = new JTextField();
		campoEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEstado.setColumns(10);
		campoEstado.setBounds(10, 334, 190, 20);
		panel.add(campoEstado);
		
		JLabel lblPais = new JLabel("Pa\u00EDs : ");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(210, 307, 46, 18);
		panel.add(lblPais);
		
		campoPais = new JTextField();
		campoPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPais.setColumns(10);
		campoPais.setBounds(210, 334, 185, 20);
		panel.add(campoPais);
		
		JLabel lblClube = new JLabel("Clube :");
		lblClube.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblClube.setBounds(10, 365, 51, 14);
		panel.add(lblClube);
		
		campoClube = new JTextField();
		campoClube.setColumns(10);
		campoClube.setBounds(10, 390, 121, 20);
		panel.add(campoClube);
		
		JLabel lblUsuario = new JLabel("Usuario : ");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsuario.setBounds(141, 365, 51, 15);
		panel.add(lblUsuario);
		
		campoUsuario = new JTextField();
		campoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoUsuario.setColumns(10);
		campoUsuario.setBounds(141, 389, 121, 20);
		panel.add(campoUsuario);
		
		JLabel lblSenha = new JLabel("Senha : ");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setBounds(272, 365, 46, 14);
		panel.add(lblSenha);
		
		campoSenha = new JTextField();
		campoSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSenha.setColumns(10);
		campoSenha.setBounds(272, 389, 121, 20);
		panel.add(campoSenha);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(10, 426, 93, 23);
		panel.add(btnAlterar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(113, 426, 93, 23);
		panel.add(btnLimpar);

	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}

}

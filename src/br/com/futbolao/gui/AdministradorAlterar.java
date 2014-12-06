package br.com.futbolao.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AdministradorAlterar extends JInternalFrame {
	private JTextField campoNome;
	private JTextField campoCPF;
	private JTextField campoTelefone;
	private JTextField campoEmail;
	private JTextField campoRua;
	private JTextField campoBairro;
	private JTextField campoNumero;
	private JTextField campoUsuario;
	private JTextField campoDatadeNascimento;
	private JTextField campoCidade;
	private JTextField campoEstado;
	private JTextField campoPais;
	private JTextField campoSenha;
	private JTextField CampoID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministradorAlterar frame = new AdministradorAlterar();
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
	public AdministradorAlterar() {
		
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().setBackground(Color.WHITE);
		setTitle("Alterar Administrador");
		setClosable(true);
		setBounds(100, 100, 440, 475);
		getContentPane().setLayout(null);
		
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(94, 11, 46, 14);
		getContentPane().add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNome.setBounds(94, 36, 316, 20);
		getContentPane().add(campoNome);
		campoNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(10, 67, 46, 14);
		getContentPane().add(lblCpf);
		
		campoCPF = new JTextField();
		campoCPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCPF.setColumns(10);
		campoCPF.setBounds(10, 92, 120, 20);
		getContentPane().add(campoCPF);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefone.setBounds(10, 123, 65, 14);
		getContentPane().add(lblTelefone);
		
		campoTelefone = new JTextField();
		campoTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoTelefone.setBounds(10, 148, 120, 20);
		getContentPane().add(campoTelefone);
		campoTelefone.setColumns(10);
		
		JComboBox campoSexo = new JComboBox();
		campoSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSexo.setBounds(269, 92, 141, 20);
		getContentPane().add(campoSexo);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(269, 67, 46, 14);
		getContentPane().add(lblSexo);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(139, 123, 46, 14);
		getContentPane().add(lblEmail);
		
		campoEmail = new JTextField();
		campoEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEmail.setBounds(140, 148, 270, 20);
		getContentPane().add(campoEmail);
		campoEmail.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRua.setBounds(10, 179, 46, 14);
		getContentPane().add(lblRua);
		
		campoRua = new JTextField();
		campoRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoRua.setBounds(10, 204, 350, 20);
		getContentPane().add(campoRua);
		campoRua.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBairro.setBounds(10, 235, 46, 14);
		getContentPane().add(lblBairro);
		
		campoBairro = new JTextField();
		campoBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoBairro.setBounds(10, 260, 200, 20);
		getContentPane().add(campoBairro);
		campoBairro.setColumns(10);
		
		JLabel lblN = new JLabel("N\u00BA:");
		lblN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblN.setBounds(364, 179, 46, 14);
		getContentPane().add(lblN);
		
		campoNumero = new JTextField();
		campoNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNumero.setBounds(364, 204, 46, 20);
		getContentPane().add(campoNumero);
		campoNumero.setColumns(10);
		
		JLabel lblUsario = new JLabel("Usu\u00E1rio:");
		lblUsario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsario.setBounds(10, 347, 46, 14);
		getContentPane().add(lblUsario);
		
		campoUsuario = new JTextField();
		campoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoUsuario.setBounds(10, 372, 200, 20);
		getContentPane().add(campoUsuario);
		campoUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setBounds(220, 347, 46, 14);
		getContentPane().add(lblSenha);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDataDeNascimento.setBounds(139, 67, 120, 14);
		getContentPane().add(lblDataDeNascimento);
		
		campoDatadeNascimento = new JTextField();
		campoDatadeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoDatadeNascimento.setBounds(140, 92, 120, 20);
		getContentPane().add(campoDatadeNascimento);
		campoDatadeNascimento.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCidade.setBounds(220, 236, 46, 14);
		getContentPane().add(lblCidade);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstado.setBounds(10, 291, 46, 14);
		getContentPane().add(lblEstado);
		
		JLabel lblPais = new JLabel("Pa\u00EDs:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(220, 291, 46, 14);
		getContentPane().add(lblPais);
		
		campoCidade = new JTextField();
		campoCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCidade.setBounds(220, 260, 190, 20);
		getContentPane().add(campoCidade);
		campoCidade.setColumns(10);
		
		campoEstado = new JTextField();
		campoEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEstado.setBounds(10, 316, 200, 20);
		getContentPane().add(campoEstado);
		campoEstado.setColumns(10);
		
		campoPais = new JTextField();
		campoPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPais.setBounds(220, 316, 190, 20);
		getContentPane().add(campoPais);
		campoPais.setColumns(10);
		
		campoSenha = new JTextField();
		campoSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSenha.setBounds(220, 372, 190, 20);
		getContentPane().add(campoSenha);
		campoSenha.setColumns(10);
		
		JButton btnCadastrar = new JButton("Alterar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrar.setBounds(10, 403, 100, 20);
		getContentPane().add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBounds(120, 403, 100, 20);
		getContentPane().add(btnLimpar);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(10, 12, 46, 14);
		getContentPane().add(lblId);
		
		CampoID = new JTextField();
		CampoID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CampoID.setBounds(10, 36, 65, 20);
		getContentPane().add(CampoID);
		CampoID.setColumns(10);
	
	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	
	
}

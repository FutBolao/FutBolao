package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import br.com.futbolao.apostador.Apostador;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.util.Endereco;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ApostadorCadastrar extends JInternalFrame {
	private JTextField campoNome;
	private JTextField campoCpf;
	private JTextField campoTelefone;
	private JTextField campoEmail;
	private JTextField campoNascimento;
	private JTextField campoUsuario;
	private JTextField campoSenha;
	private JTextField campoRua;
	private JTextField campoNumero;
	private JTextField campoBairro;
	private JTextField campoCidade;
	private JTextField campoEstado;
	private JTextField campoPais;
	Fachada fachada = null;
	private JComboBox campoSexo;
	private JTextField campoClube;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApostadorCadastrar frame = new ApostadorCadastrar();
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
	public ApostadorCadastrar() {
		setTitle("Cadastro Apostador");
		setClosable(true);
		setBounds(100, 100, 435, 521);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setForeground(Color.BLACK);
		painelForm.setBackground(Color.WHITE);
		painelForm.setBounds(10, 11, 407, 464);
		getContentPane().add(painelForm);
		painelForm.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 11, 51, 23);
		painelForm.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNome.setBounds(10, 45, 385, 20);
		painelForm.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF :");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(10, 76, 46, 14);
		painelForm.add(lblCpf);
		
		campoCpf = new JTextField();
		campoCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCpf.setBounds(10, 101, 121, 20);
		painelForm.add(campoCpf);
		campoCpf.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento :");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDataDeNascimento.setBounds(141, 74, 121, 19);
		painelForm.add(lblDataDeNascimento);
		
		campoNascimento = new JTextField();
		campoNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNascimento.setBounds(141, 101, 131, 20);
		painelForm.add(campoNascimento);
		campoNascimento.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo :");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(282, 76, 46, 14);
		painelForm.add(lblSexo);
		
		JComboBox campoSexo = new JComboBox();
		campoSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSexo.setBounds(282, 101, 113, 20);
		painelForm.add(campoSexo);
		
		JLabel lblTelefone = new JLabel("Telefone :");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefone.setBounds(10, 132, 58, 14);
		painelForm.add(lblTelefone);
		
		campoTelefone = new JTextField();
		campoTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoTelefone.setBounds(10, 160, 121, 20);
		painelForm.add(campoTelefone);
		campoTelefone.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email : ");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(141, 132, 46, 14);
		painelForm.add(lblEmail);
		
		campoEmail = new JTextField();
		campoEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEmail.setBounds(141, 160, 254, 20);
		painelForm.add(campoEmail);
		campoEmail.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua : ");
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRua.setBounds(10, 191, 67, 20);
		painelForm.add(lblRua);
		
		campoRua = new JTextField();
		campoRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoRua.setBounds(10, 222, 332, 20);
		painelForm.add(campoRua);
		campoRua.setColumns(10);
		
		JLabel lblNumero = new JLabel("N\u00BA:");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumero.setBounds(344, 194, 51, 14);
		painelForm.add(lblNumero);
		
		campoNumero = new JTextField();
		campoNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNumero.setBounds(344, 222, 51, 20);
		painelForm.add(campoNumero);
		campoNumero.setColumns(10);
		
		JLabel lblPas = new JLabel("Bairro :");
		lblPas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPas.setBounds(10, 253, 51, 14);
		painelForm.add(lblPas);
		
		campoBairro = new JTextField();
		campoBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoBairro.setBounds(10, 278, 190, 20);
		painelForm.add(campoBairro);
		campoBairro.setColumns(10);
		
		JLabel lblEstado = new JLabel("Cidade : ");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstado.setBounds(210, 253, 51, 14);
		painelForm.add(lblEstado);
		
		campoCidade = new JTextField();
		campoCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCidade.setBounds(210, 278, 185, 20);
		painelForm.add(campoCidade);
		campoCidade.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Estado :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 309, 67, 14);
		painelForm.add(lblNewLabel_1);
		
		campoEstado = new JTextField();
		campoEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEstado.setBounds(10, 334, 190, 20);
		painelForm.add(campoEstado);
		campoEstado.setColumns(10);
		
		JLabel lblPais = new JLabel("Pa\u00EDs : ");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(210, 307, 46, 18);
		painelForm.add(lblPais);
		
		campoPais = new JTextField();
		campoPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPais.setBounds(210, 334, 185, 20);
		painelForm.add(campoPais);
		campoPais.setColumns(10);
		
		JLabel lblClube = new JLabel("Clube :");
		lblClube.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblClube.setBounds(10, 365, 51, 14);
		painelForm.add(lblClube);
		
		campoClube = new JTextField();
		campoClube.setBounds(10, 390, 121, 20);
		painelForm.add(campoClube);
		campoClube.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario : ");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsuario.setBounds(141, 365, 51, 15);
		painelForm.add(lblUsuario);
		
		campoUsuario = new JTextField();
		campoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoUsuario.setBounds(141, 389, 121, 20);
		painelForm.add(campoUsuario);
		campoUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Senha : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(272, 365, 46, 14);
		painelForm.add(lblNewLabel);
		
		campoSenha = new JTextField();
		campoSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSenha.setBounds(272, 389, 121, 20);
		painelForm.add(campoSenha);
		campoSenha.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(10, 426, 93, 23);
		painelForm.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setBounds(113, 426, 93, 23);
		painelForm.add(btnLimpar);

	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void limparCampos(){
		campoNome.setText("");
		campoCpf.setText("");
		campoBairro.setText("");
		campoEstado.setText("");
		campoPais.setText("");
		campoCidade.setText("");
		campoNascimento.setText("");
		campoNumero.setText("");
		campoEmail.setText("");
		campoRua.setText("");
		campoUsuario.setText("");
		campoSenha.setText("");
		campoTelefone.setText("");
		campoSexo.setSelectedIndex(0);
		campoClube.setText("");
	}
	
	public boolean validarCampos(){
		String nome = campoNome.getText();
		String cpf = campoCpf.getText();
		String dataNascimento = campoNascimento.getText();
		String sexo = (String)campoSexo.getSelectedItem();
		String telefone = campoTelefone.getText();
		String email = campoEmail.getText();
		String rua = campoRua.getText();
		String numero = campoNumero.getText();
		String bairro = campoBairro.getText();
		String cidade = campoCidade.getText();
		String estado = campoEstado.getText();
		String pais = campoPais.getText();
		String clube = campoClube.getText();
		String usuario = campoUsuario.getText();
		String senha = campoSenha.getText();
		if (nome.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoNome.requestFocus();
			}
			return false;
		} else if (cpf.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoCpf.requestFocus();
			}
			return false;
		} else if (dataNascimento.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoNascimento.requestFocus();
			}
			return false;
		} else if (sexo.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoSexo.requestFocus();
			}
			return false;
		} else if (telefone.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoTelefone.requestFocus();
			}
			return false;
		} else if (email.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoEmail.requestFocus();
			}
			return false;
		} else if (rua.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoRua.requestFocus();
			}
			return false;
		} else if (numero.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoNumero.requestFocus();
			}
			return false;
		} else if (bairro.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoBairro.requestFocus();
			}
			return false;
		} else if (cidade.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoCidade.requestFocus();
			}
			return false;
		} else if (estado.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoEstado.requestFocus();
			}
			return false;
		} else if (pais.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoPais.requestFocus();
			}
			return false;
		} else if (clube.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoClube.requestFocus();
			}
			return false;
		} else if (usuario.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoUsuario.requestFocus();
			}
			return false;
		} else if (senha.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoSenha.requestFocus();
			}
			return false;
		}
		return true;
}
		public void cadastar(){
			if(validarCampos()){
				String nome = campoNome.getText();
				String cpf = campoCpf.getText();
				String dataDeNascimento = campoNascimento.getText();
				String sexoString = (String)campoSexo.getSelectedItem();
				char sexo = sexoString.charAt(0);
				String telefone = campoTelefone.getText();
				String email = campoEmail.getText();
				String rua = campoRua.getText();
				String numero = campoNumero.getText();
				String bairro = campoBairro.getText();
				String cidade = campoCidade.getText();
				String estado = campoEstado.getText();
				String pais = campoPais.getText();
				String clube = campoClube.getText();
				String usuario = campoUsuario.getText();
				String senha = campoSenha.getText();
				Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, pais);
				try {
					fachada.cadastrarApostador(new Apostador(0, nome, cpf, sexo, telefone, email, endereco, dataDeNascimento, usuario, senha, clube)); 
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
}
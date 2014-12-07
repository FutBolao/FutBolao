package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import br.com.futbolao.apostador.Apostador;
import br.com.futbolao.exception.ApostadorJaCadastradoException;
import br.com.futbolao.exception.CadastroEfetuadoComSucessoException;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.CpfInvalidoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.NomeVazioException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.util.Endereco;
import br.com.futbolao.util.FormataCampoPermiteTudo;
import br.com.futbolao.util.FormataCampoPermiteTudoUpperCase;
import br.com.futbolao.util.MascaraCampo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class ApostadorCadastrar extends JInternalFrame {
	private MascaraCampo mascara = new MascaraCampo();
	private JTextField campoNome;
	private JFormattedTextField campoCpf;
	private JFormattedTextField campoTelefone;
	private JTextField campoEmail;
	private JFormattedTextField campoDatadeNascimento;
	private JTextField campoUsuario;
	private JPasswordField campoSenha;
	private JTextField campoRua;
	private JTextField campoNumero;
	private JTextField campoBairro;
	private JTextField campoCidade;
	private JTextField campoEstado;
	private JTextField campoPais;
	@SuppressWarnings("rawtypes")
	private JComboBox campoSexo;
	Fachada fachada = null;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ApostadorCadastrar() {
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			try {
				throw new ErroAoInstanciarFachadaException();
			} catch (ErroAoInstanciarFachadaException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			}
		}
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		setTitle("Cadastrar Apostador");
		setClosable(true);
		setBounds(100, 100, 443, 520);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setForeground(Color.BLACK);
		painelForm.setBackground(Color.WHITE);
		painelForm.setBounds(10, 11, 407, 464);
		getContentPane().add(painelForm);
		painelForm.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 11, 46, 14);
		painelForm.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNome.setBounds(10, 36, 400, 20);
		painelForm.add(campoNome);
		campoNome.setDocument(new FormataCampoPermiteTudoUpperCase(100));
		campoNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF :");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(10, 76, 46, 14);
		painelForm.add(lblCpf);
		
		campoCpf = new JFormattedTextField();
		campoCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCpf.setBounds(10, 101, 121, 20);
		painelForm.add(campoCpf);
		mascara.getCpf().install(campoCpf);
		campoCpf.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento :");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDataDeNascimento.setBounds(141, 74, 121, 19);
		painelForm.add(lblDataDeNascimento);
		
		campoDatadeNascimento = new JFormattedTextField();
		campoDatadeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoDatadeNascimento.setBounds(141, 101, 131, 20);
		painelForm.add(campoDatadeNascimento);
		mascara.getData().install(campoDatadeNascimento);
		campoDatadeNascimento.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo :");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(282, 76, 46, 14);
		painelForm.add(lblSexo);
		
		JLabel lblTelefone = new JLabel("Telefone :");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefone.setBounds(10, 132, 58, 14);
		painelForm.add(lblTelefone);
		
		campoTelefone = new JFormattedTextField();
		campoTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoTelefone.setBounds(10, 160, 121, 20);
		painelForm.add(campoTelefone);
		mascara.getTelefone().install(campoTelefone);
		campoTelefone.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email : ");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(141, 132, 46, 14);
		painelForm.add(lblEmail);
		
		campoEmail = new JTextField();
		campoEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEmail.setBounds(141, 160, 254, 20);
		painelForm.add(campoEmail);
		campoEmail.setDocument(new FormataCampoPermiteTudoUpperCase(50));
		campoEmail.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua : ");
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRua.setBounds(10, 191, 67, 20);
		painelForm.add(lblRua);
		
		campoRua = new JTextField();
		campoRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoRua.setBounds(10, 222, 318, 20);
		painelForm.add(campoRua);
		campoRua.setDocument(new FormataCampoPermiteTudoUpperCase(50));
		campoRua.setColumns(10);
		
		JLabel lblNumero = new JLabel("N\u00BA:");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumero.setBounds(338, 194, 51, 14);
		painelForm.add(lblNumero);
		
		campoNumero = new JTextField();
		campoNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNumero.setBounds(338, 222, 57, 20);
		painelForm.add(campoNumero);
		campoNumero.setDocument(new FormataCampoPermiteTudoUpperCase(6));
		campoNumero.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro :");
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBairro.setBounds(10, 253, 51, 14);
		painelForm.add(lblBairro);
		
		campoBairro = new JTextField();
		campoBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoBairro.setBounds(10, 278, 190, 20);
		painelForm.add(campoBairro);
		campoBairro.setDocument(new FormataCampoPermiteTudoUpperCase(30));
		campoBairro.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade : ");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCidade.setBounds(210, 253, 51, 14);
		painelForm.add(lblCidade);
		
		campoCidade = new JTextField();
		campoCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCidade.setBounds(210, 278, 185, 20);
		painelForm.add(campoCidade);
		campoCidade.setDocument(new FormataCampoPermiteTudoUpperCase(30));
		campoCidade.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado :");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstado.setBounds(10, 309, 67, 14);
		painelForm.add(lblEstado);
		
		campoEstado = new JTextField();
		campoEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEstado.setBounds(10, 334, 190, 20);
		painelForm.add(campoEstado);
		campoEstado.setDocument(new FormataCampoPermiteTudoUpperCase(20));
		campoEstado.setColumns(10);
		
		JLabel lblPais = new JLabel("Pa\u00EDs : ");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(210, 307, 46, 18);
		painelForm.add(lblPais);
		
		campoPais = new JTextField();
		campoPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPais.setBounds(210, 334, 185, 20);
		painelForm.add(campoPais);
		campoPais.setDocument(new FormataCampoPermiteTudoUpperCase(20));
		campoPais.setColumns(10);
		
		JLabel lblClube = new JLabel("Clube :");
		lblClube.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblClube.setBounds(10, 365, 51, 14);
		painelForm.add(lblClube);
		
		campoClube = new JTextField();
		campoClube.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoClube.setBounds(10, 390, 121, 20);
		painelForm.add(campoClube);
		campoClube.setDocument(new FormataCampoPermiteTudoUpperCase(30));
		campoClube.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario : ");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsuario.setBounds(141, 365, 51, 15);
		painelForm.add(lblUsuario);
		
		campoUsuario = new JTextField();
		campoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoUsuario.setBounds(141, 389, 121, 20);
		painelForm.add(campoUsuario);
		campoUsuario.setDocument(new FormataCampoPermiteTudoUpperCase(20));
		campoUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha : ");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setBounds(272, 365, 46, 14);
		painelForm.add(lblSenha);
		
		campoSenha = new JPasswordField();
		campoSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSenha.setBounds(272, 389, 121, 20);
		painelForm.add(campoSenha);
		campoSenha.setDocument(new FormataCampoPermiteTudo(50));
		campoSenha.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastar();
			}
		});
		btnCadastrar.setBounds(10, 426, 93, 23);
		painelForm.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setBounds(113, 426, 93, 23);
		painelForm.add(btnLimpar);
		
		campoSexo = new JComboBox();
		campoSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSexo.setEnabled(true);
		campoSexo.setModel(new DefaultComboBoxModel(new String[] {"", "MASCULINO", "FEMININO"}));
		campoSexo.setBounds(282, 102, 115, 20);
		painelForm.add(campoSexo);

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
		campoDatadeNascimento.setText("");
		campoNumero.setText("");
		campoEmail.setText("");
		campoRua.setText("");
		campoUsuario.setText("");
		campoSenha.setText("");
		campoTelefone.setText("");
		campoSexo.setSelectedIndex(0);
		campoClube.setText("");
	}
	
	@SuppressWarnings("deprecation")
	public boolean validarCampos(){
		String nome = campoNome.getText();
		String cpf = campoCpf.getText();
		String dataNascimento = campoDatadeNascimento.getText();
		String sexo = (String)campoSexo.getSelectedItem();
		String telefone = campoTelefone.getText();
		String email = campoEmail.getText();
		String logradouro = campoRua.getText();
		String numero = campoNumero.getText();
		String bairro = campoBairro.getText();
		String cidade = campoCidade.getText();
		String estado = campoEstado.getText();
		String pais = campoPais.getText();
		String clube = campoClube.getText();
		String usuario = campoUsuario.getText();
		String senha = campoSenha.getText();
		DateFormat dataFormatada = new SimpleDateFormat ("dd/MM/yyyy");  
	    dataFormatada.setLenient(false); 
	    try {  
	        dataFormatada.parse(dataNascimento);  
	    } catch (ParseException ex) {  
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoDatadeNascimento.requestFocus();
			}
			return false;
	    }
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
		} else if (dataNascimento.equals("") || dataNascimento.contains(" ")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoDatadeNascimento.requestFocus();
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
		} else if (telefone.equals("(  )         ") || (!telefone.equals("(  )         ") && telefone.replaceAll(" ", "").length() < 12)) {
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
		} else if (logradouro.equals("")) {
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
		@SuppressWarnings("deprecation")
		public void cadastar(){
			if(validarCampos()){
				String nome = campoNome.getText();
				String cpf = campoCpf.getText();
				String dataDeNascimento = campoDatadeNascimento.getText();
				String sexoString = (String)campoSexo.getSelectedItem();
				char sexo = sexoString.charAt(0);
				String telefone = campoTelefone.getText();
				String email = campoEmail.getText();
				String logradouro = campoRua.getText();
				String numero = campoNumero.getText();
				String bairro = campoBairro.getText();
				String cidade = campoCidade.getText();
				String estado = campoEstado.getText();
				String pais = campoPais.getText();
				String clube = campoClube.getText();
				String usuario = campoUsuario.getText();
				String senha = campoSenha.getText();
				try {
					Endereco endereco = new Endereco(logradouro, numero, bairro, cidade, estado, pais);
					fachada.cadastrarApostador(new Apostador(0, nome, cpf, sexo, telefone, email, endereco, dataDeNascimento, usuario, senha, clube));
					try {
						throw new CadastroEfetuadoComSucessoException();
					} catch (CadastroEfetuadoComSucessoException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					}
					limparCampos();
				} catch (NomeVazioException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				} catch (ApostadorJaCadastradoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				} catch (CpfInvalidoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado no sistema ao tentar cadastrar!");
					e.printStackTrace();
				}
			}
		}
}
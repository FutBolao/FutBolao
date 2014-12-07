package br.com.futbolao.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import br.com.futbolao.administrador.Administrador;
import br.com.futbolao.exception.AdministradorJaCadastradoException;
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

import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class AdministradorCadastrar extends JInternalFrame {
	Fachada fachada = null;
	private MascaraCampo mascara = new MascaraCampo();
	private JTextField campoNome;
	private JFormattedTextField campoCpf;
	private JFormattedTextField campoTelefone;
	private JTextField campoEmail;
	private JTextField campoLogradouro;
	private JTextField campoBairro;
	private JTextField campoNumero;
	private JTextField campoUsuario;
	private JFormattedTextField campoDatadeNascimento;
	private JTextField campoCidade;
	private JTextField campoEstado;
	private JTextField campoPais;
	@SuppressWarnings("rawtypes")
	private JComboBox campoSexo;
	private JPasswordField campoSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministradorCadastrar frame = new AdministradorCadastrar();
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
	public AdministradorCadastrar() {
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			try {
				throw new ErroAoInstanciarFachadaException();
			} catch (ErroAoInstanciarFachadaException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			}
		}
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().setBackground(Color.WHITE);
		setTitle("Cadastrar Administrador");
		setClosable(true);
		setBounds(100, 100, 440, 475);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 11, 46, 14);
		getContentPane().add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNome.setBounds(10, 36, 400, 20);
		getContentPane().add(campoNome);
		campoNome.setDocument(new FormataCampoPermiteTudoUpperCase(100));
		campoNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(10, 67, 46, 14);
		getContentPane().add(lblCpf);
		
		campoCpf = new JFormattedTextField();
		campoCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCpf.setColumns(10);
		campoCpf.setBounds(10, 92, 120, 20);
		mascara.getCpf().install(campoCpf);
		getContentPane().add(campoCpf);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefone.setBounds(10, 123, 65, 14);
		getContentPane().add(lblTelefone);
		
		campoTelefone = new JFormattedTextField();
		campoTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoTelefone.setBounds(10, 148, 120, 20);
		getContentPane().add(campoTelefone);
		mascara.getTelefone().install(campoTelefone);
		campoTelefone.setColumns(10);
		
		campoSexo = new JComboBox();
		campoSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSexo.setEnabled(true);
		campoSexo.setModel(new DefaultComboBoxModel(new String[] {"", "MASCULINO", "FEMININO"}));
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
		campoEmail.setDocument(new FormataCampoPermiteTudoUpperCase(50));
		campoEmail.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRua.setBounds(10, 179, 46, 14);
		getContentPane().add(lblRua);
		
		campoLogradouro = new JTextField();
		campoLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoLogradouro.setBounds(10, 204, 350, 20);
		getContentPane().add(campoLogradouro);
		campoLogradouro.setDocument(new FormataCampoPermiteTudoUpperCase(50));
		campoLogradouro.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBairro.setBounds(10, 235, 46, 14);
		getContentPane().add(lblBairro);
		
		campoBairro = new JTextField();
		campoBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoBairro.setBounds(10, 260, 200, 20);
		getContentPane().add(campoBairro);
		campoBairro.setDocument(new FormataCampoPermiteTudoUpperCase(30));
		campoBairro.setColumns(10);
		
		JLabel lblN = new JLabel("N\u00BA:");
		lblN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblN.setBounds(364, 179, 46, 14);
		getContentPane().add(lblN);
		
		campoNumero = new JTextField();
		campoNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNumero.setBounds(364, 204, 46, 20);
		getContentPane().add(campoNumero);
		campoNumero.setDocument(new FormataCampoPermiteTudoUpperCase(6));
		campoNumero.setColumns(10);
		
		JLabel lblUsario = new JLabel("Usuário:");
		lblUsario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsario.setBounds(10, 347, 46, 14);
		getContentPane().add(lblUsario);
		
		campoUsuario = new JTextField();
		campoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoUsuario.setBounds(10, 372, 200, 20);
		getContentPane().add(campoUsuario);
		campoUsuario.setDocument(new FormataCampoPermiteTudoUpperCase(20));
		campoUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setBounds(220, 347, 46, 14);
		getContentPane().add(lblSenha);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDataDeNascimento.setBounds(139, 67, 120, 14);
		getContentPane().add(lblDataDeNascimento);
		
		campoDatadeNascimento = new JFormattedTextField();
		campoDatadeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoDatadeNascimento.setBounds(140, 92, 120, 20);
		getContentPane().add(campoDatadeNascimento);
		mascara.getData().install(campoDatadeNascimento);
		campoDatadeNascimento.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCidade.setBounds(220, 236, 46, 14);
		getContentPane().add(lblCidade);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstado.setBounds(10, 291, 46, 14);
		getContentPane().add(lblEstado);
		
		JLabel lblPais = new JLabel("País:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(220, 291, 46, 14);
		getContentPane().add(lblPais);
		
		campoCidade = new JTextField();
		campoCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCidade.setBounds(220, 260, 190, 20);
		getContentPane().add(campoCidade);
		campoCidade.setDocument(new FormataCampoPermiteTudoUpperCase(30));
		campoCidade.setColumns(10);
		
		campoEstado = new JTextField();
		campoEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEstado.setBounds(10, 316, 200, 20);
		getContentPane().add(campoEstado);
		campoEstado.setDocument(new FormataCampoPermiteTudoUpperCase(20));
		campoEstado.setColumns(10);
		
		campoPais = new JTextField();
		campoPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPais.setBounds(220, 316, 190, 20);
		getContentPane().add(campoPais);
		campoPais.setDocument(new FormataCampoPermiteTudoUpperCase(20));
		campoPais.setColumns(10);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBounds(120, 403, 100, 20);
		getContentPane().add(btnLimpar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrar();
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrar.setBounds(10, 403, 100, 20);
		getContentPane().add(btnCadastrar);
		
		campoSenha = new JPasswordField();
		campoSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSenha.setBounds(220, 373, 190, 20);
		campoSenha.setDocument(new FormataCampoPermiteTudo(50));
		getContentPane().add(campoSenha);

	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void limparCampos(){
		campoNome.setText("");
		campoCpf.setText("");
		campoTelefone.setText("");
		campoEmail.setText("");
		campoLogradouro.setText("");
		campoBairro.setText("");
		campoNumero.setText("");
		campoUsuario.setText("");
		campoDatadeNascimento.setText("");
		campoCidade.setText("");
		campoEstado.setText("");
		campoPais.setText("");
		campoSenha.setText("");
		campoSexo.setSelectedIndex(0);
	}
	
	@SuppressWarnings("deprecation")
	private boolean validaCampos(){
		String nome = campoNome.getText();
		String cpf = campoCpf.getText();
		String telefone = campoTelefone.getText();
		String email = campoEmail.getText();
		String sexo = (String) campoSexo.getSelectedItem();
		String logradouro = campoLogradouro.getText();
		String bairro = campoBairro.getText();
		String numero = campoNumero.getText();
		String usuario = campoUsuario.getText();
		String dataDeNascimento = campoDatadeNascimento.getText();
		String cidade = campoCidade.getText();
		String estado = campoEstado.getText();
		String pais = campoPais.getText();
		String senha = campoSenha.getText();
		DateFormat dataFormatada = new SimpleDateFormat ("dd/MM/yyyy");  
	    dataFormatada.setLenient(false); 
	    try {  
	        dataFormatada.parse(dataDeNascimento);  
	    } catch (ParseException ex) {  
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoDatadeNascimento.requestFocus();
			}
			return false;
	    }
		if(nome.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoNome.requestFocus();
			}
			return false;
		}else if(cpf.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoCpf.requestFocus();
			}
			return false;
		}else if(telefone.equals("(  )         ") || (!telefone.equals("(  )         ") && telefone.replaceAll(" ", "").length() < 12)){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoTelefone.requestFocus();
			}
			return false;
		}else if(email.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoEmail.requestFocus();
			}
			return false;
		}else if(logradouro.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoLogradouro.requestFocus();
			}
			return false;
		}else if(bairro.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoBairro.requestFocus();
			}
			return false;
		}else if(numero.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoNumero.requestFocus();
			}
			return false;
		}else if(usuario.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoUsuario.requestFocus();
			}
			return false;
		} else if (dataDeNascimento.equals("") || dataDeNascimento.contains(" ")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoDatadeNascimento.requestFocus();
			}
			return false;
		}else if(cidade.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoCidade.requestFocus();
			}
			return false;
		}else if(estado.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoEstado.requestFocus();
			}
			return false;
		}else if(pais.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoPais.requestFocus();
			}
			return false;
		}else if(senha.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoSenha.requestFocus();
			}
			return false;
		}else if(sexo.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoSexo.requestFocus();
			}
			return false;
		}else{
			return true;
		}
	}
	
	@SuppressWarnings("deprecation")
	private void cadastrar(){
		if(validaCampos()){
			String nome = campoNome.getText();
			String cpf = campoCpf.getText();
			String telefone = campoTelefone.getText();
			String email = campoEmail.getText();
			String sexoString = (String) campoSexo.getSelectedItem();
			char sexo = sexoString.charAt(0);
			String logradouro = campoLogradouro.getText();
			String bairro = campoBairro.getText();
			String numero = campoNumero.getText();
			String usuario = campoUsuario.getText();
			String dataDeNascimento = campoDatadeNascimento.getText();
			String cidade = campoCidade.getText();
			String estado = campoEstado.getText();
			String pais = campoPais.getText();
			String senha = campoSenha.getText();
			Endereco endereco = new Endereco(logradouro, numero, bairro, cidade, estado, pais);
			try {
				fachada.cadastrarAdministrador(new Administrador(0, nome, cpf, sexo, telefone, email, endereco, dataDeNascimento, usuario, senha, 'S'));
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
			} catch (AdministradorJaCadastradoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (CpfInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado no sistema ao tentar cadastrar!");
			}
		}
	}
}

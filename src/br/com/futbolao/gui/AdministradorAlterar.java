package br.com.futbolao.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import br.com.futbolao.administrador.Administrador;
import br.com.futbolao.exception.AdministradorNaoCadastradoException;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.IdInvalidoException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.util.FormataCampoPermiteTudo;

@SuppressWarnings("serial")
public class AdministradorAlterar extends JInternalFrame {
	private Fachada fachada = null;
	private JTextField campoNome;
	private JTextField campoCpf;
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
	private JTextField CampoId;
	@SuppressWarnings("rawtypes")
	JComboBox campoSexo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministradorAlterar frame = new AdministradorAlterar(0);
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
	@SuppressWarnings({ "rawtypes" })
	public AdministradorAlterar(final long id) {
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
		
		campoCpf = new JTextField();
		campoCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCpf.setColumns(10);
		campoCpf.setBounds(10, 92, 120, 20);
		getContentPane().add(campoCpf);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefone.setBounds(10, 123, 65, 14);
		getContentPane().add(lblTelefone);
		
		campoTelefone = new JTextField();
		campoTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoTelefone.setBounds(10, 148, 120, 20);
		getContentPane().add(campoTelefone);
		campoTelefone.setColumns(10);
		
		campoSexo = new JComboBox();
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
		campoSenha.setDocument(new FormataCampoPermiteTudo(50));
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
		
		CampoId = new JTextField();
		CampoId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CampoId.setBounds(10, 36, 65, 20);
		getContentPane().add(CampoId);
		CampoId.setColumns(10);
	
		// executa método para preencher os campos.
		preencheCampos(id);
	}

	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private boolean validaCampos(){
		String nome = campoNome.getText();
		String cpf = campoCpf.getText();
		String telefone = campoTelefone.getText();
		String email = campoEmail.getText();
		String sexo = (String) campoSexo.getSelectedItem();
		String logradouro = campoRua.getText();
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
				campoRua.requestFocus();
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
	
	private void preencheCampos(long id) {
		try {
			Administrador administrador = fachada.procurarAdministradorPorId(id);
			CampoId.setText(String.valueOf(administrador.getId()));
			campoNome.setText(administrador.getNome());
			campoCpf.setText(administrador.getCpf());
			campoDatadeNascimento.setText(administrador.getDataDeNascimento().substring(8, 10));
		} catch (IdInvalidoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (AdministradorNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao preencher os campos!");
		}
	}
	
}

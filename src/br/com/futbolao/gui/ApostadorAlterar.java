package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import br.com.futbolao.apostador.Apostador;
import br.com.futbolao.exception.AlteracaoEfetuadaComSucessoException;
import br.com.futbolao.exception.ApostadorNaoCadastradoException;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.CpfInvalidoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.IdInvalidoException;
import br.com.futbolao.exception.NomeVazioException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.util.Endereco;
import br.com.futbolao.util.FormataCampoPermiteApenasLetrasNumeros;
import br.com.futbolao.util.FormataCampoPermiteTudo;
import br.com.futbolao.util.FormataCampoPermiteTudoUpperCase;
import br.com.futbolao.util.MascaraCampo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ApostadorAlterar extends JInternalFrame {
	private Fachada fachada = null;
	private MascaraCampo mascara = new MascaraCampo();
	private JTextField campoNome;
	private JFormattedTextField campoCpf;
	private JFormattedTextField campoDatadeNascimento;
	private JFormattedTextField campoTelefone;
	private JTextField campoEmail;
	private JTextField campoRua;
	private JTextField campoNumero;
	private JTextField campoBairro;
	private JTextField campoCidade;
	private JTextField campoEstado;
	private JTextField campoPais;
	private JTextField campoClube;
	private JTextField campoUsuario;
	private JPasswordField campoSenha;
	@SuppressWarnings("rawtypes")
	private JComboBox campoSexo;
	private JTextField campoID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApostadorAlterar frame = new ApostadorAlterar(0);
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
	public ApostadorAlterar(final long id) {
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			try {
				throw new ErroAoInstanciarFachadaException();
			} catch (ErroAoInstanciarFachadaException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			}
		}
		setTitle("Alterar Apostador");
		setClosable(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 444, 525);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setLayout(null);
		painelForm.setForeground(Color.BLACK);
		painelForm.setBackground(Color.WHITE);
		painelForm.setBounds(10, 11, 407, 464);
		getContentPane().add(painelForm);
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(100, 12, 51, 23);
		painelForm.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNome.setColumns(10);
		campoNome.setBounds(100, 45, 297, 20);
		campoNome.setDocument(new FormataCampoPermiteTudoUpperCase(100));
		painelForm.add(campoNome);
		
		JLabel lblCPF = new JLabel("CPF :");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCPF.setBounds(10, 76, 46, 14);
		painelForm.add(lblCPF);
		
		campoCpf = new JFormattedTextField();
		campoCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCpf.setColumns(10);
		campoCpf.setBounds(10, 101, 121, 20);
		mascara.getCpf().install(campoCpf);
		painelForm.add(campoCpf);
		
		JLabel lblDataNasc = new JLabel("Data de Nascimento :");
		lblDataNasc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDataNasc.setBounds(141, 74, 121, 19);
		painelForm.add(lblDataNasc);
		
		campoDatadeNascimento = new JFormattedTextField();
		campoDatadeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoDatadeNascimento.setColumns(10);
		campoDatadeNascimento.setBounds(141, 101, 131, 20);
		mascara.getData().install(campoDatadeNascimento);
		painelForm.add(campoDatadeNascimento);
		
		JLabel lblSexo = new JLabel("Sexo :");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(282, 76, 46, 14);
		painelForm.add(lblSexo);
		
		campoSexo = new JComboBox();
		campoSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSexo.setModel(new DefaultComboBoxModel(new String[] {"", "MASCULINO", "FEMININO"}));
		campoSexo.setBounds(282, 101, 113, 20);
		painelForm.add(campoSexo);
		
		JLabel lblTelefone = new JLabel("Telefone :");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefone.setBounds(10, 132, 58, 14);
		painelForm.add(lblTelefone);
		
		campoTelefone = new JFormattedTextField();
		campoTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoTelefone.setColumns(10);
		campoTelefone.setBounds(10, 160, 121, 20);
		mascara.getTelefone().install(campoTelefone);
		painelForm.add(campoTelefone);
		
		JLabel lblEmail = new JLabel("Email : ");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(141, 132, 46, 14);
		painelForm.add(lblEmail);
		
		campoEmail = new JTextField();
		campoEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEmail.setColumns(10);
		campoEmail.setBounds(141, 160, 254, 20);
		campoEmail.setDocument(new FormataCampoPermiteTudoUpperCase(50));
		painelForm.add(campoEmail);
		
		JLabel lblRua = new JLabel("Rua : ");
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRua.setBounds(10, 191, 67, 20);
		painelForm.add(lblRua);
		
		campoRua = new JTextField();
		campoRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoRua.setColumns(10);
		campoRua.setBounds(10, 222, 332, 20);
		campoRua.setDocument(new FormataCampoPermiteTudoUpperCase(50));
		painelForm.add(campoRua);
		
		JLabel lblNumero = new JLabel("N\u00BA:");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumero.setBounds(344, 194, 51, 14);
		painelForm.add(lblNumero);
		
		campoNumero = new JTextField();
		campoNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNumero.setColumns(10);
		campoNumero.setBounds(344, 222, 51, 20);
		campoNumero.setDocument(new FormataCampoPermiteTudoUpperCase(6));
		painelForm.add(campoNumero);
		
		JLabel lblBairro = new JLabel("Bairro :");
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBairro.setBounds(10, 253, 51, 14);
		painelForm.add(lblBairro);
		
		campoBairro = new JTextField();
		campoBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoBairro.setColumns(10);
		campoBairro.setBounds(10, 278, 190, 20);
		campoBairro.setDocument(new FormataCampoPermiteTudoUpperCase(30));
		painelForm.add(campoBairro);
		
		JLabel lblCidade = new JLabel("Cidade : ");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCidade.setBounds(210, 253, 51, 14);
		painelForm.add(lblCidade);
		
		campoCidade = new JTextField();
		campoCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCidade.setColumns(10);
		campoCidade.setBounds(210, 278, 185, 20);
		campoCidade.setDocument(new FormataCampoPermiteTudoUpperCase(30));
		painelForm.add(campoCidade);
		
		JLabel lblEstado = new JLabel("Estado :");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstado.setBounds(10, 309, 67, 14);
		painelForm.add(lblEstado);
		
		campoEstado = new JTextField();
		campoEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEstado.setColumns(10);
		campoEstado.setBounds(10, 334, 190, 20);
		campoEstado.setDocument(new FormataCampoPermiteTudoUpperCase(20));
		painelForm.add(campoEstado);
		
		JLabel lblPais = new JLabel("Pa\u00EDs : ");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(210, 307, 46, 18);
		painelForm.add(lblPais);
		
		campoPais = new JTextField();
		campoPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPais.setColumns(10);
		campoPais.setBounds(210, 334, 185, 20);
		campoPais.setDocument(new FormataCampoPermiteTudoUpperCase(20));
		painelForm.add(campoPais);
		
		JLabel lblClube = new JLabel("Clube :");
		lblClube.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblClube.setBounds(10, 365, 51, 14);
		painelForm.add(lblClube);
		
		campoClube = new JTextField();
		campoClube.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoClube.setColumns(10);
		campoClube.setBounds(10, 390, 121, 20);
		campoClube.setDocument(new FormataCampoPermiteTudoUpperCase(30));
		painelForm.add(campoClube);
		
		JLabel lblUsuario = new JLabel("Usuario : ");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsuario.setBounds(141, 365, 51, 15);
		painelForm.add(lblUsuario);
		
		campoUsuario = new JTextField();
		campoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoUsuario.setColumns(10);
		campoUsuario.setBounds(141, 389, 121, 20);
		campoUsuario.setDocument(new FormataCampoPermiteApenasLetrasNumeros(20));
		painelForm.add(campoUsuario);
		
		JLabel lblSenha = new JLabel("Senha : ");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setBounds(272, 365, 46, 14);
		painelForm.add(lblSenha);
		
		campoSenha = new JPasswordField();
		campoSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSenha.setColumns(10);
		campoSenha.setBounds(272, 389, 121, 20);
		campoSenha.setDocument(new FormataCampoPermiteTudo(50));
		painelForm.add(campoSenha);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar(id);
			}
		});
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 426, 93, 23);
		painelForm.add(btnAlterar);
		
		JLabel lblId = new JLabel("ID : ");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(10, 16, 46, 14);
		painelForm.add(lblId);
		
		campoID = new JTextField();
		campoID.setEditable(false);
		campoID.setFont(new Font("Tahoma", Font.BOLD, 12));
		campoID.setBounds(10, 46, 67, 20);
		painelForm.add(campoID);
		campoID.setColumns(10);
		
		preencheCampos(id);

	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	@SuppressWarnings("deprecation")
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
		String clube = campoClube.getText();
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
		}else if(clube.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoClube.requestFocus();
			}
			return false;
		}else{
			return true;
		}
	}
	
	private void preencheCampos(long id) {
		try {
			Apostador apostador = fachada.procurarApostadorPorId(id);
			campoID.setText(String.valueOf(apostador.getId()));
			campoNome.setText(apostador.getNome());
			campoCpf.setText(apostador.getCpf());
			campoDatadeNascimento.setText(apostador.getDataDeNascimento().substring(8, 10) + apostador.getDataDeNascimento().substring(5, 7)
										+ apostador.getDataDeNascimento().substring(0, 4));
			String sexo;
			if(apostador.getSexo() == 'M'){
				sexo = "MASCULINO";
			}else{
				sexo = "FEMININO";
			}
			campoSexo.setSelectedItem(sexo);
			campoTelefone.setText(apostador.getTelefone().replace(" ", ""));
			campoEmail.setText(apostador.getEmail());
			campoNumero.setText(apostador.getEndereco().getNumero());
			campoRua.setText(apostador.getEndereco().getLogradouro());
			campoCidade.setText(apostador.getEndereco().getCidade());
			campoEstado.setText(apostador.getEndereco().getEstado());
			campoPais.setText(apostador.getEndereco().getPais());
			campoUsuario.setText(apostador.getUsuario());
			campoSenha.setText(apostador.getSenha());
			campoClube.setText(apostador.getClube());
			campoBairro.setText(apostador.getEndereco().getBairro());
		} catch (IdInvalidoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (ApostadorNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao preencher os campos!");
		}
	}
	
	@SuppressWarnings("deprecation")
	private void alterar(long id){
		if(validaCampos()){
			String nome = campoNome.getText();
			String cpf = campoCpf.getText();
			String telefone = campoTelefone.getText();
			String email = campoEmail.getText();
			String sexoString = (String) campoSexo.getSelectedItem();
			char sexo = sexoString.charAt(0);
			String logradouro = campoRua.getText();
			String bairro = campoBairro.getText();
			String numero = campoNumero.getText();
			String usuario = campoUsuario.getText();
			String dataDeNascimento = campoDatadeNascimento.getText();
			String cidade = campoCidade.getText();
			String estado = campoEstado.getText();
			String pais = campoPais.getText();
			String senha = campoSenha.getText();
			String clube = campoClube.getText();
			try {
				Endereco endereco = new Endereco(logradouro, numero, bairro, cidade, estado, pais);
				fachada.atualizaApostador(new Apostador(id, nome, cpf, sexo, telefone, email, endereco, dataDeNascimento, usuario, senha, clube));
				try {
					throw new AlteracaoEfetuadaComSucessoException();
				} catch (AlteracaoEfetuadaComSucessoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				this.dispose();
			} catch (NomeVazioException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (CpfInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				e.printStackTrace();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (ApostadorNaoCadastradoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao alterar o apostador!");
				try {
					throw new AlteracaoEfetuadaComSucessoException();
				} catch (AlteracaoEfetuadaComSucessoException ex) {
					JOptionPane.showMessageDialog(rootPane, ex.getMessage());
				}
				this.dispose();
			}
		}
		
	}
}

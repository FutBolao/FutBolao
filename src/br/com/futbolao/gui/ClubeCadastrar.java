package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;

import br.com.futbolao.clube.Clube;
import br.com.futbolao.exception.CadastroEfetuadoComSucessoException;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.ClubeJaCadastradoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.NomeVazioException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.util.FormataCampoPermiteTudo;

import java.awt.Color;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ClubeCadastrar extends JInternalFrame {
	private Fachada fachada = null;
	private JTextField campoNomeCompleto;
	private JTextField campoNome;
	private JTextField campoSigla;
	private JTextField campoEstado;
	private JTextField campoPais;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClubeCadastrar frame = new ClubeCadastrar();
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
	public ClubeCadastrar() {
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
		setTitle("Cadastrar Clube");
		setClosable(true);
		setBounds(100, 100, 450, 264);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setBackground(Color.WHITE);
		painelForm.setBounds(10, 11, 414, 214);
		getContentPane().add(painelForm);
		painelForm.setLayout(null);
		
		JLabel lblNomeCompleto = new JLabel("Nome Completo:");
		lblNomeCompleto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNomeCompleto.setBounds(10, 11, 92, 14);
		painelForm.add(lblNomeCompleto);
		
		campoNomeCompleto = new JTextField();
		campoNomeCompleto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNomeCompleto.setBounds(10, 36, 394, 20);
		painelForm.add(campoNomeCompleto);
		campoNomeCompleto.setColumns(10);
		campoNomeCompleto.setDocument(new FormataCampoPermiteTudo(60));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 67, 92, 14);
		painelForm.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNome.setColumns(10);
		campoNome.setBounds(10, 92, 306, 20);
		campoNome.setDocument(new FormataCampoPermiteTudo(30));
		painelForm.add(campoNome);
		
		JLabel lblSigla = new JLabel("Sigla:");
		lblSigla.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSigla.setBounds(326, 67, 28, 14);
		painelForm.add(lblSigla);
		
		campoSigla = new JTextField();
		campoSigla.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSigla.setColumns(10);
		campoSigla.setBounds(326, 92, 78, 20);
		campoSigla.setDocument(new FormataCampoPermiteTudo(4));
		painelForm.add(campoSigla);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstado.setBounds(10, 123, 41, 14);
		painelForm.add(lblEstado);
		
		JLabel lblPais = new JLabel("Pa\u00EDs:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(208, 123, 28, 14);
		painelForm.add(lblPais);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrar();
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrar.setBounds(10, 179, 89, 23);
		painelForm.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBounds(109, 179, 89, 23);
		painelForm.add(btnLimpar);
		
		campoEstado = new JTextField();
		campoEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEstado.setColumns(10);
		campoEstado.setBounds(10, 148, 188, 20);
		campoEstado.setDocument(new FormataCampoPermiteTudo(20));
		painelForm.add(campoEstado);
		
		campoPais = new JTextField();
		campoPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPais.setColumns(10);
		campoPais.setBounds(208, 148, 196, 20);
		campoPais.setDocument(new FormataCampoPermiteTudo(20));
		painelForm.add(campoPais);

	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void limparCampos(){
		campoNome.setText("");
		campoNomeCompleto.setText("");
		campoSigla.setText("");
		campoEstado.setText("");
		campoPais.setText("");
	}
	
	private boolean validaCampos(){
		String nome = campoNome.getText();
		String nomeCompleto = campoNomeCompleto.getText();
		String sigla = campoSigla.getText();
		String estado = campoEstado.getText();
		String pais = campoPais.getText();
		if(nome.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoNome.requestFocus();
			}
			return false;
		}else if(nomeCompleto.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoNomeCompleto.requestFocus();
			}
			return false;
		}else if(sigla.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoSigla.requestFocus();
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
		}else{
			return true;
		}
	}
	
	private void cadastrar(){
		if(validaCampos()){
			String nome = campoNome.getText();
			String nomeCompleto = campoNomeCompleto.getText();
			String sigla = campoSigla.getText();
			String estado = campoEstado.getText();
			String pais = campoPais.getText();
			try {
				fachada.cadastrarClube(new Clube(0, nome, nomeCompleto, sigla, 'S', estado, pais));
				limparCampos();
				try {
					throw new CadastroEfetuadoComSucessoException();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
			} catch (NomeVazioException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (ClubeJaCadastradoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado no sistema ao tentar cadastrar!");
			}
		}
	}
}

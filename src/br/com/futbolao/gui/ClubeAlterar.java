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
import br.com.futbolao.exception.AlteracaoEfetuadaComSucessoException;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.ClubeNaoCadastradoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.NomeVazioException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.util.FormataCampoPermiteTudoUpperCase;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class ClubeAlterar extends JInternalFrame {
	private Fachada fachada = null;
	private JTextField campoNomeCompleto;
	private JTextField campoNome;
	private JTextField campoSigla;
	private JTextField campoId;
	JButton btnCadastrar;
	private JTextField campoEstado;
	private JTextField campoPais;
	private JCheckBox campoAtivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClubeAlterar frame = new ClubeAlterar(0);
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
	public ClubeAlterar(final int id) {
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
		campoNomeCompleto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNomeCompleto.setBounds(90, 36, 314, 20);
		painelForm.add(campoNomeCompleto);
		campoNomeCompleto.setDocument(new FormataCampoPermiteTudoUpperCase(60));
		campoNomeCompleto.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 67, 92, 14);
		painelForm.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNome.setColumns(10);
		campoNome.setBounds(10, 92, 306, 20);
		campoNome.setDocument(new FormataCampoPermiteTudoUpperCase(30));
		painelForm.add(campoNome);
		
		JLabel lblSigla = new JLabel("Sigla:");
		lblSigla.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSigla.setBounds(326, 67, 28, 14);
		painelForm.add(lblSigla);
		
		campoSigla = new JTextField();
		campoSigla.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSigla.setColumns(10);
		campoSigla.setBounds(326, 92, 78, 20);
		campoSigla.setDocument(new FormataCampoPermiteTudoUpperCase(4));
		painelForm.add(campoSigla);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstado.setBounds(10, 123, 41, 14);
		painelForm.add(lblEstado);
		
		JLabel lblPais = new JLabel("Pa\u00EDs:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(208, 123, 24, 14);
		painelForm.add(lblPais);
		
		btnCadastrar = new JButton("Alterar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterar(id);
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrar.setBounds(10, 179, 89, 23);
		painelForm.add(btnCadastrar);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(10, 11, 46, 14);
		painelForm.add(lblId);
		
		campoId = new JTextField();
		campoId.setFont(new Font("Tahoma", Font.BOLD, 12));
		campoId.setEditable(false);
		campoId.setBounds(10, 36, 70, 20);
		painelForm.add(campoId);
		campoId.setColumns(10);
		
		campoEstado = new JTextField();
		campoEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEstado.setColumns(10);
		campoEstado.setBounds(10, 148, 188, 20);
		campoEstado.setDocument(new FormataCampoPermiteTudoUpperCase(20));
		painelForm.add(campoEstado);
		
		campoPais = new JTextField();
		campoPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPais.setColumns(10);
		campoPais.setBounds(208, 148, 196, 20);
		campoPais.setDocument(new FormataCampoPermiteTudoUpperCase(20));
		painelForm.add(campoPais);
		
		campoAtivo = new JCheckBox("Ativo");
		campoAtivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoAtivo.setBounds(345, 180, 59, 23);
		painelForm.add(campoAtivo);
		
		// executa método para preencher os campos.
		preencheCampos(id);
	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
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
	
	private void preencheCampos(int id){
		try {
			Clube clube = fachada.procurarClubePorId(id);
			campoId.setText(String.valueOf(clube.getId()));
			campoNome.setText(clube.getNome());
			campoNomeCompleto.setText(clube.getNomeCompleto());
			campoSigla.setText(clube.getSigla());
			campoEstado.setText(clube.getEstado());
			campoPais.setText(clube.getPais());
			if(clube.getAtivo() == 'S'){
				campoAtivo.setSelected(true);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (ClubeNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao preencher os campos!");
		}
	}
	
	private void alterar(int id){
		if(validaCampos()){
			String nome = campoNome.getText();
			String nomeCompleto = campoNomeCompleto.getText();
			String sigla = campoSigla.getText();
			String estado = campoEstado.getText();
			String pais = campoPais.getText();
			char ativo = 'S';
			if (campoAtivo.isSelected() == false){
				ativo = 'N';
			}
			try {
				fachada.atualizaClube(new Clube(id, nome, nomeCompleto, sigla, ativo, estado, pais));
				try {
					throw new AlteracaoEfetuadaComSucessoException();
				} catch (AlteracaoEfetuadaComSucessoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				this.dispose();
			} catch (NomeVazioException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (ClubeNaoCadastradoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao alterar o clube!");
			}
		}
	}
}

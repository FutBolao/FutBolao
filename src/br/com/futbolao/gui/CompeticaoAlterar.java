package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import br.com.futbolao.competicao.Competicao;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.fachada.Fachada;

public class CompeticaoAlterar extends JInternalFrame {
	private Fachada fachada = null;
	private JTextField campoNome;
	private JTextField campoQntdeRodadas;
	private JTextField campoID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompeticaoAlterar frame = new CompeticaoAlterar(0);
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
	public CompeticaoAlterar(int id) {
		setTitle("Alterar Competição");
		setClosable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setBackground(Color.WHITE);
		painelForm.setBounds(10, 11, 364, 228);
		getContentPane().add(painelForm);
		painelForm.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 67, 46, 14);
		painelForm.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNome.setBounds(10, 92, 344, 20);
		painelForm.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel lblQuantidadeDeCompeties = new JLabel("Quantidade de Competições:");
		lblQuantidadeDeCompeties.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantidadeDeCompeties.setBounds(10, 123, 170, 14);
		painelForm.add(lblQuantidadeDeCompeties);
		
		campoQntdeRodadas = new JTextField();
		campoQntdeRodadas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoQntdeRodadas.setBounds(10, 148, 344, 20);
		painelForm.add(campoQntdeRodadas);
		campoQntdeRodadas.setColumns(10);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 197, 89, 20);
		painelForm.add(btnAlterar);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(10, 11, 46, 14);
		painelForm.add(lblId);
		
		campoID = new JTextField();
		campoID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoID.setBounds(10, 36, 86, 20);
		painelForm.add(campoID);
		campoID.setColumns(10);
		setBounds(100, 100, 400, 280);
		
		//preencheCampos(id);

	}

	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private boolean validaCampos(){
		String nome = campoNome.getText();
		String qtdRodadas = campoQntdeRodadas.getText();
		if(nome.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoNome.requestFocus();
			}
			return false;
		}else if(qtdRodadas.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoQntdeRodadas.requestFocus();
			}
			return false;
		}else{
			return true;
		}
	}

	
	
}

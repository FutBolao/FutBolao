package br.com.futbolao.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.futbolao.administrador.Administrador;
import br.com.futbolao.exception.AdministradorNaoCadastradoException;
import br.com.futbolao.exception.CadastroEfetuadoComSucessoException;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.CpfInvalidoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.IdInvalidoException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.movimentacao.financeira.administrador.MovimentacaoFinanceiraAdministrador;
import br.com.futbolao.util.JMoneyField;

public class AdministradorMovimentacaoFinanceiraCadastrar extends
		JInternalFrame {
	
	Fachada fachada = null;
	private JTextField campoAdministrador;
	private JTextField campoIdAdministrador;
	private JMoneyField campoValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministradorMovimentacaoFinanceiraCadastrar frame = new AdministradorMovimentacaoFinanceiraCadastrar();
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
	public AdministradorMovimentacaoFinanceiraCadastrar() {
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			try {
				throw new ErroAoInstanciarFachadaException();
			} catch (ErroAoInstanciarFachadaException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
				e.printStackTrace();
			}
		}
		getContentPane().setBackground(Color.WHITE);
		setTitle("Movimentação Financeira Adminitrador");
		setClosable(true);
		setBounds(100, 100, 478, 242);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setBackground(Color.WHITE);
		painelForm.setBounds(10, 11, 442, 193);
		getContentPane().add(painelForm);
		painelForm.setLayout(null);
			
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrar();
			}
		});
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConfirmar.setBounds(10, 158, 89, 23);
		painelForm.add(btnConfirmar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaCampos();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBounds(135, 158, 89, 23);
		painelForm.add(btnLimpar);
		
		
		JLabel lblIdAdministrador = new JLabel("ID do Administrador : ");
		lblIdAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdAdministrador.setBounds(10, 36, 120, 14);
		painelForm.add(lblIdAdministrador);
		
		campoIdAdministrador = new JTextField();
		campoIdAdministrador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				if (tecla.getKeyCode() == KeyEvent.VK_ENTER && !campoIdAdministrador.getText().equals("0")) {
					procurar();
				}
			}
		});
		campoIdAdministrador.setBounds(10, 61, 89, 20);
		painelForm.add(campoIdAdministrador);
		campoIdAdministrador.setColumns(10);
		
		JLabel lblAdministrador = new JLabel("Administrador : ");
		lblAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdministrador.setBounds(151, 36, 102, 14);
		painelForm.add(lblAdministrador);
		
		campoAdministrador = new JTextField();
		campoAdministrador.setEditable(false);
		campoAdministrador.setBounds(152, 61, 281, 20);
		painelForm.add(campoAdministrador);
		campoAdministrador.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor : ");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValor.setBounds(10, 92, 46, 14);
		painelForm.add(lblValor);
		
		campoValor = new JMoneyField();
		campoValor.setBounds(10, 117, 86, 20);
		painelForm.add(campoValor);
		campoValor.setColumns(10);
		
		JLabel lblDigiteOId = new JLabel("Digite o ID do administrador e depois tecle enter para verificar se existe.");
		lblDigiteOId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDigiteOId.setBounds(10, 11, 423, 14);
		painelForm.add(lblDigiteOId);


	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void habilitaCampos(){
		campoIdAdministrador.disable();
		campoValor.enable();
	}
	
	private void limpaCampos(){
		campoIdAdministrador.enable();
		campoValor.disable();
		campoIdAdministrador.setText("");
		campoAdministrador.setText("");
		campoValor.setText("");
		campoIdAdministrador.requestFocus();
	}
	
	private boolean validaCampos(){
		String valor = campoValor.getText();
		if(valor.equals("0,00")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoValor.requestFocus();
			}
			return false;
		}else{
			return true;
		}
	}
	
	private void procurar(){
		long id = Long.parseLong(campoIdAdministrador.getText());				
		try {
			Administrador administrador = fachada.procurarAdministradorPorId(id);
			campoAdministrador.setText(administrador.getNome());
			habilitaCampos();
		} catch (CpfInvalidoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (AdministradorNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar procurar o adminitrador!");
		}
	 
	}
	
	private void cadastrar(){
		if(validaCampos()){
			long idAdministrador = Long.parseLong(campoIdAdministrador.getText());
			Double valor = Double.parseDouble(campoValor.getText());
			try {
				fachada.cadastrarMovimentacaoFinanceiraAdministrador(new MovimentacaoFinanceiraAdministrador(0, idAdministrador, "SAQUE", valor));
				try {
					throw new CadastroEfetuadoComSucessoException();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				limpaCampos();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar cadastrar a movimentação financeira!");
			}
			
		}
	}
}

package br.com.futbolao.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
			}
		}
		getContentPane().setBackground(Color.WHITE);
		setTitle("Movimenta\u00E7\u00E3o Financeira Adminitrador");
		setClosable(true);
		setBounds(100, 100, 393, 298);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setBackground(Color.WHITE);
		painelForm.setBounds(10, 11, 357, 246);
		getContentPane().add(painelForm);
		painelForm.setLayout(null);
			
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrar();
			}
		});
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConfirmar.setBounds(10, 212, 89, 23);
		painelForm.add(btnConfirmar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBounds(134, 212, 89, 23);
		painelForm.add(btnLimpar);
		
		
		JLabel lblIdAdministrador = new JLabel("ID do Administrador : ");
		lblIdAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdAdministrador.setBounds(10, 11, 120, 14);
		painelForm.add(lblIdAdministrador);
		
		campoIdAdministrador = new JTextField();
		campoIdAdministrador.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent focus) {
				procurar();
			}
		});
		campoIdAdministrador.setBounds(10, 36, 89, 20);
		painelForm.add(campoIdAdministrador);
		campoIdAdministrador.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					procurar();
			}
		});
		btnProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProcurar.setBounds(134, 34, 89, 23);
		painelForm.add(btnProcurar);
		
		JLabel lblAdministrador = new JLabel("Administrador : ");
		lblAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdministrador.setBounds(10, 67, 102, 14);
		painelForm.add(lblAdministrador);
		
		campoAdministrador = new JTextField();
		campoAdministrador.setEditable(false);
		campoAdministrador.setBounds(10, 92, 337, 20);
		painelForm.add(campoAdministrador);
		campoAdministrador.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor : ");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValor.setBounds(10, 132, 46, 14);
		painelForm.add(lblValor);
		
		campoValor = new JMoneyField();
		campoValor.setBounds(10, 157, 86, 20);
		painelForm.add(campoValor);
		campoValor.setColumns(10);


	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void limparCampos(){
		campoAdministrador.setText("");
		campoIdAdministrador.setText("");
		campoValor.setText("0,00");
	}
	
	private boolean validaCampos(){
		String id = campoIdAdministrador.getText();
		String valor = campoValor.getText();
		if(id.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoIdAdministrador.requestFocus();
			}
			return false;
		}else if(valor.equals("0,00")){
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
		if(id != 0){		
		try {
			Administrador administrador = fachada.procurarAdministradorPorId(id);
			campoAdministrador.setText(administrador.getNome());
		} catch (CpfInvalidoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (AdministradorNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar procurar o adminitrador!");
		}
	  }else{		 
			try {
				throw new IdInvalidoException();
			} catch (IdInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			}		
	  }
	}
	
	private void cadastrar(){
		if(validaCampos()){
			long idAdministrador = Long.parseLong(campoIdAdministrador.getText());
			double valor = Double.parseDouble(campoValor.getText());
			try {
				fachada.cadastrarMovimentacaoFinanceiraAdministrador(new MovimentacaoFinanceiraAdministrador(0, idAdministrador, "SAQUE", valor));
				limparCampos();
				try {
					throw new CadastroEfetuadoComSucessoException();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar cadastrar a movimentação financeira!");
			}
			
		}
	}

}

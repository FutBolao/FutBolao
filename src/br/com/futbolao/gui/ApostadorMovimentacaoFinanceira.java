package br.com.futbolao.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.util.JMoneyField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ApostadorMovimentacaoFinanceira extends JInternalFrame {
	Fachada fachada = null;
	private JTextField campoApostador;
	private JMoneyField campoValor;
	private JComboBox campoTipoMovimentacao;
	private JTextField campoIdApostador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApostadorMovimentacaoFinanceira frame = new ApostadorMovimentacaoFinanceira();
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
	public ApostadorMovimentacaoFinanceira() {
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
		setTitle("Movimenta\u00E7\u00E3o Financeira Apostador");
		setClosable(true);
		setBounds(100, 100, 454, 298);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setBackground(Color.WHITE);
		painelForm.setBounds(10, 11, 417, 246);
		getContentPane().add(painelForm);
		painelForm.setLayout(null);
		
		JLabel lblApostador = new JLabel("Apostador : ");
		lblApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApostador.setBounds(10, 69, 76, 14);
		painelForm.add(lblApostador);
		
		campoApostador = new JTextField();
		campoApostador.setBounds(10, 94, 293, 20);
		painelForm.add(campoApostador);
		campoApostador.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProcurar.setBounds(318, 92, 89, 23);
		painelForm.add(btnProcurar);
		
		JLabel lblTipoDaMovimentao = new JLabel("Tipo Da Movimentação : ");
		lblTipoDaMovimentao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipoDaMovimentao.setBounds(10, 132, 145, 14);
		painelForm.add(lblTipoDaMovimentao);
		
		campoTipoMovimentacao = new JComboBox();
		campoTipoMovimentacao.setBounds(10, 157, 134, 20);
		campoTipoMovimentacao.setModel(new DefaultComboBoxModel(new String[] {"", "CREDITO", "SAQUE"}));
		painelForm.add(campoTipoMovimentacao);
		
		JLabel lblNewLabel = new JLabel("Valor : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(208, 132, 46, 14);
		painelForm.add(lblNewLabel);
		
		campoValor = new JMoneyField();
		campoValor.setBounds(208, 157, 86, 20);
		painelForm.add(campoValor);
		campoValor.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
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
		
		JLabel lblIdDoApostador = new JLabel("ID do Apostador : ");
		lblIdDoApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdDoApostador.setBounds(10, 11, 106, 14);
		painelForm.add(lblIdDoApostador);
		
		campoIdApostador = new JTextField();
		campoIdApostador.setEditable(false);
		campoIdApostador.setBounds(13, 38, 86, 20);
		painelForm.add(campoIdApostador);
		campoIdApostador.setColumns(10);

	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void limparCampos(){
		campoApostador.setText("");
		campoIdApostador.setText("");
		campoTipoMovimentacao.setSelectedIndex(0);
		campoValor.setText("0,00");
	}
	
	private boolean validaCampos(){
		String apostador = campoApostador.getText();
		String tipoMovimentacao = (String) campoTipoMovimentacao.getSelectedItem();
		String valor = campoValor.getText();
		if(apostador.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoApostador.requestFocus();
			}
			return false;
		}else if(tipoMovimentacao.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoTipoMovimentacao.requestFocus();
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
	
	
	
	
}

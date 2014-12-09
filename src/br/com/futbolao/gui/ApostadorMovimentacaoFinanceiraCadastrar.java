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

import br.com.futbolao.apostador.Apostador;
import br.com.futbolao.exception.ApostadorNaoCadastradoException;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.CpfInvalidoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.movimentacao.financeira.apostador.MovimentacaoFinanceiraApostador;
import br.com.futbolao.util.FormataCampoApenasNumeros;
import br.com.futbolao.util.JMoneyField;
import br.com.futbolao.exception.CadastroEfetuadoComSucessoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class ApostadorMovimentacaoFinanceiraCadastrar extends JInternalFrame {
	Fachada fachada = null;
	private JTextField campoApostador;
	private JMoneyField campoValor;
	@SuppressWarnings("rawtypes")
	private JComboBox campoTipoMovimentacao;
	private JTextField campoIdApostador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApostadorMovimentacaoFinanceiraCadastrar frame = new ApostadorMovimentacaoFinanceiraCadastrar();
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
	public ApostadorMovimentacaoFinanceiraCadastrar() {
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
		setTitle("Movimentação Financeira Apostador");
		setClosable(true);
		setBounds(100, 100, 466, 233);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setBackground(Color.WHITE);
		painelForm.setBounds(10, 11, 430, 147);
		getContentPane().add(painelForm);
		painelForm.setLayout(null);
		
		JLabel lblApostador = new JLabel("Apostador : ");
		lblApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApostador.setBounds(122, 36, 76, 14);
		painelForm.add(lblApostador);
		
		campoApostador = new JTextField();
		campoApostador.setEnabled(false);
		campoApostador.setBounds(122, 61, 298, 20);
		painelForm.add(campoApostador);
		campoApostador.setColumns(10);
		
		JLabel lblTipoDaMovimentao = new JLabel("Tipo Da Movimentação : ");
		lblTipoDaMovimentao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipoDaMovimentao.setBounds(10, 92, 145, 14);
		painelForm.add(lblTipoDaMovimentao);
		
		campoTipoMovimentacao = new JComboBox();
		campoTipoMovimentacao.setEnabled(false);
		campoTipoMovimentacao.setBounds(10, 117, 285, 20);
		campoTipoMovimentacao.setModel(new DefaultComboBoxModel(new String[] {"", "CREDITO", "SAQUE"}));
		painelForm.add(campoTipoMovimentacao);
		
		JLabel lblNewLabel = new JLabel("Valor : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(305, 92, 46, 14);
		painelForm.add(lblNewLabel);
		
		campoValor = new JMoneyField();
		campoValor.setEnabled(false);
		campoValor.setBounds(305, 117, 115, 20);
		painelForm.add(campoValor);
		campoValor.setColumns(10);
		
		JLabel lblIdDoApostador = new JLabel("ID do Apostador : ");
		lblIdDoApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdDoApostador.setBounds(10, 36, 102, 14);
		painelForm.add(lblIdDoApostador);
		
		campoIdApostador = new JTextField();
		campoIdApostador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				if (tecla.getKeyCode() == KeyEvent.VK_ENTER && !campoIdApostador.getText().equals("0")) {
					procurar();
				}
			}
		});
		campoIdApostador.setBounds(10, 61, 102, 20);
		painelForm.add(campoIdApostador);
		campoIdApostador.setDocument(new FormataCampoApenasNumeros(13));
		campoIdApostador.setColumns(10);
		
		JLabel lblDigiteOId = new JLabel("Digite o ID do apostador e depois tecle enter para verificar se existe.");
		lblDigiteOId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDigiteOId.setBounds(10, 11, 410, 14);
		painelForm.add(lblDigiteOId);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 156, 430, 42);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton button = new JButton("Cadastrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastar();
			}
		});
		button.setBounds(10, 11, 83, 23);
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(button);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaCampos();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBounds(103, 12, 89, 23);
		panel.add(btnLimpar);


	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
	
	@SuppressWarnings("deprecation")
	private void habilitaCampos(){
		campoIdApostador.disable();
		campoTipoMovimentacao.enable();
		campoValor.enable();
	}
	
	@SuppressWarnings("deprecation")
	private void limpaCampos(){
		campoIdApostador.enable();
		campoTipoMovimentacao.disable();
		campoValor.disable();
		campoIdApostador.setText("");
		campoApostador.setText("");
		campoTipoMovimentacao.setSelectedIndex(0);
		campoValor.setText("");
		campoIdApostador.requestFocus();
	}
	
	private boolean validaCampos(){
		String tipoMovimentacao = (String) campoTipoMovimentacao.getSelectedItem();
		String valor = campoValor.getText();
		if(tipoMovimentacao.equals("")){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoTipoMovimentacao.requestFocus();
			}
			return false;
		}else if(valor.equals("0.00")){
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
		long id = Long.parseLong(campoIdApostador.getText());		
		try {
			Apostador apostador = fachada.procurarApostadorPorId(id);
			campoApostador.setText(apostador.getNome());
			habilitaCampos();
		} catch (CpfInvalidoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (ApostadorNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar procurar o apostador!");
		}
	}
	
	private void cadastar(){
		if (validaCampos()) {
			long idApostador = Long.parseLong(campoIdApostador.getText());
			String tipoMovimentacao = (String) campoTipoMovimentacao.getSelectedItem();
			double valor = Double.parseDouble(campoValor.getText());
			try {
				fachada.cadastrarMovimentacaoFinanceiraApostador(new MovimentacaoFinanceiraApostador(0, idApostador, tipoMovimentacao, valor));
				try {
					throw new CadastroEfetuadoComSucessoException();
				} catch (CadastroEfetuadoComSucessoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				limpaCampos();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao cadastrar a movimentação financeira!");
			}
		}
	}
}

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
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import br.com.futbolao.competicao.Competicao;
import br.com.futbolao.exception.CadastroEfetuadoComSucessoException;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.GrupoJaCadastradoException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.grupo.Grupo;
import br.com.futbolao.util.FormataCampoApenasNumeros;
import br.com.futbolao.util.JMoneyField;
import br.com.futbolao.util.MascaraCampo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GrupoCadastrar extends JInternalFrame {
	private Fachada fachada = null;
	private MascaraCampo mascara = new MascaraCampo();
	private JMoneyField campoValorDaAposta;
	private JTextField campoLimiteDeApostas;
	private JFormattedTextField campoPercentualAdministrador;
	private JTextField campoLimiteDeApostasPorApostador;
	private JFormattedTextField campoEncerramentoAposta;
	private JTextField campoPontuacaoPorResultado;
	private JTextField campoPontuacaoPorPlacar;
	private int[] valueCopeticao;
	private int[] valueRodada;
	@SuppressWarnings("rawtypes")
	private JComboBox campoCompeticao;
	@SuppressWarnings("rawtypes")
	private JComboBox campoRodada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrupoCadastrar frame = new GrupoCadastrar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Esta Classe não está pronta
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public GrupoCadastrar() {
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
		setTitle("Grupo Cadastrar");
		setClosable(true);
		setBounds(100, 100, 420, 359);
		getContentPane().setLayout(null);
		
		JPanel painelCadastrar = new JPanel();
		painelCadastrar.setBackground(Color.WHITE);
		painelCadastrar.setBounds(10, 11, 384, 310);
		getContentPane().add(painelCadastrar);
		painelCadastrar.setLayout(null);
		
		JLabel lblValorAposta = new JLabel("Valor da aposta:");
		lblValorAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValorAposta.setBounds(257, 73, 113, 20);
		painelCadastrar.add(lblValorAposta);
		
		campoValorDaAposta = new JMoneyField();
		campoValorDaAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoValorDaAposta.setBounds(257, 104, 113, 20);
		painelCadastrar.add(campoValorDaAposta);
		campoValorDaAposta.setColumns(10);
		
		JLabel lblLimiteApostas = new JLabel("Limite de apostas:");
		lblLimiteApostas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLimiteApostas.setBounds(10, 73, 113, 20);
		painelCadastrar.add(lblLimiteApostas);
		
		campoLimiteDeApostas = new JTextField();
		campoLimiteDeApostas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoLimiteDeApostas.setColumns(10);
		campoLimiteDeApostas.setBounds(10, 104, 100, 20);
		campoLimiteDeApostas.setDocument(new FormataCampoApenasNumeros(5));
		painelCadastrar.add(campoLimiteDeApostas);
		
		JLabel lblPercentualdoADM = new JLabel("Lucro do ADM %:");
		lblPercentualdoADM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPercentualdoADM.setBounds(10, 135, 157, 20);
		painelCadastrar.add(lblPercentualdoADM);
		
		campoPercentualAdministrador = new JFormattedTextField();
		campoPercentualAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPercentualAdministrador.setColumns(10);
		campoPercentualAdministrador.setBounds(10, 166, 100, 20);
		campoPercentualAdministrador.setDocument(new FormataCampoApenasNumeros(2));
		painelCadastrar.add(campoPercentualAdministrador);
		
	    campoCompeticao = new JComboBox();
		campoCompeticao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (campoCompeticao.getSelectedIndex() > 0){
					listaRodada();
				}
			}
		});
		campoCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCompeticao.setBounds(10, 42, 295, 20);
		painelCadastrar.add(campoCompeticao);
		
		JLabel lblCompeticao = new JLabel("Selecione a competição:");
		lblCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCompeticao.setBounds(10, 11, 157, 20);
		painelCadastrar.add(lblCompeticao);
		
		JLabel lblRodada = new JLabel("Rodada:");
		lblRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRodada.setBounds(315, 11, 55, 20);
		painelCadastrar.add(lblRodada);
		
		campoRodada = new JComboBox();
		campoRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoRodada.setBounds(315, 42, 55, 20);
		painelCadastrar.add(campoRodada);
		
		JLabel lblLimete_apostas = new JLabel("Apostas por apostador:");
		lblLimete_apostas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLimete_apostas.setBounds(120, 73, 127, 20);
		painelCadastrar.add(lblLimete_apostas);
		
		campoLimiteDeApostasPorApostador = new JTextField();
		campoLimiteDeApostasPorApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoLimiteDeApostasPorApostador.setBounds(120, 104, 127, 20);
		painelCadastrar.add(campoLimiteDeApostasPorApostador);
		campoLimiteDeApostasPorApostador.setDocument(new FormataCampoApenasNumeros(4));
		campoLimiteDeApostasPorApostador.setColumns(10);
		
		JLabel lblEncerramentodaAposta = new JLabel("Data de encerramento das apostas:");
		lblEncerramentodaAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEncerramentodaAposta.setBounds(10, 202, 195, 20);
		painelCadastrar.add(lblEncerramentodaAposta);
		
		campoEncerramentoAposta = new JFormattedTextField();
		campoEncerramentoAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEncerramentoAposta.setBounds(10, 233, 100, 20);
		painelCadastrar.add(campoEncerramentoAposta);
		mascara.getData().install(campoEncerramentoAposta);
		campoEncerramentoAposta.setColumns(10);
		
		JLabel lblPontuacaoporResultado = new JLabel("Pontos por resultado:");
		lblPontuacaoporResultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPontuacaoporResultado.setBounds(120, 135, 119, 20);
		painelCadastrar.add(lblPontuacaoporResultado);
		
		campoPontuacaoPorResultado = new JTextField();
		campoPontuacaoPorResultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPontuacaoPorResultado.setBounds(120, 166, 119, 20);
		painelCadastrar.add(campoPontuacaoPorResultado);
		campoPontuacaoPorResultado.setDocument(new FormataCampoApenasNumeros(5));
		campoPontuacaoPorResultado.setColumns(10);
		
		JLabel lblPontuacaoporPlacar = new JLabel("Pontos por placar:");
		lblPontuacaoporPlacar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPontuacaoporPlacar.setBounds(249, 135, 146, 20);
		painelCadastrar.add(lblPontuacaoporPlacar);
		
		campoPontuacaoPorPlacar = new JTextField();
		campoPontuacaoPorPlacar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPontuacaoPorPlacar.setBounds(249, 166, 119, 20);
		painelCadastrar.add(campoPontuacaoPorPlacar);
		campoPontuacaoPorPlacar.setDocument(new FormataCampoApenasNumeros(5));
		campoPontuacaoPorPlacar.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrar();
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrar.setBounds(10, 275, 89, 23);
		painelCadastrar.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBounds(109, 275, 89, 23);
		painelCadastrar.add(btnLimpar);
		
		listaCompeticao();

	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void limparCampos(){
		campoCompeticao.setSelectedIndex(0);
		campoRodada.setSelectedIndex(0);
		campoEncerramentoAposta.setText("");
		campoLimiteDeApostasPorApostador.setText("");
		campoLimiteDeApostas.setText("");
		campoPercentualAdministrador.setText("");
		campoPontuacaoPorPlacar.setText("");
		campoPontuacaoPorResultado.setText("");
		campoValorDaAposta.setText("");
	}
	
	private boolean validaCampos(){
		String limiteDeApostasPorApostador = campoLimiteDeApostasPorApostador.getText();
		String limiteDeApostas = campoLimiteDeApostas.getText();
		String percentualAdministrador = campoPercentualAdministrador.getText();
		String pontosPorPlacar = campoPontuacaoPorPlacar.getText();
		String pontosPorReultado = campoPontuacaoPorResultado.getText();
		String valorDaAposta = campoValorDaAposta.getText();
		String data = campoEncerramentoAposta.getText();
		DateFormat dataFormatada = new SimpleDateFormat ("dd/MM/yyyy");  
	    dataFormatada.setLenient(false); 
	    try {  
	        dataFormatada.parse(data);  
	    } catch (ParseException ex) {  
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoEncerramentoAposta.requestFocus();
			}
			return false;
	    }
	    if(campoCompeticao.getSelectedIndex() == 0){
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoCompeticao.requestFocus();
			}
			return false;
	    } else if (campoCompeticao.getSelectedIndex() == 0){
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoRodada.requestFocus();
			}
			return false;
	    } else if (limiteDeApostas.equals("")){
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoLimiteDeApostas.requestFocus();
			}
			return false;
	    } else if (limiteDeApostasPorApostador.equals("")){
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoLimiteDeApostasPorApostador.requestFocus();
			}
			return false;
	    } else if (valorDaAposta.equals("0.00")){
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoLimiteDeApostas.requestFocus();
			}
			return false;
	    } else if (percentualAdministrador.equals("")){
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoPercentualAdministrador.requestFocus();
			}
			return false;
	    } else if (pontosPorPlacar.equals("")){
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoPontuacaoPorPlacar.requestFocus();
			}
			return false;
	    } else if (pontosPorReultado.equals("")){
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoPontuacaoPorResultado.requestFocus();
			}
			return false;
	    } else {
	    	return true;
	    }
	}
	
	@SuppressWarnings({ "unchecked" })
	private void  listaCompeticao(){
		ArrayList<Competicao> lista = new ArrayList<>();
		try {
			campoCompeticao.addItem("");
			lista = fachada.listarCompeticaoComRodada();
			valueCopeticao = new int[(lista.size()+1)];
			for (int i = 1; i <= lista.size(); i++) {
				campoCompeticao.addItem(lista.get(i-1).getNome());
				valueCopeticao[i] = lista.get(i-1).getId();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (CompeticaoNaoCadastradaException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao listar as competições!");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void listaRodada(){
		campoRodada.removeAllItems();
		ArrayList<Integer> lista = new ArrayList<>();
		int idCompeticao = valueCopeticao[campoCompeticao.getSelectedIndex()];
		try {
			campoRodada.addItem("");
			lista = fachada.listarRodadaPorCompeticao(idCompeticao, 'N');
			valueRodada = new int[(lista.size()+1)];
			for (int i = 1; i <= lista.size(); i++) {
				campoRodada.addItem(lista.get(i-1));
				valueRodada[i] = lista.get(i-1);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (RodadaNaoCadastradaException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao listar as rodadas!");
		}
	}
	
	private void cadastrar(){
		if(validaCampos()){
			int competicao = campoCompeticao.getSelectedIndex();
			int rodada = campoRodada.getSelectedIndex();
			int limiteDeApostasPorApostador = Integer.parseInt(campoLimiteDeApostasPorApostador.getText());
			int limiteDeApostas = Integer.parseInt(campoLimiteDeApostas.getText());
			int percentualAdministrador = Integer.parseInt(campoPercentualAdministrador.getText());
			int pontosPorPlacar = Integer.parseInt(campoPontuacaoPorPlacar.getText());
			int pontosPorResultado = Integer.parseInt(campoPontuacaoPorResultado.getText());
			if (limiteDeApostas < 1) {
		    	try {
					throw new CampoInvalidoException();
				} catch (CampoInvalidoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
					campoLimiteDeApostas.requestFocus();
				}
				return;
			} else if (limiteDeApostasPorApostador < 1) {
		    	try {
					throw new CampoInvalidoException();
				} catch (CampoInvalidoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
					campoLimiteDeApostasPorApostador.requestFocus();
				}
				return;
			} else if (pontosPorResultado == 0 && pontosPorPlacar == 0) {
		    	try {
					throw new CampoInvalidoException("Defina uma opção de pontuação");
				} catch (CampoInvalidoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				return;
			}
			double valorDaAposta = Double.parseDouble(campoValorDaAposta.getText());
			String data = campoEncerramentoAposta.getText().substring(6, 10) + "-" + campoEncerramentoAposta.getText().substring(3, 5) + "-" + campoEncerramentoAposta.getText().substring(0, 2);
			try {
				fachada.cadastrarGrupo(new Grupo(0, valorDaAposta, limiteDeApostas, limiteDeApostasPorApostador, percentualAdministrador, data, valueCopeticao[competicao], valueRodada[rodada], pontosPorResultado, pontosPorPlacar));
				try {
					throw new CadastroEfetuadoComSucessoException();
				} catch (CadastroEfetuadoComSucessoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				limparCampos();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (GrupoJaCadastradoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao cadastrar o grupo!");
			}
		}
	}
}

package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import br.com.futbolao.competicao.Competicao;
import br.com.futbolao.exception.AlteracaoEfetuadaComSucessoException;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.GrupoNaoCadastradoException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.grupo.Grupo;
import br.com.futbolao.util.FormataCampoApenasNumeros;
import br.com.futbolao.util.JMoneyField;
import br.com.futbolao.util.MascaraCampo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GrupoAlterar extends JInternalFrame {
	private Fachada fachada = null;
	private MascaraCampo mascara = new MascaraCampo();
	private JMoneyField campoValorDaAposta;
	private JTextField campoLimiteDeApostas;
	private JTextField campoPercentualAdministrador;
	private JTextField campoLimiteDeApostasPorApostador;
	private JFormattedTextField campoEncerramentoAposta;
	private JTextField campoPontuacaoPorResultado;
	private JTextField campoPontuacaoPorPlacar;
	private int[] valueCompeticao;
	private int[] valueRodada;
	@SuppressWarnings("rawtypes")
	private JComboBox campoCompeticao;
	@SuppressWarnings("rawtypes")
	private JComboBox campoRodada;
	private DecimalFormat formatacaoDinheiro =  new DecimalFormat("#,##0.00;(#,##0.00)", new DecimalFormatSymbols(new Locale("pt", "BR")));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrupoAlterar frame = new GrupoAlterar(0);
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
	@SuppressWarnings("rawtypes")
	public GrupoAlterar(final long id) {
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
		setTitle("Alterar Grupo");
		setClosable(true);
		setBounds(100, 100, 420, 360);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setBounds(10, 11, 384, 308);
		painelForm.setLayout(null);
		painelForm.setBackground(Color.WHITE);
		getContentPane().add(painelForm);
		
		JLabel label = new JLabel("Valor da aposta:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(257, 73, 113, 20);
		painelForm.add(label);
		
		campoValorDaAposta = new JMoneyField();
		campoValorDaAposta.setEnabled(false);
		campoValorDaAposta.setEditable(false);
		campoValorDaAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoValorDaAposta.setColumns(10);
		campoValorDaAposta.setBounds(257, 104, 113, 20);
		painelForm.add(campoValorDaAposta);
		
		JLabel lblLimiteApostas = new JLabel("Limite de apostas:");
		lblLimiteApostas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLimiteApostas.setBounds(10, 73, 113, 20);
		painelForm.add(lblLimiteApostas);
		
		campoLimiteDeApostas = new JTextField();
		campoLimiteDeApostas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoLimiteDeApostas.setColumns(10);
		campoLimiteDeApostas.setBounds(10, 104, 100, 20);
		campoLimiteDeApostas.setDocument(new FormataCampoApenasNumeros(5));
		painelForm.add(campoLimiteDeApostas);
		
		JLabel LucroADM = new JLabel("Lucro do ADM %:");
		LucroADM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		LucroADM.setBounds(10, 135, 157, 20);
		painelForm.add(LucroADM);
		
		campoPercentualAdministrador = new JTextField();
		campoPercentualAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPercentualAdministrador.setColumns(10);
		campoPercentualAdministrador.setBounds(10, 166, 100, 20);
		campoPercentualAdministrador.setDocument(new FormataCampoApenasNumeros(2));
		painelForm.add(campoPercentualAdministrador);
		
		campoCompeticao = new JComboBox();
		campoCompeticao.setEnabled(false);
		campoCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCompeticao.setBounds(10, 42, 295, 20);
		painelForm.add(campoCompeticao);
		
		JLabel label_3 = new JLabel("Selecione a competi\u00E7\u00E3o:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(10, 11, 157, 20);
		painelForm.add(label_3);
		
		JLabel label_4 = new JLabel("Rodada:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(315, 11, 55, 20);
		painelForm.add(label_4);
		
		campoRodada = new JComboBox();
		campoRodada.setEnabled(false);
		campoRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoRodada.setBounds(315, 42, 55, 20);
		painelForm.add(campoRodada);
		
		JLabel lblApostasApostador = new JLabel("Apostas por apostador:");
		lblApostasApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApostasApostador.setBounds(120, 73, 127, 20);
		painelForm.add(lblApostasApostador);
		
		campoLimiteDeApostasPorApostador = new JTextField();
		campoLimiteDeApostasPorApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoLimiteDeApostasPorApostador.setColumns(10);
		campoLimiteDeApostasPorApostador.setBounds(120, 104, 127, 20);
		campoLimiteDeApostasPorApostador.setDocument(new FormataCampoApenasNumeros(4));
		painelForm.add(campoLimiteDeApostasPorApostador);
		
		JLabel lblEncerramentoAposta = new JLabel("Encerramento da aposta:");
		lblEncerramentoAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEncerramentoAposta.setBounds(10, 202, 146, 20);
		painelForm.add(lblEncerramentoAposta);
		
		campoEncerramentoAposta = new JFormattedTextField();
		campoEncerramentoAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEncerramentoAposta.setColumns(10);
		campoEncerramentoAposta.setBounds(10, 233, 100, 20);
		mascara.getData().install(campoEncerramentoAposta);
		painelForm.add(campoEncerramentoAposta);
		
		JLabel lblPontosResultado = new JLabel("Pontos por resultado:");
		lblPontosResultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPontosResultado.setBounds(120, 135, 119, 20);
		painelForm.add(lblPontosResultado);
		
		campoPontuacaoPorResultado = new JTextField();
		campoPontuacaoPorResultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPontuacaoPorResultado.setColumns(10);
		campoPontuacaoPorResultado.setBounds(120, 166, 119, 20);
		campoPontuacaoPorResultado.setDocument(new FormataCampoApenasNumeros(5));
		painelForm.add(campoPontuacaoPorResultado);
		
		JLabel lblPontosPlacar = new JLabel("Pontos por placar:");
		lblPontosPlacar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPontosPlacar.setBounds(249, 135, 146, 20);
		painelForm.add(lblPontosPlacar);
		
		campoPontuacaoPorPlacar = new JTextField();
		campoPontuacaoPorPlacar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPontuacaoPorPlacar.setColumns(10);
		campoPontuacaoPorPlacar.setBounds(251, 166, 119, 20);
		campoPontuacaoPorPlacar.setDocument(new FormataCampoApenasNumeros(5));
		painelForm.add(campoPontuacaoPorPlacar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterar(id);
			}
		});
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 275, 100, 23);
		painelForm.add(btnAlterar);
		
		preencheCampos(id);
		
	}

	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
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
			lista = fachada.listarCompeticao(' ');
			valueCompeticao = new int[(lista.size()+1)];
			for (int i = 1; i <= lista.size(); i++) {
				campoCompeticao.addItem(lista.get(i-1).getNome());
				valueCompeticao[i] = lista.get(i-1).getId();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (CompeticaoNaoCadastradaException e) {
			JOptionPane.showMessageDialog(rootPane, "Não há grupos cadastrados!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao listar as competições!");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void listaRodada(){
		campoRodada.removeAllItems();
		ArrayList<Integer> lista = new ArrayList<>();
		int idCompeticao = valueCompeticao[campoCompeticao.getSelectedIndex()];
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
			e.printStackTrace();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao listar as rodadas!");
		}
	}
	
	private void preencheCampos(long id){
		try {
			listaCompeticao();
			Grupo grupo = fachada.procurarGrupoPorId(id);
			for (int i=0; i < valueCompeticao.length; i++) {
				if (valueCompeticao[i] == grupo.getIdCompeticao()) {
					campoCompeticao.setSelectedIndex(i);
				}
			}
			listaRodada();
			for (int i=0; i < valueRodada.length; i++) {
				if (valueRodada[i] == grupo.getIdRodada()) {
					campoRodada.setSelectedIndex(i);
				}
			}
			campoEncerramentoAposta.setText(grupo.getDataEncerramentoAposta());
			campoLimiteDeApostas.setText(String.valueOf(grupo.getLimiteApostas()));
			campoLimiteDeApostasPorApostador.setText(String.valueOf(grupo.getLimiteApostasPorApostador()));
			campoPercentualAdministrador.setText(String.valueOf(grupo.getPercentualLucroAdministrador()));
			campoPontuacaoPorPlacar.setText(String.valueOf(grupo.getPontuacaoPorPlacar()));
			campoPontuacaoPorResultado.setText(String.valueOf(grupo.getPontuacaoPorResultado()));
			campoValorDaAposta.setText(formatacaoDinheiro.format(grupo.getValorAposta()));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (GrupoNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao preencher campos!");
			e.printStackTrace();
		}
	}
	
	private void alterar(long id){
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
				fachada.atualizarGrupo(new Grupo(id, valorDaAposta, limiteDeApostas, limiteDeApostasPorApostador, percentualAdministrador, data, valueCompeticao[competicao], valueRodada[rodada], pontosPorResultado, pontosPorPlacar));
				try {
					throw new AlteracaoEfetuadaComSucessoException();
				} catch (AlteracaoEfetuadaComSucessoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				this.dispose();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (GrupoNaoCadastradoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao alterar o grupo!");
			}
		}
	}
}

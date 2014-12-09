package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

import br.com.futbolao.competicao.Competicao;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;
import br.com.futbolao.exception.ConfirmacaoDeExclusaoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.RodadaDestravadaComSucessoException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;
import br.com.futbolao.exception.RodadaTravadaComSucessoException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.rodada.Rodada;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

@SuppressWarnings("serial")
public class RodadaListar extends JInternalFrame {
	private Fachada fachada = null;
	private JTable tabelaRodada;
	private DefaultTableModel modeloTabelaRodada;
	private String[] colunaTabelaRodada;
	@SuppressWarnings("rawtypes")
	private JComboBox campoCompeticao;
	private int[] valueCopeticao;
	private int[] valueRodada;
	@SuppressWarnings("rawtypes")
	private JComboBox campoRodada;
	private JButton btnTravarRodada;
	private JButton btnDestravarRodada;
	private JButton btnDeletar;
	private JButton btnAlterar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RodadaListar frame = new RodadaListar();
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
	public RodadaListar() {
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			try {
				throw new ErroAoInstanciarFachadaException();
			} catch (ErroAoInstanciarFachadaException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			}
		}
		setClosable(true);
		setTitle("Listar Rodada");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		setBounds(100, 100, 1100, 430);
		getContentPane().setLayout(null);
		
		JPanel painelTabela = new JPanel();
		painelTabela.setBackground(Color.WHITE);
		painelTabela.setBounds(10, 11, 1064, 344);
		getContentPane().add(painelTabela);
		painelTabela.setLayout(null);
		
		JLabel lblSelelcioneCompeticao = new JLabel("Selecione a competi\u00E7\u00E3o:");
		lblSelelcioneCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSelelcioneCompeticao.setBounds(10, 11, 133, 20);
		painelTabela.add(lblSelelcioneCompeticao);
		
		JScrollPane scrollPaneRodada = new JScrollPane();
		scrollPaneRodada.setBounds(10, 73, 1044, 271);
		painelTabela.add(scrollPaneRodada);
		
		tabelaRodada = new JTable();
		tabelaRodada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colunaTabelaRodada = new String[] {"","RODADA", "JOGO", "DATA/HORA", "CLUBE 1", "RESULTADO 1", "CLUBE 2", "RESULTADO 2", "TRAVADO"};
		modeloTabelaRodada = new DefaultTableModel(new Object[][] {},colunaTabelaRodada){
			boolean[] columnEditables = new boolean[] {false, false, false, false, false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabelaRodada.setModel(modeloTabelaRodada);
		tabelaRodada.getColumnModel().getColumn(0).setMinWidth(0);
		tabelaRodada.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabelaRodada.getColumnModel().getColumn(0).setMaxWidth(0);
		tabelaRodada.getColumnModel().getColumn(1).setPreferredWidth(50);
		tabelaRodada.getColumnModel().getColumn(2).setPreferredWidth(50);
		tabelaRodada.getColumnModel().getColumn(3).setPreferredWidth(120);
		tabelaRodada.getColumnModel().getColumn(4).setPreferredWidth(260);
		tabelaRodada.getColumnModel().getColumn(5).setPreferredWidth(90);
		tabelaRodada.getColumnModel().getColumn(6).setPreferredWidth(260);
		tabelaRodada.getColumnModel().getColumn(7).setPreferredWidth(90);
		tabelaRodada.getColumnModel().getColumn(8).setPreferredWidth(70);
		tabelaRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneRodada.setViewportView(tabelaRodada);
		
		campoCompeticao = new JComboBox();
		campoCompeticao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (campoCompeticao.getSelectedIndex() > 0){
					listaRodada();
					limparTabela();
				}else{
					campoRodada.removeAllItems();
					btnAlterar.setEnabled(false);
					btnDeletar.setEnabled(false);
					btnTravarRodada.setEnabled(false);
					btnDestravarRodada.setEnabled(false);
					limparTabela();
				}
			}
		});
		campoCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCompeticao.setBounds(10, 42, 445, 20);
		campoCompeticao.setMaximumRowCount(20);
		painelTabela.add(campoCompeticao);
		
		campoRodada = new JComboBox();
		campoRodada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (campoRodada.getSelectedIndex() > 0){
					btnAlterar.setEnabled(true);
					btnDeletar.setEnabled(true);
					limparTabela();
					procurar();
				}else{
					btnAlterar.setEnabled(false);
					btnDeletar.setEnabled(false);
					btnTravarRodada.setEnabled(false);
					btnDestravarRodada.setEnabled(false);
					limparTabela();
				}
			}
		});
		campoRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoRodada.setBounds(465, 42, 111, 20);
		campoRodada.setMaximumRowCount(20);
		painelTabela.add(campoRodada);
		
		JLabel lblRodada = new JLabel("Selecione a rodada:");
		lblRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRodada.setBounds(465, 15, 111, 20);
		painelTabela.add(lblRodada);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 353, 1064, 36);
		getContentPane().add(painelBotoes);
		painelBotoes.setLayout(null);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterar();
			}
		});
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 11, 89, 23);
		painelBotoes.add(btnAlterar);
		
		btnDeletar = new JButton("Deletar");
		btnDeletar.setEnabled(false);
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deletar();
			}
		});
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeletar.setBounds(109, 12, 89, 23);
		painelBotoes.add(btnDeletar);
		
		btnTravarRodada = new JButton("Travar Rodada");
		btnTravarRodada.setEnabled(false);
		btnTravarRodada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				travar();
			}
		});
		btnTravarRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTravarRodada.setBounds(208, 12, 111, 23);
		painelBotoes.add(btnTravarRodada);
		
		btnDestravarRodada = new JButton("Destravar Rodada");
		btnDestravarRodada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				destravar();
			}
		});
		btnDestravarRodada.setEnabled(false);
		btnDestravarRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDestravarRodada.setBounds(329, 11, 133, 23);
		painelBotoes.add(btnDestravarRodada);
		
		listaCompeticao();

	}

	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void limparTabela(){
		this.modeloTabelaRodada.setNumRows(0);
	}
	
	@SuppressWarnings({ "unchecked" })
	private void  listaCompeticao(){
		limparTabela();
		ArrayList<Competicao> lista = new ArrayList<>();
		try {
			campoCompeticao.addItem("");
			lista = fachada.listarCompeticao();
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
			lista = fachada.procurarRodada(idCompeticao, 0);
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void procurar(){
		limparTabela();
		int idCompeticao = campoCompeticao.getSelectedIndex();
		int numeroDaRodada = campoRodada.getSelectedIndex();
		ArrayList<Rodada> lista = new ArrayList<>();
		try {
			char acaoDosBotoes = ' ';
			lista = fachada.procurarRodada(valueCopeticao[idCompeticao], valueRodada[numeroDaRodada]);
			for (Rodada rodada: lista) {
				Vector vector = new Vector();
				vector.add(rodada.getId());
				vector.add(rodada.getNumeroRodada());
				vector.add(rodada.getIdJogo());
				vector.add(rodada.getDataHora());
				vector.add(rodada.getNomeClube1());
				vector.add(rodada.getResultadoClube1());
				vector.add(rodada.getNomeClube2());
				vector.add(rodada.getResultadoClube2());
				vector.add(rodada.getTrava());
				modeloTabelaRodada.addRow(vector);
				acaoDosBotoes = rodada.getTrava();
			}
			if (acaoDosBotoes == 'S') {
				btnDestravarRodada.setEnabled(true);
				btnTravarRodada.setEnabled(false);
				btnAlterar.setEnabled(false);
				btnDeletar.setEnabled(false);
			} else {
				btnTravarRodada.setEnabled(true);
				btnDestravarRodada.setEnabled(false);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (RodadaNaoCadastradaException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao listar as rodadas!");
		}
	}
	
	private void alterar(){
		if (tabelaRodada.getSelectedRowCount() == 1) {
			int linha = tabelaRodada.getSelectedRow();
			long id = (long)tabelaRodada.getValueAt(linha, 0);
			RodadaAlterar rodadaAlterar = new RodadaAlterar(id);
			Principal.desktopPane.add(rodadaAlterar);
			rodadaAlterar.setVisible(true);
			rodadaAlterar.setPosicao();	
		}
	}
	
	private void deletar(){
		if (tabelaRodada.getSelectedRowCount() == 1) {
			try {
				throw new ConfirmacaoDeExclusaoException();
			} catch (ConfirmacaoDeExclusaoException ex) {
				int confirmacao = JOptionPane.showConfirmDialog(rootPane, ex.getMessage(), "Alerta", JOptionPane.YES_NO_OPTION);
				if (confirmacao == 0) {
					try {
						int linha = tabelaRodada.getSelectedRow();
						long id = (long)tabelaRodada.getValueAt(linha, 0);
						fachada.deletarRodada(id);
						procurar();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					} catch (RodadaNaoCadastradaException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao deletar a rodada!");
					}
				}
			}
		}
	}
	
	private void travar(){
		int idCompeticao = valueCopeticao[campoCompeticao.getSelectedIndex()];
		int numeroDaRodada = valueRodada[campoRodada.getSelectedIndex()];
		try {
			fachada.travarRodada(idCompeticao, numeroDaRodada);
			procurar();
			try {
				throw new RodadaTravadaComSucessoException();
			} catch (RodadaTravadaComSucessoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao travar a rodada!");
		}
	}
	
	private void destravar(){
		int idCompeticao = valueCopeticao[campoCompeticao.getSelectedIndex()];
		int numeroDaRodada = valueRodada[campoRodada.getSelectedIndex()];
		try {
			fachada.destravarRodada(idCompeticao, numeroDaRodada);
			procurar();
			try {
				throw new RodadaDestravadaComSucessoException();
			} catch (RodadaDestravadaComSucessoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao travar a rodada!");
		}
	}
}

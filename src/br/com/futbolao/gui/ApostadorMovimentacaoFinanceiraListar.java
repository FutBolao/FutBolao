package br.com.futbolao.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.com.futbolao.exception.ConfirmacaoDeExclusaoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.MovimentacaoNaoCadastradaException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.movimentacao.financeira.apostador.MovimentacaoFinanceiraApostador;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class ApostadorMovimentacaoFinanceiraListar extends JInternalFrame {
	private Fachada fachada = null;
	private JTable tabelaMovimentacaoFinanceiraApostador;
	private DefaultTableModel tabelaModeloMovimentacaoFinanceiraApostador;
	private String[] colunaTabelaMovimentacaoFinanceiraApostador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApostadorMovimentacaoFinanceiraListar frame = new ApostadorMovimentacaoFinanceiraListar();
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
	@SuppressWarnings("serial")
	public ApostadorMovimentacaoFinanceiraListar() {
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
		setBounds(100, 100, 788, 366);
		getContentPane().setLayout(null);
		
		JPanel painelMovimentacaoFinanceiraApostador = new JPanel();
		painelMovimentacaoFinanceiraApostador.setBackground(Color.WHITE);
		painelMovimentacaoFinanceiraApostador.setBounds(10, 11, 752, 314);
		getContentPane().add(painelMovimentacaoFinanceiraApostador);
		painelMovimentacaoFinanceiraApostador.setLayout(null);
		
		JScrollPane scrollPaneMovimentacaoFinanceiraApostador = new JScrollPane();
		scrollPaneMovimentacaoFinanceiraApostador.setBounds(10, 10, 732, 260);
		painelMovimentacaoFinanceiraApostador.add(scrollPaneMovimentacaoFinanceiraApostador);
		
		tabelaMovimentacaoFinanceiraApostador = new JTable();
		tabelaMovimentacaoFinanceiraApostador.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colunaTabelaMovimentacaoFinanceiraApostador = new String[] {"ID", "ID do Apostador", "Apostador", "Tipo da Movimentação", "Valor", "Data - Hora"};
		tabelaModeloMovimentacaoFinanceiraApostador = new DefaultTableModel(new Object[][] {}, colunaTabelaMovimentacaoFinanceiraApostador){
			boolean[] columnEditables = new boolean[] {false, false, false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabelaMovimentacaoFinanceiraApostador.setModel(tabelaModeloMovimentacaoFinanceiraApostador);
		tabelaMovimentacaoFinanceiraApostador.getColumnModel().getColumn(0).setPreferredWidth(45);
		tabelaMovimentacaoFinanceiraApostador.getColumnModel().getColumn(1).setPreferredWidth(95);
		tabelaMovimentacaoFinanceiraApostador.getColumnModel().getColumn(2).setPreferredWidth(272);
		tabelaMovimentacaoFinanceiraApostador.getColumnModel().getColumn(3).setPreferredWidth(127);
		tabelaMovimentacaoFinanceiraApostador.getColumnModel().getColumn(4).setPreferredWidth(85);
		tabelaMovimentacaoFinanceiraApostador.getColumnModel().getColumn(5).setPreferredWidth(125);
		scrollPaneMovimentacaoFinanceiraApostador.setViewportView(tabelaMovimentacaoFinanceiraApostador);
		
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 276, 732, 28);
		painelMovimentacaoFinanceiraApostador.add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deletar();
			}
		});
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeletar.setBounds(0, 0, 89, 23);
		painelBotoes.add(btnDeletar);	
		
		listar();
	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void limparTabela(){
		tabelaModeloMovimentacaoFinanceiraApostador.setNumRows(0);
	}
	
	private void listar(){		
		try {
			limparTabela();
			ArrayList<MovimentacaoFinanceiraApostador> lista = new ArrayList<>();
			lista = fachada.listarMovimentacaoFinanceiraApostador();
			for (MovimentacaoFinanceiraApostador movimentacaoFinanceiraApostador : lista) {
				Vector vector = new Vector<>();
				vector.add(movimentacaoFinanceiraApostador.getId());
				vector.add(movimentacaoFinanceiraApostador.getIdApostador());
				vector.add(movimentacaoFinanceiraApostador.getNomeApostador());
				vector.add(movimentacaoFinanceiraApostador.getTipoMovimentacao());
				vector.add(movimentacaoFinanceiraApostador.getValor());
				vector.add(movimentacaoFinanceiraApostador.getDataHora());
				tabelaModeloMovimentacaoFinanceiraApostador.addRow(vector);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (MovimentacaoNaoCadastradaException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar tentar listar as movimentações do apostador!");
		}
	}
	
	private void deletar(){
		if (tabelaMovimentacaoFinanceiraApostador.getSelectedRowCount() == 1) {
			try {
				throw new ConfirmacaoDeExclusaoException();
			} catch (ConfirmacaoDeExclusaoException ex) {
				int confirmacao = JOptionPane.showConfirmDialog(rootPane, ex.getMessage(), "Alerta", JOptionPane.YES_NO_OPTION);
				if (confirmacao == 0) {
					try {
						int linha = tabelaMovimentacaoFinanceiraApostador.getSelectedRow();
						Long id = (Long) tabelaMovimentacaoFinanceiraApostador.getValueAt(linha, 0);
						fachada.deletarMovimentacaoFinanceiraApostador(id);
						listar();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					} catch (MovimentacaoNaoCadastradaException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar tentar deletar a movimentação selecionada!");
					}
				}
			}
		}
	}
}
























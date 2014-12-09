package br.com.futbolao.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.com.futbolao.exception.ConfirmacaoDeExclusaoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.MovimentacaoNaoCadastradaException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.movimentacao.financeira.administrador.MovimentacaoFinanceiraAdministrador;

@SuppressWarnings("serial")
public class AdministradorMovimentacaoFinanceiraListar extends JInternalFrame {
	private Fachada fachada = null;
	private JTable tabelaMovimentacaoFinanceiraAdministrador;
	private DefaultTableModel tabelaModeloMovimentacaoFinanceiraAdministrador;
	private String[] colunaTabelaMovimentacaoFinanceiraAdministrador;
	private DecimalFormat formatacaoDinheiro =  new DecimalFormat("#,##0.00;(#,##0.00)", new DecimalFormatSymbols(new Locale("pt", "BR")));
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministradorMovimentacaoFinanceiraListar frame = new AdministradorMovimentacaoFinanceiraListar();
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
	public AdministradorMovimentacaoFinanceiraListar() {
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
		setTitle("Movimentação Financeira Administrador");
		setClosable(true);
		setBounds(100, 100, 788, 366);
		getContentPane().setLayout(null);
		
		JPanel painelMovimentacaoFinanceiraAdministrador = new JPanel();
		painelMovimentacaoFinanceiraAdministrador.setBackground(Color.WHITE);
		painelMovimentacaoFinanceiraAdministrador.setBounds(10, 11, 752, 314);
		getContentPane().add(painelMovimentacaoFinanceiraAdministrador);
		painelMovimentacaoFinanceiraAdministrador.setLayout(null);
		
		JScrollPane scrollPaneMovimentacaoFinanceiraAdministrador = new JScrollPane();
		scrollPaneMovimentacaoFinanceiraAdministrador.setBounds(10, 10, 732, 260);
		painelMovimentacaoFinanceiraAdministrador.add(scrollPaneMovimentacaoFinanceiraAdministrador);
		
		tabelaMovimentacaoFinanceiraAdministrador = new JTable();
		tabelaMovimentacaoFinanceiraAdministrador.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colunaTabelaMovimentacaoFinanceiraAdministrador = new String[] {"ID", "ID do Administrador", "Administrador", "Tipo da Movimentação", "Valor", "Data - Hora"};
		tabelaModeloMovimentacaoFinanceiraAdministrador = new DefaultTableModel(new Object[][] {}, colunaTabelaMovimentacaoFinanceiraAdministrador){
			boolean[] columnEditables = new boolean[] {false, false, false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabelaMovimentacaoFinanceiraAdministrador.setModel(tabelaModeloMovimentacaoFinanceiraAdministrador);
		tabelaMovimentacaoFinanceiraAdministrador.getColumnModel().getColumn(0).setPreferredWidth(45);
		tabelaMovimentacaoFinanceiraAdministrador.getColumnModel().getColumn(1).setPreferredWidth(95);
		tabelaMovimentacaoFinanceiraAdministrador.getColumnModel().getColumn(2).setPreferredWidth(272);
		tabelaMovimentacaoFinanceiraAdministrador.getColumnModel().getColumn(3).setPreferredWidth(127);
		tabelaMovimentacaoFinanceiraAdministrador.getColumnModel().getColumn(4).setPreferredWidth(85);
		tabelaMovimentacaoFinanceiraAdministrador.getColumnModel().getColumn(5).setPreferredWidth(125);
		scrollPaneMovimentacaoFinanceiraAdministrador.setViewportView(tabelaMovimentacaoFinanceiraAdministrador);
		
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 276, 732, 28);
		painelMovimentacaoFinanceiraAdministrador.add(painelBotoes);
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
		
		JButton btnAtualizar = new JButton("Atualizar Lista");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listar();
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAtualizar.setBounds(99, 1, 122, 23);
		painelBotoes.add(btnAtualizar);
		
		listar();

	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void limparTabela(){
		tabelaModeloMovimentacaoFinanceiraAdministrador.setNumRows(0);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void listar(){		
		try {
			limparTabela();
			ArrayList<MovimentacaoFinanceiraAdministrador> lista = new ArrayList<>();
			lista = fachada.listarMovimentacaoFinanceiraAdministrador();
			for (MovimentacaoFinanceiraAdministrador movimentacaoFinanceiraAdministrador : lista) {
				Vector vector = new Vector<>();
				vector.add(movimentacaoFinanceiraAdministrador.getId());
				vector.add(movimentacaoFinanceiraAdministrador.getIdAdministrador());
				vector.add(movimentacaoFinanceiraAdministrador.getNomeAdministrador());
				vector.add(movimentacaoFinanceiraAdministrador.getTipoMovimentacao());
				vector.add(formatacaoDinheiro.format(movimentacaoFinanceiraAdministrador.getValor()));
				vector.add(movimentacaoFinanceiraAdministrador.getDataHora());
				tabelaModeloMovimentacaoFinanceiraAdministrador.addRow(vector);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (MovimentacaoNaoCadastradaException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar tentar listar as movimentações do administrador!");
		}
	}
	
	private void deletar(){
		if (tabelaMovimentacaoFinanceiraAdministrador.getSelectedRowCount() == 1) {
			try {
				throw new ConfirmacaoDeExclusaoException();
			} catch (ConfirmacaoDeExclusaoException ex) {
				int confirmacao = JOptionPane.showConfirmDialog(rootPane, ex.getMessage(), "Alerta", JOptionPane.YES_NO_OPTION);
				if (confirmacao == 0) {
					try {
						int linha = tabelaMovimentacaoFinanceiraAdministrador.getSelectedRow();
						Long id = (Long) tabelaMovimentacaoFinanceiraAdministrador.getValueAt(linha, 0);
						fachada.deletarMovimentacaoFinanceiraAdministrador(id);
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

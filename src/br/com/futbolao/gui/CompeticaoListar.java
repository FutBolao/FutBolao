package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import br.com.futbolao.competicao.Competicao;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;
import br.com.futbolao.exception.ConfirmacaoDeExclusaoException;
import br.com.futbolao.fachada.Fachada;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompeticaoListar extends JInternalFrame {
	private Fachada fachada;
	private JTextField campoProcurar;
	private JTable tabelaCompeticao;
	private DefaultTableModel tabelaModeloCompeticao;
	private String[] colunaTabelaCompeticao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompeticaoListar frame = new CompeticaoListar();
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
	public CompeticaoListar() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel painelTabela = new JPanel();
		painelTabela.setBackground(Color.WHITE);
		painelTabela.setBounds(10, 11, 522, 402);
		getContentPane().add(painelTabela);
		painelTabela.setLayout(null);
		
		campoProcurar = new JTextField();
		campoProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoProcurar.setBounds(10, 32, 380, 20);
		painelTabela.add(campoProcurar);
		campoProcurar.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				procurar();
			}
		});
		btnProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProcurar.setBounds(400, 30, 110, 23);
		painelTabela.add(btnProcurar);
		
		JScrollPane scrollPaneCompeticao = new JScrollPane();
		scrollPaneCompeticao.setBounds(10, 63, 500, 339);
		painelTabela.add(scrollPaneCompeticao);
		
		tabelaCompeticao = new JTable();
		tabelaCompeticao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colunaTabelaCompeticao = new String[] {"ID", "Nome", "Quantidade de Rodadas", "Ativo"};
		tabelaModeloCompeticao = new DefaultTableModel(new Object[][] {},colunaTabelaCompeticao){
			boolean[] columnEditables = new boolean[] {false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabelaCompeticao.setModel(tabelaModeloCompeticao);
		tabelaCompeticao.getColumnModel().getColumn(1).setPreferredWidth(244);
		tabelaCompeticao.getColumnModel().getColumn(2).setPreferredWidth(133);
		tabelaCompeticao.getColumnModel().getColumn(3).setPreferredWidth(46);
		scrollPaneCompeticao.setViewportView(tabelaCompeticao);
		
		JLabel lblTituloProcurar = new JLabel("Digite o nome da competição ou deixe em branco para listar todas:");
		lblTituloProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTituloProcurar.setBounds(10, 7, 400, 19);
		painelTabela.add(lblTituloProcurar);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 414, 522, 45);
		getContentPane().add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 11, 89, 23);
		painelBotoes.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeletar.setBounds(109, 12, 89, 23);
		painelBotoes.add(btnDeletar);
		setTitle("Listar Competições");
		setClosable(true);
		setBounds(100, 100, 557, 500);

	}
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void limparTabela(){
		this.tabelaModeloCompeticao.setNumRows(0);
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void procurar(){
		try {
			limparTabela();
			String procurar = campoProcurar.getText();
			limparTabela();
			ArrayList<Competicao> lista = new ArrayList<>();
			if (procurar.equals("")){
				lista = fachada.listarCompeticao();
			} else {
				lista = fachada.listarCompeticaoPorNome(procurar);
			}
			for (Competicao competicao : lista) {
				@SuppressWarnings("rawtypes")
				Vector vector = new Vector();
				vector.add(competicao.getId());
				vector.add(competicao.getNome());
				vector.add(competicao.getQtdRodadas());
				vector.add(competicao.getAtivo());
				tabelaModeloCompeticao.addRow(vector);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (CompeticaoNaoCadastradaException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar procurar competição!");
		}
				
	}
	
	private void alterar(){
		if (tabelaCompeticao.getSelectedRowCount() == 1) {
				int linha = tabelaCompeticao.getSelectedRow();
				int id = (int)tabelaCompeticao.getValueAt(linha, 0);
				CompeticaoAlterar competicaoAlterar = new CompeticaoAlterar(id);
				Principal.desktopPane.add(competicaoAlterar);
				competicaoAlterar.setVisible(true);
				competicaoAlterar.setPosicao();
		}
	}
	
	private void deletar(){
		if (tabelaCompeticao.getSelectedRowCount() == 1) {
			try {
				throw new ConfirmacaoDeExclusaoException();
			} catch (ConfirmacaoDeExclusaoException ex) {
				int confirmacao = JOptionPane.showConfirmDialog(rootPane, ex.getMessage(), "Alerta", JOptionPane.YES_NO_OPTION);
				if (confirmacao == 0) {
					try {
						int linha = tabelaCompeticao.getSelectedRow();
						int id = (int)tabelaCompeticao.getValueAt(linha, 0);
						fachada.deletarCompeticao(id);
						procurar();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					} catch (CompeticaoNaoCadastradaException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar deletar competição!");
					}
				}
			}
		}
	}
}

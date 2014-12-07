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
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;
import br.com.futbolao.fachada.Fachada;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class RodadaListar extends JInternalFrame {
	private Fachada fachada = null;
	private JTable tabelaRodada;
	private DefaultTableModel modeloTabelaRodada;
	private String[] colunaTabelaRodada;
	@SuppressWarnings("rawtypes")
	private JComboBox campoCompeticao;
	private JButton btnProcurar;
	private int[] valueCopeticao;
	private int[] valueRodada;
	@SuppressWarnings("rawtypes")
	private JComboBox campoRodada;

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
		
		JLabel lblTituloTabela = new JLabel("Digite o nome da competi\u00E7\u00E3o ou deixe em branco para listar todos:");
		lblTituloTabela.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTituloTabela.setBounds(10, 11, 391, 20);
		painelTabela.add(lblTituloTabela);
		
		btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				procurar();
			}
		});
		btnProcurar.setEnabled(false);
		btnProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProcurar.setBounds(534, 41, 89, 23);
		painelTabela.add(btnProcurar);
		
		JScrollPane scrollPaneRodada = new JScrollPane();
		scrollPaneRodada.setBounds(10, 73, 1044, 271);
		painelTabela.add(scrollPaneRodada);
		
		tabelaRodada = new JTable();
		tabelaRodada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colunaTabelaRodada = new String[] {"ID", "Competi\u00E7\u00E3o", "Rodada", "Jogo", "Data/Hora", "Clube 1", "Resultado 1", "Clube 2", "Resultado 2"};
		modeloTabelaRodada = new DefaultTableModel(new Object[][] {},colunaTabelaRodada){
			boolean[] columnEditables = new boolean[] {false, false, false, false, false, false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabelaRodada.setModel(modeloTabelaRodada);
		tabelaRodada.getColumnModel().getColumn(0).setPreferredWidth(52);
		tabelaRodada.getColumnModel().getColumn(1).setPreferredWidth(149);
		tabelaRodada.getColumnModel().getColumn(2).setPreferredWidth(93);
		tabelaRodada.getColumnModel().getColumn(3).setPreferredWidth(115);
		tabelaRodada.getColumnModel().getColumn(4).setPreferredWidth(188);
		tabelaRodada.getColumnModel().getColumn(5).setPreferredWidth(202);
		tabelaRodada.getColumnModel().getColumn(7).setPreferredWidth(185);
		tabelaRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneRodada.setViewportView(tabelaRodada);
		
		campoCompeticao = new JComboBox();
		campoCompeticao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (campoCompeticao.getSelectedIndex() != 0){
					btnProcurar.setEnabled(true);
				}else{
					campoRodada.removeAllItems();
					btnProcurar.setEnabled(false);
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
				if (campoCompeticao.getSelectedIndex() != 0){
					listaRodada();
				}else{
					btnProcurar.setEnabled(false);
				}
			}
		});
		campoRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoRodada.setBounds(465, 42, 59, 20);
		campoRodada.setMaximumRowCount(20);
		painelTabela.add(campoRodada);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 353, 1064, 36);
		getContentPane().add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 11, 89, 23);
		painelBotoes.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeletar.setBounds(109, 12, 89, 23);
		painelBotoes.add(btnDeletar);
		
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
			valueCopeticao = new int[lista.size()];
			for (int i = 0; i < lista.size(); i++) {
				campoCompeticao.addItem(lista.get(i).getNome());
				valueCopeticao[i] = lista.get(i).getId();
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
		limparTabela();
		ArrayList<Integer> lista = new ArrayList<>();
		int idCompeticao = valueCopeticao[campoCompeticao.getSelectedIndex()];
		try {
			campoRodada.addItem("");
			lista = fachada.procurarRodada(idCompeticao, 0);
			valueRodada = new int[lista.size()];
			for (int i = 0; i < lista.size(); i++) {
				campoRodada.addItem(lista.get(i));
				valueRodada[i] = lista.get(i);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (RodadaNaoCadastradaException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao listar as rodadas!");
		}
	}
	
	private void procurar(){
		int idCompeticao = campoCompeticao.getSelectedIndex();
		int numeroDaRodada = campoCompeticao.getSelectedIndex();
		try {
			fachada.procurarRodada(valueCopeticao[idCompeticao], valueRodada[numeroDaRodada]);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (RodadaNaoCadastradaException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao listar as competições!");
		}
	}
	
}

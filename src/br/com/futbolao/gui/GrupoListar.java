package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import br.com.futbolao.competicao.Competicao;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.GrupoNaoCadastradoException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.grupo.Grupo;

@SuppressWarnings("serial")
public class GrupoListar extends JInternalFrame {
	private Fachada fachada = null;
	private JTable tabelaGrupo;
	private DefaultTableModel modeloTabelaGrupo;
	private String[] colunaTabelaGrupo;
	private int[] valueCopeticao;
	private int[] valueRodada;
	@SuppressWarnings("rawtypes")
	private JComboBox campoCompeticao;
	@SuppressWarnings("rawtypes")
	private JComboBox campoRodada;
	private JRadioButton rdbtnGruposEncerrados;
	private JRadioButton rdbtnGruposAtivos;
	private ButtonGroup grupoRadio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrupoListar frame = new GrupoListar();
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
	public GrupoListar() {
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			try {
				throw new ErroAoInstanciarFachadaException();
			} catch (ErroAoInstanciarFachadaException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			}
		}
		setTitle("Listar Grupo");
		setClosable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel painelTabela = new JPanel();
		painelTabela.setBackground(Color.WHITE);
		painelTabela.setBounds(10, 11, 964, 346);
		getContentPane().add(painelTabela);
		painelTabela.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 944, 268);
		painelTabela.add(scrollPane);
		
		tabelaGrupo = new JTable();
		tabelaGrupo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colunaTabelaGrupo = new String[] {"ID", "Valor Aposta", "Limite Apostas", "Apostas por Apostador", "Lucro ADM%", "Encerramento", "Pts. por resultado", "Pts. por placar"};
		modeloTabelaGrupo = new DefaultTableModel(new Object[][] {},colunaTabelaGrupo){
			boolean[] columnEditables = new boolean[] {false, false, false, false, false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabelaGrupo.setModel(modeloTabelaGrupo);
		tabelaGrupo.getColumnModel().getColumn(0).setPreferredWidth(56);
		tabelaGrupo.getColumnModel().getColumn(2).setPreferredWidth(90);
		tabelaGrupo.getColumnModel().getColumn(3).setPreferredWidth(125);
		tabelaGrupo.getColumnModel().getColumn(5).setPreferredWidth(122);
		tabelaGrupo.getColumnModel().getColumn(6).setPreferredWidth(90);
		tabelaGrupo.getColumnModel().getColumn(7).setPreferredWidth(90);
		tabelaGrupo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(tabelaGrupo);
		
		JLabel label = new JLabel("Selecione a competi\u00E7\u00E3o:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(10, 11, 133, 20);
		painelTabela.add(label);
		
		JLabel label_1 = new JLabel("Selecione a rodada:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(465, 11, 111, 20);
		painelTabela.add(label_1);
		
		campoRodada = new JComboBox();
		campoRodada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (campoRodada.getSelectedIndex() > 0){
					limparTabela();
					procurar();
				}else{
					limparTabela();
				}
			}
		});
		campoRodada.setMaximumRowCount(20);
		campoRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoRodada.setBounds(465, 38, 111, 20);
		painelTabela.add(campoRodada);
		
		campoCompeticao = new JComboBox();
		campoCompeticao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (campoCompeticao.getSelectedIndex() > 0){
					listaRodada();
					limparTabela();
				}else{
					campoRodada.removeAllItems();
					limparTabela();
				}
			}
		});
		campoCompeticao.setMaximumRowCount(20);
		campoCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCompeticao.setBounds(10, 38, 445, 20);
		painelTabela.add(campoCompeticao);
		
		rdbtnGruposAtivos = new JRadioButton("Grupos Ativos");
		rdbtnGruposAtivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				campoCompeticao.removeAllItems();
				campoRodada.removeAllItems();
				limparTabela();
				listaCompeticao();
			}
		});
		rdbtnGruposAtivos.setSelected(true);
		rdbtnGruposAtivos.setBounds(582, 38, 109, 23);
		painelTabela.add(rdbtnGruposAtivos);
		
		rdbtnGruposEncerrados = new JRadioButton("Grupos Encerrados");
		rdbtnGruposEncerrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				campoCompeticao.removeAllItems();
				campoRodada.removeAllItems();
				limparTabela();
				listaCompeticao();
			}
		});
		rdbtnGruposEncerrados.setBounds(693, 38, 133, 23);
		painelTabela.add(rdbtnGruposEncerrados);
		
		grupoRadio = new ButtonGroup();
		grupoRadio.add(rdbtnGruposAtivos);
		grupoRadio.add(rdbtnGruposEncerrados);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 357, 964, 38);
		getContentPane().add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 11, 89, 23);
		painelBotoes.add(btnAlterar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemover.setBounds(109, 11, 89, 23);
		painelBotoes.add(btnRemover);
		setBounds(100, 100, 1000, 430);
		
		listaCompeticao();

	}

	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void limparTabela(){
		this.modeloTabelaGrupo.setNumRows(0);
	}
	
	@SuppressWarnings({ "unchecked" })
	private void  listaCompeticao(){
		char ativo = 'S';
		if (rdbtnGruposAtivos.isSelected()) {
			ativo = 'S';
		}else{
			ativo = 'N';
		}
		limparTabela();
		ArrayList<Competicao> lista = new ArrayList<>();
		try {
			campoCompeticao.addItem("");
			lista = fachada.listarCompeticaoComGrupo(ativo);
			valueCopeticao = new int[(lista.size()+1)];
			for (int i = 1; i <= lista.size(); i++) {
				campoCompeticao.addItem(lista.get(i-1).getNome());
				valueCopeticao[i] = lista.get(i-1).getId();
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
		int idCompeticao = valueCopeticao[campoCompeticao.getSelectedIndex()];
		try {
			campoRodada.addItem("");
			lista = fachada.listarRodadaPorCompeticaoComGrupo(idCompeticao);
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
		int idCompeticao = campoCompeticao.getSelectedIndex();
		int numeroDaRodada = campoRodada.getSelectedIndex();
		ArrayList<Grupo> lista = new ArrayList<>();
		try {
			lista = fachada.procurarGrupoPorCompeticao(valueCopeticao[idCompeticao], valueRodada[numeroDaRodada]);
			for (Grupo grupo: lista) {
				Vector vector = new Vector();
				vector.add(grupo.getId());
				vector.add(grupo.getValorAposta());
				vector.add(grupo.getLimiteApostas());
				vector.add(grupo.getLimiteApostasPorApostador());
				vector.add(grupo.getPercentualLucroAdministrador());
				vector.add(grupo.getDataEncerramentoAposta());
				vector.add(grupo.getPontuacaoPorResultado());
				vector.add(grupo.getPontuacaoPorPlacar());
				modeloTabelaGrupo.addRow(vector);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (GrupoNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao listar os grupos!");
			e.printStackTrace();
		}
	}
}

package br.com.futbolao.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.com.futbolao.aposta.Aposta;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.GrupoNaoCadastradoException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.grupo.Grupo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class ApostaListar extends JInternalFrame {
	private Fachada fachada = null;
	private JTable tabelaAposta;
	private DefaultTableModel modeloTabelaAposta;
	private String[] colunaTabelaAposta;
	private JTextField campoID;
	private JTextPane textoDadosGrupo;
	private JRadioButton rdbtnApostador;
	private JRadioButton rdbtnGrupo;
	private ButtonGroup grupoRadio;
	private ButtonGroup grupoRadio2;
	private JRadioButton rdbtnAtivas;
	private JRadioButton rdbtnInativas;
	private String nomeCompeticao = "";
	private String numeroRodada = "";
	private String limiteApostas = "";
	private String limiteApostasPorApostador = "";
	private String valorAposta = "";
	private String taxaDeAdministracao = "";
	private String pontuacaoPorPlacar = "";
	private String pontuacaoPorResultado = "";
	private String dataEncerramentoAposta = "";
	private String dadosCompeticao = "";
	private DecimalFormat formatacaoDinheiro =  new DecimalFormat("#,##0.00;(#,##0.00)", new DecimalFormatSymbols(new Locale("pt", "BR")));
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApostaListar frame = new ApostaListar();
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
	public ApostaListar() {
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			try {
				throw new ErroAoInstanciarFachadaException();
			} catch (ErroAoInstanciarFachadaException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			}
		}
		setTitle("Listar Apostas");
		setClosable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel painelTabela = new JPanel();
		painelTabela.setBackground(Color.WHITE);
		painelTabela.setBounds(10, 11, 843, 392);
		getContentPane().add(painelTabela);
		painelTabela.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 111, 823, 270);
		painelTabela.add(scrollPane);
		
		tabelaAposta = new JTable();
		tabelaAposta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colunaTabelaAposta = new String[] {"ID", "ID Apostador", "Nome Apostador", "ID Grupo", "Valor", "Data Aposta", "Ativa"};
		modeloTabelaAposta = new DefaultTableModel(new Object[][] {},colunaTabelaAposta){
			boolean[] columnEditables = new boolean[] {false, false, false, false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabelaAposta.setModel(modeloTabelaAposta);
		tabelaAposta.getColumnModel().getColumn(0).setPreferredWidth(44);
		tabelaAposta.getColumnModel().getColumn(2).setPreferredWidth(250);
		tabelaAposta.getColumnModel().getColumn(4).setPreferredWidth(80);
		tabelaAposta.getColumnModel().getColumn(5).setPreferredWidth(124);
		tabelaAposta.getColumnModel().getColumn(6).setPreferredWidth(44);
		tabelaAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(tabelaAposta);
		
		JLabel lblID = new JLabel("Digite o ID : ");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblID.setBounds(10, 79, 71, 21);
		painelTabela.add(lblID);
		
		campoID = new JTextField();
		campoID.setBounds(84, 80, 136, 20);
		painelTabela.add(campoID);
		campoID.setColumns(10);
		
		rdbtnApostador = new JRadioButton("Apostador");
		rdbtnApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnApostador.setBounds(121, 7, 99, 21);
		painelTabela.add(rdbtnApostador);
		
		rdbtnGrupo = new JRadioButton("Grupo");
		rdbtnGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textoDadosGrupo.setText("");
				procurarGrupo();
			}
		});
		rdbtnGrupo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnGrupo.setBounds(10, 7, 99, 21);
		rdbtnGrupo.setSelected(true);
		painelTabela.add(rdbtnGrupo);
		
		grupoRadio = new ButtonGroup();
		grupoRadio.add(rdbtnApostador);
		grupoRadio.add(rdbtnGrupo);
		
		textoDadosGrupo = new JTextPane();
		textoDadosGrupo.setEnabled(false);
		textoDadosGrupo.setEditable(false);
		textoDadosGrupo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textoDadosGrupo.setBounds(272, 7, 349, 93);
		painelTabela.add(textoDadosGrupo);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnApostador.isSelected()){
					listarApostasPorApostador();
				}else if(rdbtnGrupo.isSelected()) {
					listarApostasPorGrupo();
				}
			}
		});
		btnListar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListar.setBounds(651, 77, 89, 23);
		painelTabela.add(btnListar);
		
		rdbtnAtivas = new JRadioButton("Ativas");
		rdbtnAtivas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnAtivas.setBounds(10, 41, 99, 21);
		rdbtnAtivas.setSelected(true);
		painelTabela.add(rdbtnAtivas);
		
		rdbtnInativas = new JRadioButton("Inativas");
		rdbtnInativas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnInativas.setBounds(121, 41, 99, 21);
		painelTabela.add(rdbtnInativas);
		
		grupoRadio2 = new ButtonGroup();
		grupoRadio2.add(rdbtnAtivas);
		grupoRadio2.add(rdbtnInativas);
		
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 403, 843, 37);
		getContentPane().add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemover.setBounds(10, 11, 89, 23);
		painelBotoes.add(btnRemover);

		
		
	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void limparTabela(){
		this.modeloTabelaAposta.setNumRows(0);
	}
	
	private void procurarGrupo(){
		limparTabela();
		long idGrupo = Long.parseLong(campoID.getText());
		try {
			Grupo grupo = fachada.procurarGrupoPorId(idGrupo);
			nomeCompeticao = grupo.getNomeCompeticao();
			numeroRodada = String.valueOf(grupo.getIdRodada());
			limiteApostas = String.valueOf(grupo.getLimiteApostas());
			limiteApostasPorApostador = String.valueOf(grupo.getLimiteApostasPorApostador());
			taxaDeAdministracao = String.valueOf(grupo.getPercentualLucroAdministrador());
			valorAposta = formatacaoDinheiro.format(grupo.getValorAposta());
			pontuacaoPorPlacar = String.valueOf(grupo.getPontuacaoPorPlacar());
			pontuacaoPorResultado = String.valueOf(grupo.getPontuacaoPorResultado());
			dataEncerramentoAposta = String.valueOf(grupo.getDataEncerramentoAposta());
			dadosCompeticao = "Competi\u00E7\u00E3o: " + nomeCompeticao 
					 +"  |  Rodada: " + numeroRodada  
					 +"\r\nLimite de apostas do grupo: " + limiteApostas
					 + "  |  Limite de aposta por apostador: " + limiteApostasPorApostador
					 + "\r\nPontua\u00E7\u00E3o por placar: " + pontuacaoPorPlacar
					 + "  |  Pontua\u00E7\u00E3o po Resultado: " + pontuacaoPorResultado
					 + "\r\nTaxa de Administra\u00E7\u00E3o: " + taxaDeAdministracao + "%"
					 + "  |  Valor da Aposta: " + valorAposta
					 + "\r\nEncerramento das apostas: " + dataEncerramentoAposta;
			textoDadosGrupo.setText(dadosCompeticao);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (GrupoNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao procurar o grupo");
		}
	}
	
	private void listarApostasPorApostador(){
		long id = Long.parseLong(campoID.getText());
		char ativa = 'S';
		if (rdbtnAtivas.isSelected()) {
			ativa = 'S';
		}else{
			ativa = 'N';
		}
		limparTabela();
		ArrayList<Aposta> lista = new ArrayList<>();
		
		
	}
	
	private void listarApostasPorGrupo(){
		long id = Long.parseLong(campoID.getText());
		char ativa = 'S';
		if (rdbtnAtivas.isSelected()) {
			ativa = 'S';
		}else{
			ativa = 'N';
		}
		limparTabela();
	}
}


package br.com.futbolao.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.com.futbolao.apostador.Apostador;
import br.com.futbolao.exception.ApostadorNaoCadastradoException;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.GrupoEncerradoException;
import br.com.futbolao.exception.GrupoNaoCadastradoException;
import br.com.futbolao.exception.IdInvalidoException;
import br.com.futbolao.exception.NaoFoiPossivelRealizarApostaException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;
import br.com.futbolao.exception.TotalDeApostasDoGrupoAtingidoException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.grupo.Grupo;
import br.com.futbolao.rodada.Rodada;
import br.com.futbolao.util.EditorDeTabela;
import br.com.futbolao.util.FormataCampoApenasNumeros;
import br.com.futbolao.util.VerificaData;

public class ApostaMostrar extends JInternalFrame {
	Fachada fachada = null;
	private JTable tabelaRodada;
	private DefaultTableModel modeloTabelaRodada;
	private String[] colunaTabelaRodada;
	private JTextField campoNomeApostador;
	private JTextPane textoDadosGrupo;
	private String nomeCompeticao = "";
	private String numeroRodada = "";
	private String limiteApostas = "";
	private String limiteApostasPorApostador = "";
	private String valorAposta = "";
	private double saldoApostadorDouble;
	private double valorApostaDouble;
	private int limiteApostasPorApostadorInt;
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
					ApostaMostrar frame = new ApostaMostrar(0, 0);
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
	public ApostaMostrar(final long idGrupo, final long idApostador) {
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			try {
				throw new ErroAoInstanciarFachadaException();
			} catch (ErroAoInstanciarFachadaException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			}
		}
		String grupo = String .valueOf(idGrupo);
		String apostador = String.valueOf(idApostador);
		setClosable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		setTitle("Cadastrar Aposta");
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 559, 539);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setBackground(Color.WHITE);
		painelForm.setBounds(10, 11, 523, 472);
		getContentPane().add(painelForm);
		painelForm.setLayout(null);
		
		campoNomeApostador = new JTextField();
		campoNomeApostador.setEnabled(false);
		campoNomeApostador.setEditable(false);
		campoNomeApostador.setFont(new Font("Tahoma", Font.BOLD, 12));
		campoNomeApostador.setBounds(10, 41, 399, 20);
		painelForm.add(campoNomeApostador);
		campoNomeApostador.setColumns(10);
		
		JLabel lblNomeDoApostador = new JLabel("Nome do Apostador:");
		lblNomeDoApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNomeDoApostador.setBounds(10, 11, 114, 14);
		painelForm.add(lblNomeDoApostador);
		
		JLabel lblInfoDoGrupo = new JLabel("Informa\u00E7\u00F5es do Grupo:");
		lblInfoDoGrupo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInfoDoGrupo.setBounds(10, 78, 181, 14);
		painelForm.add(lblInfoDoGrupo);
		
		textoDadosGrupo = new JTextPane();
		textoDadosGrupo.setEnabled(false);
		textoDadosGrupo.setEditable(false);
		textoDadosGrupo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textoDadosGrupo.setText(dadosCompeticao);
		textoDadosGrupo.setBounds(10, 103, 399, 81);
		painelForm.add(textoDadosGrupo);
		
		JLabel lblJogos = new JLabel("JOGOS");
		lblJogos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblJogos.setBounds(229, 195, 40, 14);
		painelForm.add(lblJogos);
		
		JScrollPane scrollPaneTabelaJogos = new JScrollPane();
		scrollPaneTabelaJogos.setBounds(10, 227, 503, 234);
		painelForm.add(scrollPaneTabelaJogos);
		
		tabelaRodada = new JTable();
		tabelaRodada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colunaTabelaRodada = new String[] {"", "", "Clube1", "Resultado", "X", "Resultado", "", "Clube 2"};
		modeloTabelaRodada = new DefaultTableModel(new Object[][] {},colunaTabelaRodada){
			boolean[] columnEditables = new boolean[] {false, false, false, true, false, true, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabelaRodada.setModel(modeloTabelaRodada);
		tabelaRodada.getColumnModel().getColumn(0).setMinWidth(0);
		tabelaRodada.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabelaRodada.getColumnModel().getColumn(0).setMaxWidth(0);
		tabelaRodada.getColumnModel().getColumn(1).setMinWidth(0);
		tabelaRodada.getColumnModel().getColumn(1).setPreferredWidth(0);
		tabelaRodada.getColumnModel().getColumn(1).setMaxWidth(0);
		tabelaRodada.getColumnModel().getColumn(2).setResizable(false);
		tabelaRodada.getColumnModel().getColumn(2).setPreferredWidth(170);
		tabelaRodada.getColumnModel().getColumn(3).setResizable(false);
		tabelaRodada.getColumnModel().getColumn(3).setPreferredWidth(60);
		tabelaRodada.getColumnModel().getColumn(4).setResizable(false);
		tabelaRodada.getColumnModel().getColumn(4).setPreferredWidth(20);
		tabelaRodada.getColumnModel().getColumn(5).setResizable(false);
		tabelaRodada.getColumnModel().getColumn(5).setPreferredWidth(60);
		tabelaRodada.getColumnModel().getColumn(6).setMinWidth(0);
		tabelaRodada.getColumnModel().getColumn(6).setPreferredWidth(0);
		tabelaRodada.getColumnModel().getColumn(6).setMaxWidth(0);
		tabelaRodada.getColumnModel().getColumn(7).setResizable(false);
		tabelaRodada.getColumnModel().getColumn(7).setPreferredWidth(190);
		tabelaRodada.getColumnModel().getColumn(3).setCellEditor(new EditorDeTabela());
		tabelaRodada.getColumnModel().getColumn(5).setCellEditor(new EditorDeTabela());
		scrollPaneTabelaJogos.setViewportView(tabelaRodada);
		painelForm.setBackground(Color.WHITE);

		procurarApostador(idApostador);
		procurarGrupo(idGrupo, idApostador);
	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
	
	private void limparTabela(){
		this.modeloTabelaRodada.setNumRows(0);
	}
	
	@SuppressWarnings("deprecation")
	private void limparCampos(){
		campoNomeApostador.setText("");
		nomeCompeticao = "";
		numeroRodada = "";
		limiteApostas = "";
		limiteApostasPorApostador = "";
		taxaDeAdministracao = "";
		pontuacaoPorPlacar = "";
		pontuacaoPorResultado = "";
		dataEncerramentoAposta = "";
		textoDadosGrupo.setText("");
		limparTabela();
		
	}
	
	public boolean validarCampos(){
		if (tabelaRodada.getRowCount() == 0) {
			try {
				throw new NaoFoiPossivelRealizarApostaException();
			} catch (NaoFoiPossivelRealizarApostaException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			}
			return false;
		} else {
			return true;
		}
		
	}
	
	private void procurarApostador(long idApostador){
		//long idApostador = Long.parseLong(campoIdApostador.getText());
		try {
			Apostador apostador = fachada.procurarApostadorPorId(idApostador);
			campoNomeApostador.setText(apostador.getNome());
			saldoApostadorDouble = apostador.getSaldo();
		} catch (IdInvalidoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (ApostadorNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao procurar o apostador");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	private void procurarGrupo(long idGrupo, long idApostador){
		limparTabela();
		try {
			Grupo grupo = fachada.procurarGrupoPorId(idGrupo);
			String vencimento = grupo.getDataEncerramentoAposta().substring(6,10)
					+ grupo.getDataEncerramentoAposta().substring(3,5) + grupo.getDataEncerramentoAposta().substring(0,2);
			String dataAtual = grupo.getDataAtual().replace("-", "");
			if (!(grupo.getLimiteApostas() > grupo.getTotalApostas())) {
				try {
					throw new TotalDeApostasDoGrupoAtingidoException();
				} catch (TotalDeApostasDoGrupoAtingidoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				return;
			}
			if(VerificaData.verifica(vencimento, dataAtual) == false){
				try {
					throw new GrupoEncerradoException();
				} catch (GrupoEncerradoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				return;
			}
			nomeCompeticao = grupo.getNomeCompeticao();
			numeroRodada = String.valueOf(grupo.getIdRodada());
			limiteApostas = String.valueOf(grupo.getLimiteApostas());
			limiteApostasPorApostador = String.valueOf(grupo.getLimiteApostasPorApostador());
			limiteApostasPorApostadorInt = grupo.getLimiteApostasPorApostador();
			taxaDeAdministracao = String.valueOf(grupo.getPercentualLucroAdministrador());
			valorAposta = formatacaoDinheiro.format(grupo.getValorAposta());
			valorApostaDouble = grupo.getValorAposta();
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
			try {
				ArrayList<Rodada> lista = new ArrayList<>();
				lista = fachada.procurarRodada(grupo.getIdCompeticao(), grupo.getIdRodada());
				for (Rodada rodada: lista) {
					Vector vector = new Vector();
					vector.add(rodada.getId());
					vector.add(rodada.getClube1());
					vector.add(rodada.getNomeClube1());
					vector.add("");
					vector.add("X");
					vector.add("");
					vector.add(rodada.getClube2());
					vector.add(rodada.getNomeClube2());
					modeloTabelaRodada.addRow(vector);
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			} catch (RodadaNaoCadastradaException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao procurar os jogos do grupo");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (GrupoNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao procurar o grupo");
		}
	}

}

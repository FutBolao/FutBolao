package br.com.futbolao.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import br.com.futbolao.aposta.Aposta;
import br.com.futbolao.apostador.Apostador;
import br.com.futbolao.exception.ApostaNaoCadastradaException;
import br.com.futbolao.exception.ApostadorNaoCadastradoException;
import br.com.futbolao.exception.CadastroEfetuadoComSucessoException;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.GrupoEncerradoException;
import br.com.futbolao.exception.GrupoNaoCadastradoException;
import br.com.futbolao.exception.IdInvalidoException;
import br.com.futbolao.exception.NaoFoiPossivelRealizarApostaException;
import br.com.futbolao.exception.PreenchaTodosOsResultadosException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;
import br.com.futbolao.exception.SaldoInsulficienteException;
import br.com.futbolao.exception.TotalDeApostasDoGrupoAtingidoException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.grupo.Grupo;
import br.com.futbolao.rodada.Rodada;
import br.com.futbolao.util.EditorDeTabela;
import br.com.futbolao.util.FormataCampoApenasNumeros;
import br.com.futbolao.util.VerificaData;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ApostaCadastrar extends JInternalFrame {
	Fachada fachada = null;
	private JTable tabelaRodada;
	private DefaultTableModel modeloTabelaRodada;
	private String[] colunaTabelaRodada;
	private JTextField campoIdApostador;
	private JTextField campoNomeApostador;
	private JTextField campoIdGrupo;
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
					ApostaCadastrar frame = new ApostaCadastrar();
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
	public ApostaCadastrar() {
		setClosable(true);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		setTitle("Cadastrar Aposta");
		getContentPane().setBackground(Color.WHITE);
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			try {
				throw new ErroAoInstanciarFachadaException();
			} catch (ErroAoInstanciarFachadaException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			}
		}
		setBounds(100, 100, 559, 539);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setBackground(Color.WHITE);
		painelForm.setBounds(10, 11, 523, 453);
		getContentPane().add(painelForm);
		painelForm.setLayout(null);
		
		JLabel lblIdDoApostador = new JLabel("ID do Apostador:");
		lblIdDoApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdDoApostador.setBounds(10, 36, 94, 14);
		painelForm.add(lblIdDoApostador);
		
		campoIdApostador = new JTextField();
		campoIdApostador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				if (tecla.getKeyCode() == KeyEvent.VK_ENTER && !campoIdApostador.getText().equals("0")){
					procurarApostador();
				}
			}
		});
		campoIdApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoIdApostador.setBounds(10, 61, 94, 20);
		painelForm.add(campoIdApostador);
		campoIdApostador.setDocument(new FormataCampoApenasNumeros(13));
		campoIdApostador.setColumns(10);
		
		campoNomeApostador = new JTextField();
		campoNomeApostador.setEnabled(false);
		campoNomeApostador.setEditable(false);
		campoNomeApostador.setFont(new Font("Tahoma", Font.BOLD, 12));
		campoNomeApostador.setBounds(114, 61, 399, 20);
		painelForm.add(campoNomeApostador);
		campoNomeApostador.setColumns(10);
		
		JLabel lblNomeDoApostador = new JLabel("Nome do Apostador:");
		lblNomeDoApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNomeDoApostador.setBounds(114, 37, 114, 14);
		painelForm.add(lblNomeDoApostador);
		
		JLabel lblDigiteOId = new JLabel("Digite o ID do apostador e depois tecle enter, depois digite o ID do grupo e tecle enter.");
		lblDigiteOId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDigiteOId.setBounds(10, 11, 503, 14);
		painelForm.add(lblDigiteOId);
		
		JLabel lblIdDoGrupo = new JLabel("ID do Grupo:");
		lblIdDoGrupo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdDoGrupo.setBounds(10, 92, 71, 14);
		painelForm.add(lblIdDoGrupo);
		
		campoIdGrupo = new JTextField();
		campoIdGrupo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				if (tecla.getKeyCode() == KeyEvent.VK_ENTER && !campoIdGrupo.getText().equals("0")){
					procurarGrupo();
				}
			}
		});
		campoIdGrupo.setBounds(10, 117, 94, 20);
		painelForm.add(campoIdGrupo);
		campoIdGrupo.setDocument(new FormataCampoApenasNumeros(13));
		campoIdGrupo.setColumns(10);
		
		textoDadosGrupo = new JTextPane();
		textoDadosGrupo.setEnabled(false);
		textoDadosGrupo.setEditable(false);
		textoDadosGrupo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textoDadosGrupo.setText(dadosCompeticao);
		textoDadosGrupo.setBounds(114, 92, 399, 81);
		painelForm.add(textoDadosGrupo);
		
		JLabel lblJogos = new JLabel("JOGOS");
		lblJogos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblJogos.setBounds(229, 184, 40, 14);
		painelForm.add(lblJogos);
		
		JScrollPane scrollPaneTabelaJogos = new JScrollPane();
		scrollPaneTabelaJogos.setBounds(10, 213, 503, 229);
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
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelForm.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 460, 523, 42);
		getContentPane().add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrar();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(10, 11, 89, 23);
		painelBotoes.add(btnNewButton);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
			}
		});
		btnLimpar.setBounds(109, 12, 89, 23);
		painelBotoes.add(btnLimpar);

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
		campoIdApostador.setEditable(true);
		campoIdApostador.enable();
		campoIdApostador.setText("");
		campoNomeApostador.setText("");
		campoIdGrupo.setEditable(true);
		campoIdGrupo.enable();
		campoIdGrupo.setText("");
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
		String idApostador = campoIdApostador.getText();
		String idGrupo = campoIdGrupo.getText();
		if (idApostador.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoIdApostador.requestFocus();
			}
			return false;
		} else if (idGrupo.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoIdGrupo.requestFocus();
			}
			return false;
		} else if (tabelaRodada.getRowCount() == 0) {
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
	
	@SuppressWarnings("deprecation")
	private void procurarApostador(){
		long idApostador = Long.parseLong(campoIdApostador.getText());
		try {
			Apostador apostador = fachada.procurarApostadorPorId(idApostador);
			campoIdApostador.setEditable(false);
			campoIdApostador.disable();
			campoNomeApostador.setText(apostador.getNome());
			saldoApostadorDouble = apostador.getSaldo();
			campoIdGrupo.requestFocus();
		} catch (IdInvalidoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (ApostadorNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
			campoIdApostador.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao procurar o apostador");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	private void procurarGrupo(){
		limparTabela();
		long idGrupo = Long.parseLong(campoIdGrupo.getText());
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
				campoIdGrupo.requestFocus();
				return;
			}
			if(VerificaData.verifica(vencimento, dataAtual) == false){
				try {
					throw new GrupoEncerradoException();
				} catch (GrupoEncerradoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				campoIdGrupo.requestFocus();
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
			campoIdGrupo.setEditable(false);
			campoIdGrupo.disable();
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
			campoIdGrupo.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao procurar o grupo");
		}
	}
	
	private void cadastrar(){
		procurarGrupo();
		if (validarCampos()) {
			ArrayList<Aposta> apostas = new ArrayList<>();
			long idApostador = Long.parseLong(campoIdApostador.getText());
			long idGrupo = Long.parseLong(campoIdGrupo.getText());
			int linhas = tabelaRodada.getRowCount();
			try {
				if (!(fachada.totalDeApostasPoGrupo(idApostador, idGrupo) < limiteApostasPorApostadorInt)) {
					try {
						throw new TotalDeApostasDoGrupoAtingidoException();
					} catch (TotalDeApostasDoGrupoAtingidoException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					}
					limparCampos();
					return;
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao verificar quantas apostas o apostador já fez no grupo");
			}
			if (saldoApostadorDouble < valorApostaDouble) {
				try {
					throw new SaldoInsulficienteException();
				} catch (SaldoInsulficienteException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				limparCampos();
				return;
			}
			for (int i=0; i < linhas; i++) {
				if (tabelaRodada.getValueAt(i, 3) != null && tabelaRodada.getValueAt(i, 5) != null){
					if (!tabelaRodada.getValueAt(i, 3).equals("") && !tabelaRodada.getValueAt(i, 5).equals("")){
						int idJogo = Integer.parseInt(String.valueOf(tabelaRodada.getValueAt(i, 0)));
						int clube1 = Integer.parseInt(String.valueOf(tabelaRodada.getValueAt(i, 1)));
						int resultadoClube1 = Integer.parseInt(String.valueOf(tabelaRodada.getValueAt(i, 3)));
						int clube2 = Integer.parseInt(String.valueOf(tabelaRodada.getValueAt(i, 6)));
						int resultadoClube2 = Integer.parseInt(String.valueOf(tabelaRodada.getValueAt(i, 5)));
						apostas.add(new Aposta(0, idApostador, idGrupo, idJogo, clube1, resultadoClube1, clube2, resultadoClube2));
					} else {
						try {
							throw new PreenchaTodosOsResultadosException();
						} catch (PreenchaTodosOsResultadosException e) {
							JOptionPane.showMessageDialog(rootPane, e.getMessage());
						}
						return;
					}
				} else {
					try {
						throw new PreenchaTodosOsResultadosException();
					} catch (PreenchaTodosOsResultadosException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					}
					return;
				}
			}
			try {
				fachada.cadastrarAposta(apostas);
				try {
					throw new CadastroEfetuadoComSucessoException();
				} catch (CadastroEfetuadoComSucessoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				limparCampos();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (ApostaNaoCadastradaException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao cadastrar a aposta");
			}
		}
	}
}

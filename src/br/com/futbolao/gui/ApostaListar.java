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
import br.com.futbolao.apostador.Apostador;
import br.com.futbolao.exception.ApostaNaoCadastradaException;
import br.com.futbolao.exception.ApostadorNaoCadastradoException;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.ConfirmacaoDeExclusaoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.GrupoNaoCadastradoException;
import br.com.futbolao.exception.IdInvalidoException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.grupo.Grupo;
import br.com.futbolao.util.FormataCampoApenasNumeros;

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
import java.util.Vector;

@SuppressWarnings("serial")
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
	private String campoNomeApostador;
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
		System.out.println("abriu");
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
		setClosable(true);
		setTitle("Listar Apostas");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		setBounds(100, 100, 879, 494);
		getContentPane().setLayout(null);
		
		JPanel painelTabela = new JPanel();
		painelTabela.setBackground(Color.WHITE);
		painelTabela.setBounds(10, 11, 843, 413);
		getContentPane().add(painelTabela);
		painelTabela.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 131, 823, 270);
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
		lblID.setBounds(10, 69, 71, 21);
		painelTabela.add(lblID);
		
		campoID = new JTextField();
		campoID.setBounds(10, 101, 99, 20);
		painelTabela.add(campoID);
		campoID.setDocument(new FormataCampoApenasNumeros(13));
		campoID.setColumns(10);
		
		rdbtnApostador = new JRadioButton("Apostador");
		rdbtnApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnApostador.setBounds(121, 7, 99, 21);
		painelTabela.add(rdbtnApostador);
		
		rdbtnGrupo = new JRadioButton("Grupo");
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
		textoDadosGrupo.setBounds(226, 7, 421, 113);
		painelTabela.add(textoDadosGrupo);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				procurar();
			}
		});
		btnListar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListar.setBounds(119, 99, 78, 23);
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
		painelBotoes.setBounds(10, 424, 843, 37);
		getContentPane().add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remover();
			}
		});
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemover.setBounds(10, 11, 89, 23);
		painelBotoes.add(btnRemover);
		
		JButton btnVerAposta = new JButton("Ver Aposta");
		btnVerAposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarAposta();
			}
		});
		btnVerAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVerAposta.setBounds(149, 12, 115, 23);
		painelBotoes.add(btnVerAposta);
	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private boolean validaCampos(){
		String id = campoID.getText();
		if (id.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			}
			return false;
		}else{
			return true;
		}
	}
	
	private void limparTabela(){
		this.modeloTabelaAposta.setNumRows(0);
		textoDadosGrupo.setText("");
	}
	
	private void procurarApostador(){
		limparTabela();
		long idApostador = Long.parseLong(campoID.getText());
		try {
			Apostador apostador = fachada.procurarApostadorPorId(idApostador);
			campoNomeApostador = apostador.getNome();
			textoDadosGrupo.setText(campoNomeApostador);
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void listarApostasPorApostador(){
		long idApostador = Long.parseLong(campoID.getText());
		char ativa = 'S';
		if (rdbtnAtivas.isSelected()) {
			ativa = 'S';
		}else{
			ativa = 'N';
		}
		try {
			procurarApostador();
			ArrayList<Aposta> lista = new ArrayList<>();
			lista = fachada.procurarApostaPorApostador(idApostador, ativa);
			for (Aposta aposta: lista) {
				Vector vector = new Vector();
				vector.add(aposta.getId());
				vector.add(aposta.getIdApostador());
				vector.add(aposta.getNomeApostador());
				vector.add(aposta.getIdGrupo());
				vector.add(formatacaoDinheiro.format(aposta.getValorAposta()));
				vector.add(aposta.getDataAposta());
				vector.add(aposta.getAtiva());
				modeloTabelaAposta.addRow(vector);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (ApostaNaoCadastradaException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao listar as apostas");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void listarApostasPorGrupo(){
		long idGrupo = Long.parseLong(campoID.getText());
		char ativa = 'S';
		if (rdbtnAtivas.isSelected()) {
			ativa = 'S';
		}else{
			ativa = 'N';
		}
		try {
			procurarGrupo();
			ArrayList<Aposta> lista = new ArrayList<>();
			lista = fachada.procurarApostaPorGrupo(idGrupo, ativa);
			for (Aposta aposta: lista) {
				Vector vector = new Vector();
				vector.add(aposta.getId());
				vector.add(aposta.getIdApostador());
				vector.add(aposta.getNomeApostador());
				vector.add(aposta.getIdGrupo());
				vector.add(formatacaoDinheiro.format(aposta.getValorAposta()));
				vector.add(aposta.getDataAposta());
				vector.add(aposta.getAtiva());
				modeloTabelaAposta.addRow(vector);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (ApostaNaoCadastradaException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao listar as apostas");
		}
	}
	
	private void procurar(){
		if (validaCampos()) {
			if (rdbtnGrupo.isSelected()) {
				listarApostasPorGrupo();
			}else{
				listarApostasPorApostador();
			}
		}
	}
	
	private void remover(){
		if (tabelaAposta.getSelectedRowCount() == 1) {
			try {
				throw new ConfirmacaoDeExclusaoException();
			} catch (ConfirmacaoDeExclusaoException ex) {
				int confirmacao = JOptionPane.showConfirmDialog(rootPane, ex.getMessage(), "Alerta", JOptionPane.YES_NO_OPTION);
				if (confirmacao == 0) {
					try {
						int linha = tabelaAposta.getSelectedRow();
						long id = (long)tabelaAposta.getValueAt(linha, 0);
						fachada.deletarAposta(id);
						procurar();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					} catch (ApostaNaoCadastradaException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao deletar a aposta!");
					}
				}
			}
		}
	}
	
	private void mostrarAposta(){
		if (tabelaAposta.getSelectedRowCount() == 1){
			int linha = tabelaAposta.getSelectedRow();
			long idApostador = (long) tabelaAposta.getValueAt(linha, 3);
			long idGrupo = (long) tabelaAposta.getValueAt(linha, 1);
			//new ApostaMostrar(idApostador, idGrupo).setVisible(true);
			
			
			ApostaMostrar apostaMostrar = new ApostaMostrar(idApostador, idGrupo);
			Principal.desktopPane.add(apostaMostrar);
			apostaMostrar.setVisible(true);
			apostaMostrar.setPosicao();
		}
	}
}


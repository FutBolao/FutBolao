package br.com.futbolao.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.GrupoNaoCadastradoException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.ganhadores.Ganhador;
import br.com.futbolao.grupo.Grupo;
import br.com.futbolao.util.FormataCampoApenasNumeros;

import javax.swing.JLabel;
import javax.swing.JTextField;
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
public class GanhadorListar extends JInternalFrame {
	private Fachada fachada = null;
	private JTable tabelaAposta;
	private DefaultTableModel modeloTabelaGanhador;
	private String[] colunaTabelaAposta;
	private JTextField campoID;
	private JTextPane textoDadosGrupo;
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
					GanhadorListar frame = new GanhadorListar();
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
	public GanhadorListar() {
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
		setBounds(100, 100, 879, 454);
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
		colunaTabelaAposta = new String[] {"ID Apostador", "Nome Apostador", "ID Grupo", "Pontos", "Valor"};
		modeloTabelaGanhador = new DefaultTableModel(new Object[][] {},colunaTabelaAposta){
			boolean[] columnEditables = new boolean[] {false, false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabelaAposta.setModel(modeloTabelaGanhador);
		tabelaAposta.getColumnModel().getColumn(0).setPreferredWidth(44);
		tabelaAposta.getColumnModel().getColumn(1).setPreferredWidth(250);
		tabelaAposta.getColumnModel().getColumn(4).setPreferredWidth(80);
		tabelaAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(tabelaAposta);
		
		JLabel lblID = new JLabel("Digite o ID do Grupo:");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblID.setBounds(10, 7, 139, 21);
		painelTabela.add(lblID);
		
		campoID = new JTextField();
		campoID.setBounds(10, 39, 206, 20);
		painelTabela.add(campoID);
		campoID.setDocument(new FormataCampoApenasNumeros(13));
		campoID.setColumns(10);
		
		
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
		btnListar.setBounds(10, 70, 206, 23);
		painelTabela.add(btnListar);
		
	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private boolean validaCampos(){
		String id = campoID.getText();
		if (id.equals("")) {
			System.out.println("ss");
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
		this.modeloTabelaGanhador.setNumRows(0);
		textoDadosGrupo.setText("");
	}
	
	private void procurarGrupo(){
		limparTabela();
		long idGrupo = Long.parseLong(campoID.getText());
		if(idGrupo > 0){
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
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void procurar(){
		if(validaCampos()){
			long idGrupo = Long.parseLong(campoID.getText());
			try {
				procurarGrupo();
				ArrayList<Ganhador> lista = new ArrayList<>();
				lista = fachada.listarGanhador(idGrupo);
				for (Ganhador ganhador: lista) {
					Vector vector = new Vector();
					vector.add(ganhador.getIdApostador());
					vector.add(ganhador.getNomeApostador());
					vector.add(ganhador.getIdGrupo());
					vector.add(ganhador.getPontos());
					vector.add(formatacaoDinheiro.format(ganhador.getValor()));
					modeloTabelaGanhador.addRow(vector);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao listar os ganhadores");
				e.printStackTrace();
			}
		}
	}
}


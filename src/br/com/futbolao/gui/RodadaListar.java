package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

public class RodadaListar extends JInternalFrame {
	private JTable tabelaRodada;

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
	public RodadaListar() {
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
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProcurar.setBounds(465, 41, 89, 23);
		painelTabela.add(btnProcurar);
		
		JScrollPane scrollPaneRodada = new JScrollPane();
		scrollPaneRodada.setBounds(10, 73, 1044, 271);
		painelTabela.add(scrollPaneRodada);
		
		tabelaRodada = new JTable();
		tabelaRodada.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Competi\u00E7\u00E3o", "Rodada", "Jogo", "Data/Hora", "Clube 1", "Resultado 1", "Clube 2", "Resultado 2"
			}
		));
		tabelaRodada.getColumnModel().getColumn(0).setPreferredWidth(52);
		tabelaRodada.getColumnModel().getColumn(1).setPreferredWidth(149);
		tabelaRodada.getColumnModel().getColumn(2).setPreferredWidth(93);
		tabelaRodada.getColumnModel().getColumn(3).setPreferredWidth(115);
		tabelaRodada.getColumnModel().getColumn(4).setPreferredWidth(188);
		tabelaRodada.getColumnModel().getColumn(5).setPreferredWidth(202);
		tabelaRodada.getColumnModel().getColumn(7).setPreferredWidth(185);
		tabelaRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneRodada.setViewportView(tabelaRodada);
		
		JComboBox campoCompeticao = new JComboBox();
		campoCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCompeticao.setBounds(10, 42, 445, 20);
		painelTabela.add(campoCompeticao);
		
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

	}

	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	
}

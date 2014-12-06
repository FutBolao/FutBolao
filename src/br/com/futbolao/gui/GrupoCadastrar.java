package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class GrupoCadastrar extends JInternalFrame {
	private JTextField campoValorAposta;
	private JTextField campoLimiteApostas;
	private JTextField campoPercentualADM;
	private JTextField campoLimiteApostador;
	private JTextField campoEncerramentoAposta;
	private JTextField campoPontuacaoResultado;
	private JTextField campoPontuacaoporPlacar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrupoCadastrar frame = new GrupoCadastrar();
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
	public GrupoCadastrar() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Grupo Cadastrar");
		setClosable(true);
		setBounds(100, 100, 420, 359);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 384, 310);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblValorAposta = new JLabel("Valor da aposta:");
		lblValorAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValorAposta.setBounds(257, 73, 113, 20);
		panel.add(lblValorAposta);
		
		campoValorAposta = new JTextField();
		campoValorAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoValorAposta.setBounds(257, 104, 113, 20);
		panel.add(campoValorAposta);
		campoValorAposta.setColumns(10);
		
		JLabel lblLimiteApostas = new JLabel("Limite de apostas:");
		lblLimiteApostas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLimiteApostas.setBounds(10, 73, 113, 20);
		panel.add(lblLimiteApostas);
		
		campoLimiteApostas = new JTextField();
		campoLimiteApostas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoLimiteApostas.setColumns(10);
		campoLimiteApostas.setBounds(10, 104, 100, 20);
		panel.add(campoLimiteApostas);
		
		JLabel lblPercentualdoADM = new JLabel("Lucro do ADM %:");
		lblPercentualdoADM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPercentualdoADM.setBounds(10, 135, 157, 20);
		panel.add(lblPercentualdoADM);
		
		campoPercentualADM = new JTextField();
		campoPercentualADM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPercentualADM.setColumns(10);
		campoPercentualADM.setBounds(10, 166, 100, 20);
		panel.add(campoPercentualADM);
		
		JComboBox campoCompeticao = new JComboBox();
		campoCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCompeticao.setBounds(10, 42, 295, 20);
		panel.add(campoCompeticao);
		
		JLabel lblCompeticao = new JLabel("Selecione a competi\u00E7\u00E3o:");
		lblCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCompeticao.setBounds(10, 11, 157, 20);
		panel.add(lblCompeticao);
		
		JLabel lblNewLabel_1 = new JLabel("Rodada:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(315, 11, 55, 20);
		panel.add(lblNewLabel_1);
		
		JComboBox campoRodada = new JComboBox();
		campoRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoRodada.setBounds(315, 42, 55, 20);
		panel.add(campoRodada);
		
		JLabel lblLimete_apostas = new JLabel("Apostas por apostador:");
		lblLimete_apostas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLimete_apostas.setBounds(120, 73, 127, 20);
		panel.add(lblLimete_apostas);
		
		campoLimiteApostador = new JTextField();
		campoLimiteApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoLimiteApostador.setBounds(120, 104, 127, 20);
		panel.add(campoLimiteApostador);
		campoLimiteApostador.setColumns(10);
		
		JLabel lblEncerramentodaAposta = new JLabel("Encerramento da aposta:");
		lblEncerramentodaAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEncerramentodaAposta.setBounds(10, 202, 146, 20);
		panel.add(lblEncerramentodaAposta);
		
		campoEncerramentoAposta = new JTextField();
		campoEncerramentoAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEncerramentoAposta.setBounds(10, 233, 135, 20);
		panel.add(campoEncerramentoAposta);
		campoEncerramentoAposta.setColumns(10);
		
		JLabel lblPontuacaoporResultado = new JLabel("Pontos por resultado:");
		lblPontuacaoporResultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPontuacaoporResultado.setBounds(120, 135, 119, 20);
		panel.add(lblPontuacaoporResultado);
		
		campoPontuacaoResultado = new JTextField();
		campoPontuacaoResultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPontuacaoResultado.setBounds(120, 166, 119, 20);
		panel.add(campoPontuacaoResultado);
		campoPontuacaoResultado.setColumns(10);
		
		JLabel lblPontuacaoporPlacar = new JLabel("Pontos por placar:");
		lblPontuacaoporPlacar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPontuacaoporPlacar.setBounds(249, 135, 146, 20);
		panel.add(lblPontuacaoporPlacar);
		
		campoPontuacaoporPlacar = new JTextField();
		campoPontuacaoporPlacar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPontuacaoporPlacar.setBounds(249, 166, 119, 20);
		panel.add(campoPontuacaoporPlacar);
		campoPontuacaoporPlacar.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrar.setBounds(10, 275, 89, 23);
		panel.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBounds(109, 275, 89, 23);
		panel.add(btnLimpar);

	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
}

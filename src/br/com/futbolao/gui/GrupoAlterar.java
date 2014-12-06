package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class GrupoAlterar extends JInternalFrame {
	private JTextField campoValordaAposta;
	private JTextField campoLimiteApostas;
	private JTextField campoLucroADM;
	private JTextField campoApostaporApostador;
	private JTextField campoEncerramentoAposta;
	private JTextField campoPontosporResultado;
	private JTextField campoPontosporPlacar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrupoAlterar frame = new GrupoAlterar();
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
	public GrupoAlterar() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Alterar Grupo");
		setClosable(true);
		setBounds(100, 100, 420, 360);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setBounds(10, 11, 384, 308);
		painelForm.setLayout(null);
		painelForm.setBackground(Color.WHITE);
		getContentPane().add(painelForm);
		
		JLabel label = new JLabel("Valor da aposta:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(257, 73, 113, 20);
		painelForm.add(label);
		
		campoValordaAposta = new JTextField();
		campoValordaAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoValordaAposta.setColumns(10);
		campoValordaAposta.setBounds(257, 104, 113, 20);
		painelForm.add(campoValordaAposta);
		
		JLabel lblLimiteApostas = new JLabel("Limite de apostas:");
		lblLimiteApostas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLimiteApostas.setBounds(10, 73, 113, 20);
		painelForm.add(lblLimiteApostas);
		
		campoLimiteApostas = new JTextField();
		campoLimiteApostas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoLimiteApostas.setColumns(10);
		campoLimiteApostas.setBounds(10, 104, 100, 20);
		painelForm.add(campoLimiteApostas);
		
		JLabel LucroADM = new JLabel("Lucro do ADM %:");
		LucroADM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		LucroADM.setBounds(10, 135, 157, 20);
		painelForm.add(LucroADM);
		
		campoLucroADM = new JTextField();
		campoLucroADM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoLucroADM.setColumns(10);
		campoLucroADM.setBounds(10, 166, 100, 20);
		painelForm.add(campoLucroADM);
		
		JComboBox campoCompeticao = new JComboBox();
		campoCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCompeticao.setBounds(10, 42, 295, 20);
		painelForm.add(campoCompeticao);
		
		JLabel label_3 = new JLabel("Selecione a competi\u00E7\u00E3o:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(10, 11, 157, 20);
		painelForm.add(label_3);
		
		JLabel label_4 = new JLabel("Rodada:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(315, 11, 55, 20);
		painelForm.add(label_4);
		
		JComboBox campoRodada = new JComboBox();
		campoRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoRodada.setBounds(315, 42, 55, 20);
		painelForm.add(campoRodada);
		
		JLabel lblApostasApostador = new JLabel("Apostas por apostador:");
		lblApostasApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApostasApostador.setBounds(120, 73, 127, 20);
		painelForm.add(lblApostasApostador);
		
		campoApostaporApostador = new JTextField();
		campoApostaporApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoApostaporApostador.setColumns(10);
		campoApostaporApostador.setBounds(120, 104, 127, 20);
		painelForm.add(campoApostaporApostador);
		
		JLabel lblEncerramentoAposta = new JLabel("Encerramento da aposta:");
		lblEncerramentoAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEncerramentoAposta.setBounds(10, 202, 146, 20);
		painelForm.add(lblEncerramentoAposta);
		
		campoEncerramentoAposta = new JTextField();
		campoEncerramentoAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEncerramentoAposta.setColumns(10);
		campoEncerramentoAposta.setBounds(10, 233, 135, 20);
		painelForm.add(campoEncerramentoAposta);
		
		JLabel lblPontosResultado = new JLabel("Pontos por resultado:");
		lblPontosResultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPontosResultado.setBounds(120, 135, 119, 20);
		painelForm.add(lblPontosResultado);
		
		campoPontosporResultado = new JTextField();
		campoPontosporResultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPontosporResultado.setColumns(10);
		campoPontosporResultado.setBounds(120, 166, 119, 20);
		painelForm.add(campoPontosporResultado);
		
		JLabel lblPontosPlacar = new JLabel("Pontos por placar:");
		lblPontosPlacar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPontosPlacar.setBounds(249, 135, 146, 20);
		painelForm.add(lblPontosPlacar);
		
		campoPontosporPlacar = new JTextField();
		campoPontosporPlacar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPontosporPlacar.setColumns(10);
		campoPontosporPlacar.setBounds(249, 166, 119, 20);
		painelForm.add(campoPontosporPlacar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 275, 89, 23);
		painelForm.add(btnAlterar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBounds(109, 275, 89, 23);
		painelForm.add(btnLimpar);

	}

	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	
}

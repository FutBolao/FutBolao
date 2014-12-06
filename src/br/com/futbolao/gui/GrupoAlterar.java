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
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 384, 308);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel);
		
		JLabel label = new JLabel("Valor da aposta:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(257, 73, 113, 20);
		panel.add(label);
		
		campoValordaAposta = new JTextField();
		campoValordaAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoValordaAposta.setColumns(10);
		campoValordaAposta.setBounds(257, 104, 113, 20);
		panel.add(campoValordaAposta);
		
		JLabel label_1 = new JLabel("Limite de apostas:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(10, 73, 113, 20);
		panel.add(label_1);
		
		campoLimiteApostas = new JTextField();
		campoLimiteApostas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoLimiteApostas.setColumns(10);
		campoLimiteApostas.setBounds(10, 104, 100, 20);
		panel.add(campoLimiteApostas);
		
		JLabel label_2 = new JLabel("Lucro do ADM %:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(10, 135, 157, 20);
		panel.add(label_2);
		
		campoLucroADM = new JTextField();
		campoLucroADM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoLucroADM.setColumns(10);
		campoLucroADM.setBounds(10, 166, 100, 20);
		panel.add(campoLucroADM);
		
		JComboBox campoCompeticao = new JComboBox();
		campoCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCompeticao.setBounds(10, 42, 295, 20);
		panel.add(campoCompeticao);
		
		JLabel label_3 = new JLabel("Selecione a competi\u00E7\u00E3o:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(10, 11, 157, 20);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Rodada:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(315, 11, 55, 20);
		panel.add(label_4);
		
		JComboBox campoRodada = new JComboBox();
		campoRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoRodada.setBounds(315, 42, 55, 20);
		panel.add(campoRodada);
		
		JLabel label_5 = new JLabel("Apostas por apostador:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_5.setBounds(120, 73, 127, 20);
		panel.add(label_5);
		
		campoApostaporApostador = new JTextField();
		campoApostaporApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoApostaporApostador.setColumns(10);
		campoApostaporApostador.setBounds(120, 104, 127, 20);
		panel.add(campoApostaporApostador);
		
		JLabel label_6 = new JLabel("Encerramento da aposta:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_6.setBounds(10, 202, 146, 20);
		panel.add(label_6);
		
		campoEncerramentoAposta = new JTextField();
		campoEncerramentoAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEncerramentoAposta.setColumns(10);
		campoEncerramentoAposta.setBounds(10, 233, 135, 20);
		panel.add(campoEncerramentoAposta);
		
		JLabel label_7 = new JLabel("Pontos por resultado:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_7.setBounds(120, 135, 119, 20);
		panel.add(label_7);
		
		campoPontosporResultado = new JTextField();
		campoPontosporResultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPontosporResultado.setColumns(10);
		campoPontosporResultado.setBounds(120, 166, 119, 20);
		panel.add(campoPontosporResultado);
		
		JLabel label_8 = new JLabel("Pontos por placar:");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_8.setBounds(249, 135, 146, 20);
		panel.add(label_8);
		
		campoPontosporPlacar = new JTextField();
		campoPontosporPlacar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPontosporPlacar.setColumns(10);
		campoPontosporPlacar.setBounds(249, 166, 119, 20);
		panel.add(campoPontosporPlacar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 275, 89, 23);
		panel.add(btnAlterar);
		
		JButton button_1 = new JButton("Limpar");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(109, 275, 89, 23);
		panel.add(button_1);

	}

	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	
}

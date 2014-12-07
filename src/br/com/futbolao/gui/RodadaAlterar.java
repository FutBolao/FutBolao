package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class RodadaAlterar extends JInternalFrame {
	private JTextField campoNRodada;
	private JTextField campoNJogo;
	private JTextField campoData;
	private JTextField campoHora;
	private JTextField campoResultado1;
	private JTextField campoResultado2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RodadaAlterar frame = new RodadaAlterar();
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
	public RodadaAlterar() {
		setTitle("Alterar Rodada");
		setClosable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		setBounds(100, 100, 466, 367);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setLayout(null);
		painelForm.setBackground(Color.WHITE);
		painelForm.setBounds(10, 11, 430, 266);
		getContentPane().add(painelForm);
		
		JLabel lblCompeticao = new JLabel("Competi\u00E7\u00E3o:");
		lblCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCompeticao.setBounds(10, 10, 80, 20);
		painelForm.add(lblCompeticao);
		
		JComboBox campoCompeticao = new JComboBox();
		campoCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCompeticao.setBounds(10, 41, 410, 20);
		painelForm.add(campoCompeticao);
		
		JLabel lblNRodada = new JLabel("N\u00BA da Rodada");
		lblNRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNRodada.setBounds(10, 72, 110, 20);
		painelForm.add(lblNRodada);
		
		campoNRodada = new JTextField();
		campoNRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNRodada.setColumns(10);
		campoNRodada.setBounds(10, 103, 100, 20);
		painelForm.add(campoNRodada);
		
		JLabel lblNJogo = new JLabel("N\u00BA Jogo:");
		lblNJogo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNJogo.setBounds(120, 71, 62, 20);
		painelForm.add(lblNJogo);
		
		campoNJogo = new JTextField();
		campoNJogo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNJogo.setColumns(10);
		campoNJogo.setBounds(120, 103, 90, 20);
		painelForm.add(campoNJogo);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblData.setBounds(220, 72, 50, 20);
		painelForm.add(lblData);
		
		campoData = new JTextField();
		campoData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoData.setColumns(10);
		campoData.setBounds(220, 103, 100, 20);
		painelForm.add(campoData);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHora.setBounds(330, 72, 43, 20);
		painelForm.add(lblHora);
		
		campoHora = new JTextField();
		campoHora.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoHora.setColumns(10);
		campoHora.setBounds(330, 103, 90, 20);
		painelForm.add(campoHora);
		
		JLabel lblClube1 = new JLabel("Clube 1:");
		lblClube1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblClube1.setBounds(10, 134, 50, 20);
		painelForm.add(lblClube1);
		
		JLabel lblClube2 = new JLabel("Clube 2");
		lblClube2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblClube2.setBounds(10, 196, 50, 20);
		painelForm.add(lblClube2);
		
		JComboBox campoClube1 = new JComboBox();
		campoClube1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoClube1.setBounds(10, 165, 310, 20);
		painelForm.add(campoClube1);
		
		JComboBox campoClube2 = new JComboBox();
		campoClube2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoClube2.setBounds(10, 227, 310, 20);
		painelForm.add(campoClube2);
		
		JLabel lblResultado1 = new JLabel("Resultado 1:");
		lblResultado1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblResultado1.setBounds(330, 134, 73, 20);
		painelForm.add(lblResultado1);
		
		JLabel lblResultado2 = new JLabel("Resultado 2:");
		lblResultado2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblResultado2.setBounds(330, 196, 73, 20);
		painelForm.add(lblResultado2);
		
		campoResultado1 = new JTextField();
		campoResultado1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoResultado1.setColumns(10);
		campoResultado1.setBounds(330, 165, 90, 20);
		painelForm.add(campoResultado1);
		
		campoResultado2 = new JTextField();
		campoResultado2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoResultado2.setColumns(10);
		campoResultado2.setBounds(330, 227, 90, 20);
		painelForm.add(campoResultado2);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 276, 430, 51);
		getContentPane().add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 11, 89, 23);
		painelBotoes.add(btnAlterar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBounds(109, 12, 89, 23);
		painelBotoes.add(btnLimpar);

	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}

}

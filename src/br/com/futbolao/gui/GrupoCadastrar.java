package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import br.com.futbolao.competicao.Competicao;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.rodada.Rodada;
import br.com.futbolao.util.JMoneyField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GrupoCadastrar extends JInternalFrame {
	private Fachada fachada = null;
	private JMoneyField campoValorAposta;
	private JMoneyField campoLimiteApostas;
	private JMoneyField campoPercentualADM;
	private JTextField campoLimiteApostador;
	private JTextField campoEncerramentoAposta;
	private JTextField campoPontuacaoResultado;
	private JTextField campoPontuacaoporPlacar;
	private JComboBox campoCompeticao;
	private JComboBox campoRodada;

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
	
	//Esta Classe não está pronta
	
	/**
	 * Create the frame.
	 */
	public GrupoCadastrar() {
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			try {
				throw new ErroAoInstanciarFachadaException();
			} catch (ErroAoInstanciarFachadaException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			}
		}
		getContentPane().setBackground(Color.WHITE);
		setTitle("Grupo Cadastrar");
		setClosable(true);
		setBounds(100, 100, 420, 359);
		getContentPane().setLayout(null);
		
		JPanel painelCadastrar = new JPanel();
		painelCadastrar.setBackground(Color.WHITE);
		painelCadastrar.setBounds(10, 11, 384, 310);
		getContentPane().add(painelCadastrar);
		painelCadastrar.setLayout(null);
		
		JLabel lblValorAposta = new JLabel("Valor da aposta:");
		lblValorAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValorAposta.setBounds(257, 73, 113, 20);
		painelCadastrar.add(lblValorAposta);
		
		campoValorAposta = new JMoneyField();
		campoValorAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoValorAposta.setBounds(257, 104, 113, 20);
		painelCadastrar.add(campoValorAposta);
		campoValorAposta.setColumns(10);
		
		JLabel lblLimiteApostas = new JLabel("Limite de apostas:");
		lblLimiteApostas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLimiteApostas.setBounds(10, 73, 113, 20);
		painelCadastrar.add(lblLimiteApostas);
		
		campoLimiteApostas = new JMoneyField();
		campoLimiteApostas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoLimiteApostas.setColumns(10);
		campoLimiteApostas.setBounds(10, 104, 100, 20);
		painelCadastrar.add(campoLimiteApostas);
		
		JLabel lblPercentualdoADM = new JLabel("Lucro do ADM %:");
		lblPercentualdoADM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPercentualdoADM.setBounds(10, 135, 157, 20);
		painelCadastrar.add(lblPercentualdoADM);
		
		campoPercentualADM = new JMoneyField();
		campoPercentualADM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPercentualADM.setColumns(10);
		campoPercentualADM.setBounds(10, 166, 100, 20);
		painelCadastrar.add(campoPercentualADM);
		
	    campoCompeticao = new JComboBox();
		campoCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCompeticao.setBounds(10, 42, 295, 20);
		painelCadastrar.add(campoCompeticao);
		
		JLabel lblCompeticao = new JLabel("Selecione a competição:");
		lblCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCompeticao.setBounds(10, 11, 157, 20);
		painelCadastrar.add(lblCompeticao);
		
		JLabel lblRodada = new JLabel("Rodada:");
		lblRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRodada.setBounds(315, 11, 55, 20);
		painelCadastrar.add(lblRodada);
		
		campoRodada = new JComboBox();
		campoRodada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (campoCompeticao.getSelectedIndex() != 0) {
					listaRodada();
				}
			}
		});
		campoRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoRodada.setBounds(315, 42, 55, 20);
		painelCadastrar.add(campoRodada);
		
		JLabel lblLimete_apostas = new JLabel("Apostas por apostador:");
		lblLimete_apostas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLimete_apostas.setBounds(120, 73, 127, 20);
		painelCadastrar.add(lblLimete_apostas);
		
		campoLimiteApostador = new JTextField();
		campoLimiteApostador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoLimiteApostador.setBounds(120, 104, 127, 20);
		painelCadastrar.add(campoLimiteApostador);
		campoLimiteApostador.setColumns(10);
		
		JLabel lblEncerramentodaAposta = new JLabel("Encerramento da aposta:");
		lblEncerramentodaAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEncerramentodaAposta.setBounds(10, 202, 146, 20);
		painelCadastrar.add(lblEncerramentodaAposta);
		
		campoEncerramentoAposta = new JTextField();
		campoEncerramentoAposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoEncerramentoAposta.setBounds(10, 233, 135, 20);
		painelCadastrar.add(campoEncerramentoAposta);
		campoEncerramentoAposta.setColumns(10);
		
		JLabel lblPontuacaoporResultado = new JLabel("Pontos por resultado:");
		lblPontuacaoporResultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPontuacaoporResultado.setBounds(120, 135, 119, 20);
		painelCadastrar.add(lblPontuacaoporResultado);
		
		campoPontuacaoResultado = new JTextField();
		campoPontuacaoResultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPontuacaoResultado.setBounds(120, 166, 119, 20);
		painelCadastrar.add(campoPontuacaoResultado);
		campoPontuacaoResultado.setColumns(10);
		
		JLabel lblPontuacaoporPlacar = new JLabel("Pontos por placar:");
		lblPontuacaoporPlacar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPontuacaoporPlacar.setBounds(249, 135, 146, 20);
		painelCadastrar.add(lblPontuacaoporPlacar);
		
		campoPontuacaoporPlacar = new JTextField();
		campoPontuacaoporPlacar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoPontuacaoporPlacar.setBounds(249, 166, 119, 20);
		painelCadastrar.add(campoPontuacaoporPlacar);
		campoPontuacaoporPlacar.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrar.setBounds(10, 275, 89, 23);
		painelCadastrar.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBounds(109, 275, 89, 23);
		painelCadastrar.add(btnLimpar);
		
		listaCompeticao();

	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void listaCompeticao(){
		ArrayList<Competicao> lista = new ArrayList<>();
		try {
			campoCompeticao.addItem("");
			lista = fachada.listarCompeticao();
			for (Competicao competicao : lista){
				campoCompeticao.addItem(competicao.getNome());
			}			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (CompeticaoNaoCadastradaException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao listar as competições!");
		}
	}
	
	private void listaRodada(){
		
	}
	
	
	
}

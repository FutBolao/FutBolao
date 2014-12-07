package br.com.futbolao.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import br.com.futbolao.clube.Clube;
import br.com.futbolao.competicao.Competicao;
import br.com.futbolao.exception.ClubeNaoCadastradoException;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.fachada.Fachada;

@SuppressWarnings("serial")
public class RodadaCadastrar extends JInternalFrame {
	private Fachada fachada = null;
	private JTextField campoNRodada;
	private JTextField campoNJogo;
	private JTextField campoData;
	private JTextField campoHora;
	private JTextField campoResultado1;
	private JTextField campoResultado2;
	@SuppressWarnings("rawtypes")
	private JComboBox campoCompeticao;
	@SuppressWarnings("rawtypes")
	private JComboBox campoClube1;
	@SuppressWarnings("rawtypes")
	private JComboBox campoClube2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RodadaCadastrar frame = new RodadaCadastrar();
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
	@SuppressWarnings("rawtypes")
	public RodadaCadastrar() {
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			try {
				throw new ErroAoInstanciarFachadaException();
			} catch (ErroAoInstanciarFachadaException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			}
		}
		setTitle("Cadastrar Rodada");
		setClosable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		setBounds(100, 100, 465, 363);
		getContentPane().setLayout(null);
		
		JPanel painelForm = new JPanel();
		painelForm.setBackground(Color.WHITE);
		painelForm.setBounds(10, 11, 430, 266);
		getContentPane().add(painelForm);
		painelForm.setLayout(null);
		
		JLabel lblCompeticao = new JLabel("Competi\u00E7\u00E3o:");
		lblCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCompeticao.setBounds(10, 10, 80, 20);
		painelForm.add(lblCompeticao);
		
		campoCompeticao = new JComboBox();
		campoCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCompeticao.setBounds(10, 41, 410, 20);
		campoCompeticao.setMaximumRowCount(20);
		painelForm.add(campoCompeticao);
		
		JLabel lblNRodada = new JLabel("N\u00BA da Rodada");
		lblNRodada.setBounds(10, 72, 110, 20);
		painelForm.add(lblNRodada);
		
		campoNRodada = new JTextField();
		campoNRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNRodada.setBounds(10, 103, 100, 20);
		painelForm.add(campoNRodada);
		campoNRodada.setColumns(10);
		
		JLabel lblIdJogo = new JLabel("N\u00BA Jogo:");
		lblIdJogo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdJogo.setBounds(120, 71, 62, 20);
		painelForm.add(lblIdJogo);
		
		campoNJogo = new JTextField();
		campoNJogo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNJogo.setBounds(120, 103, 90, 20);
		painelForm.add(campoNJogo);
		campoNJogo.setColumns(10);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblData.setBounds(250, 72, 50, 20);
		painelForm.add(lblData);
		
		campoData = new JTextField();
		campoData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoData.setBounds(220, 103, 100, 20);
		painelForm.add(campoData);
		campoData.setColumns(10);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHora.setBounds(340, 72, 43, 20);
		painelForm.add(lblHora);
		
		campoHora = new JTextField();
		campoHora.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoHora.setBounds(330, 103, 90, 20);
		painelForm.add(campoHora);
		campoHora.setColumns(10);
		
		JLabel lblClube1 = new JLabel("Clube 1:");
		lblClube1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblClube1.setBounds(10, 134, 50, 20);
		painelForm.add(lblClube1);
		
		JLabel lblClube2 = new JLabel("Clube 2");
		lblClube2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblClube2.setBounds(10, 196, 50, 20);
		painelForm.add(lblClube2);
		
		campoClube1 = new JComboBox();
		campoClube1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoClube1.setBounds(10, 165, 310, 20);
		campoClube1.setMaximumRowCount(20);
		painelForm.add(campoClube1);
		
		campoClube2 = new JComboBox();
		campoClube2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoClube2.setBounds(10, 227, 310, 20);
		campoClube2.setMaximumRowCount(20);
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
		campoResultado1.setBounds(330, 165, 90, 20);
		painelForm.add(campoResultado1);
		campoResultado1.setColumns(10);
		
		campoResultado2 = new JTextField();
		campoResultado2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoResultado2.setBounds(330, 227, 90, 20);
		painelForm.add(campoResultado2);
		campoResultado2.setColumns(10);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 275, 430, 48);
		getContentPane().add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrar.setBounds(10, 11, 89, 23);
		painelBotoes.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBounds(109, 12, 89, 23);
		painelBotoes.add(btnLimpar);
		
		preencheCampos();

	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	@SuppressWarnings({ "unchecked" })
	private void  listaCompeticao(){
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
	
	@SuppressWarnings("unchecked")
	private void  listaClube(){
		ArrayList<Clube> lista = new ArrayList<>();
		campoClube1.addItem("");
		campoClube2.addItem("");
		try {
			lista = fachada.listarClube('S');
			for (Clube clube : lista){
				campoClube1.addItem(clube.getNome());
				campoClube2.addItem(clube.getNome());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (ClubeNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao listar os clubes!");
		}
	}
	
	private void preencheCampos(){
		listaCompeticao();
		listaClube();
	}

}

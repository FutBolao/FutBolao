package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import br.com.futbolao.clube.Clube;
import br.com.futbolao.competicao.Competicao;
import br.com.futbolao.exception.AlteracaoEfetuadaComSucessoException;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.ClubeJaCadastradoNessaRodadaException;
import br.com.futbolao.exception.ClubeNaoCadastradoException;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.JogoJaCadastradoNessaRodadaException;
import br.com.futbolao.exception.JogoNaoCadastradoNessaRodadaException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.rodada.Rodada;
import br.com.futbolao.util.FormataCampoApenasNumeros;
import br.com.futbolao.util.MascaraCampo;

@SuppressWarnings("serial")
public class RodadaAlterar extends JInternalFrame {
	private Fachada fachada = null;
	private MascaraCampo mascara = new MascaraCampo();
	private JTextField campoNumeroRodada;
	private JTextField campoNumeroJogo;
	private JFormattedTextField campoData;
	private JFormattedTextField campoHora;
	private JTextField campoResultado1;
	private JTextField campoResultado2;
	@SuppressWarnings("rawtypes")
	private JComboBox campoCompeticao;
	private int[] valueCompeticao;
	@SuppressWarnings("rawtypes")
	private JComboBox campoClube1;
	private int[] valueClube1;
	@SuppressWarnings("rawtypes")
	private JComboBox campoClube2;
	private int[] valueClube2;
	private JButton btnAlterar;
	private String data = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" + Calendar.getInstance().get(Calendar.MONTH) + "/" + Calendar.getInstance().get(Calendar.YEAR);
	private dat

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RodadaAlterar frame = new RodadaAlterar(0);
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
	public RodadaAlterar(final long id) {
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			try {
				throw new ErroAoInstanciarFachadaException();
			} catch (ErroAoInstanciarFachadaException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			}
		}
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
		
		campoCompeticao = new JComboBox();
		campoCompeticao.setEnabled(false);
		campoCompeticao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoCompeticao.setBounds(10, 41, 410, 20);
		painelForm.add(campoCompeticao);
		
		JLabel lblNRodada = new JLabel("N\u00BA da Rodada");
		lblNRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNRodada.setBounds(10, 72, 110, 20);
		painelForm.add(lblNRodada);
		
		campoNumeroRodada = new JTextField();
		campoNumeroRodada.setEnabled(false);
		campoNumeroRodada.setEditable(false);
		campoNumeroRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNumeroRodada.setColumns(10);
		campoNumeroRodada.setBounds(10, 103, 100, 20);
		campoNumeroRodada.setDocument(new FormataCampoApenasNumeros(2));
		painelForm.add(campoNumeroRodada);
		
		JLabel lblNJogo = new JLabel("N\u00BA Jogo:");
		lblNJogo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNJogo.setBounds(120, 71, 62, 20);
		painelForm.add(lblNJogo);
		
		campoNumeroJogo = new JTextField();
		campoNumeroJogo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNumeroJogo.setColumns(10);
		campoNumeroJogo.setBounds(120, 103, 90, 20);
		campoNumeroJogo.setDocument(new FormataCampoApenasNumeros(2));
		painelForm.add(campoNumeroJogo);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblData.setBounds(220, 72, 50, 20);
		painelForm.add(lblData);
		
		campoData = new JFormattedTextField();
		campoData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoData.setColumns(10);
		campoData.setBounds(220, 103, 100, 20);
		mascara.getData().install(campoData);
		painelForm.add(campoData);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHora.setBounds(330, 72, 43, 20);
		painelForm.add(lblHora);
		
		campoHora = new JFormattedTextField();
		campoHora.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoHora.setColumns(10);
		campoHora.setBounds(330, 103, 90, 20);
		mascara.getHoraMinuto().install(campoHora);
		painelForm.add(campoHora);
		
		JLabel lblClube1 = new JLabel("Clube 1:");
		lblClube1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblClube1.setBounds(10, 134, 50, 20);
		painelForm.add(lblClube1);
		
		JLabel lblClube2 = new JLabel("Clube 2");
		lblClube2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblClube2.setBounds(10, 196, 50, 20);
		painelForm.add(lblClube2);
		
		campoClube1 = new JComboBox();
		campoClube1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (campoClube1.getSelectedIndex() != 0 && campoClube1.getSelectedIndex() == campoClube2.getSelectedIndex()) {
					campoClube1.setSelectedIndex(0);
					campoClube1.requestFocus();
					try {
						throw new CampoInvalidoException("Selecione outro clube, você não pode selecionar dois clubes iguais!");
					} catch (CampoInvalidoException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					}
				}
			}
		});
		campoClube1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoClube1.setBounds(10, 165, 310, 20);
		painelForm.add(campoClube1);
		
		campoClube2 = new JComboBox();
		campoClube2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (campoClube2.getSelectedIndex() != 0 && campoClube2.getSelectedIndex() == campoClube1.getSelectedIndex()) {
					campoClube2.setSelectedIndex(0);
					campoClube2.requestFocus();
					try {
						throw new CampoInvalidoException("Selecione outro clube, você não pode selecionar dois clubes iguais!");
					} catch (CampoInvalidoException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					}
				}
			}
		});
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
		campoResultado1.setDocument(new FormataCampoApenasNumeros(3));
		painelForm.add(campoResultado1);
		
		campoResultado2 = new JTextField();
		campoResultado2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoResultado2.setColumns(10);
		campoResultado2.setBounds(330, 227, 90, 20);
		campoResultado2.setDocument(new FormataCampoApenasNumeros(3));
		painelForm.add(campoResultado2);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 276, 430, 51);
		getContentPane().add(painelBotoes);
		painelBotoes.setLayout(null);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterar(id);
			}
		});
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 11, 89, 23);
		painelBotoes.add(btnAlterar);

		preencheCampos(id);
	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private boolean validaCampos(){
		String data = campoData.getText();
		String hora = campoHora.getText();
		DateFormat dataFormatada = new SimpleDateFormat ("dd/MM/yyyy");  
	    dataFormatada.setLenient(false); 
	    try {  
	        dataFormatada.parse(data);  
	    } catch (ParseException ex) {  
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoData.requestFocus();
			}
			return false;
	    }
		DateFormat horaFormatada = new SimpleDateFormat ("HH:mm");  
	    horaFormatada.setLenient(false); 
	    try {  
	        horaFormatada.parse(hora);  
	    } catch (ParseException ex) {  
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoHora.requestFocus();
			}
			return false;
	    }
	    if (campoCompeticao.getSelectedIndex() == 0) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoCompeticao.requestFocus();
			}
			return false;
	    } else if (campoNumeroRodada.getText().equals("") || Integer.parseInt(campoNumeroRodada.getText()) < 1){
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoNumeroRodada.requestFocus();
			}
			return false;
	    } else if (campoNumeroJogo.getText().equals("") || Integer.parseInt(campoNumeroJogo.getText()) < 1){
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoNumeroJogo.requestFocus();
			}
			return false;
	    } else if (data.equals("") || data.contains(" ")){
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoData.requestFocus();
			}
			return false;
	    } else if (hora.equals("") || hora.contains(" ")){
	    	try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoHora.requestFocus();
			}
			return false;
	    } else if (campoClube1.getSelectedIndex() == 0) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoClube1.requestFocus();
			}
			return false;
	    } else if (campoClube2.getSelectedIndex() == 0) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoClube2.requestFocus();
			}
			return false;
	    } else {
	    	return true;
	    }
	}
	
	@SuppressWarnings({ "unchecked" })
	private void  listaCompeticao(){
		ArrayList<Competicao> lista = new ArrayList<>();
		try {
			campoCompeticao.addItem("");
			lista = fachada.listarCompeticao('S');
			valueCompeticao = new int[(lista.size() + 1)];
			valueCompeticao[0] = 0;
			for (int i = 1; i <= lista.size(); i++) {
				campoCompeticao.addItem(lista.get(i-1).getNome());
				valueCompeticao[i] = lista.get(i-1).getId();
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
			valueClube1 = new int[(lista.size() + 1)];
			valueClube1[0] = 0;
			valueClube2 = new int[(lista.size() + 1)];
			valueClube2[0] = 0;
			for (int i = 1; i <= lista.size(); i++) {
				campoClube1.addItem(lista.get(i-1).getNome());
				valueClube1[i] = lista.get(i-1).getId();
				campoClube2.addItem(lista.get(i-1).getNome());
				valueClube2[i] = lista.get(i-1).getId();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (ClubeNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao listar os clubes!");
		}
	}

	private void preencheCampos(long id){
		try {
			listaCompeticao();
			listaClube();
			Rodada rodada = fachada.procurarRodadaPorId(id);
			for (int i=0; i < valueCompeticao.length; i++) {
				if (valueCompeticao[i] == rodada.getIdCompeticao()) {
					campoCompeticao.setSelectedIndex(i);
				}
			}
			for (int i=0; i < valueClube1.length; i++) {
				if (valueClube1[i] == rodada.getClube1()) {
					campoClube1.setSelectedIndex(i);
				}
			}
			for (int i=0; i < valueClube2.length; i++) {
				if (valueClube2[i] == rodada.getClube2()) {
					campoClube2.setSelectedIndex(i);
				}
			}
			campoNumeroJogo.setText(String.valueOf(rodada.getIdJogo()));
			campoNumeroRodada.setText(String.valueOf(rodada.getNumeroRodada()));
			campoData.setText(rodada.getDataHora().substring(0,10));
			campoHora.setText(rodada.getDataHora().substring(11, 16));
			if (rodada.getResultadoClube1() == null) {
				campoResultado1.setText("");
			} else {
				campoResultado1.setText(String.valueOf(rodada.getResultadoClube1()));
			}
			if (rodada.getResultadoClube2() == null) {
				campoResultado2.setText("");
			} else {
				campoResultado2.setText(String.valueOf(rodada.getResultadoClube2()));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (RodadaNaoCadastradaException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao preencher os campos!");
		}
	}
	
	private void alterar(long id){
		if(validaCampos()){
			int competicao = campoCompeticao.getSelectedIndex();
			int numeroRodada = Integer.parseInt(campoNumeroRodada.getText());
			int numeroJogo = Integer.parseInt(campoNumeroJogo.getText());
			String data = campoData.getText().substring(6, 10) + "-" + campoData.getText().substring(3, 5) + "-" + campoData.getText().substring(0, 2);
			String hora = campoHora.getText();
			String dataHora = data + " " + hora + ":00";
			int clube1 = campoClube1.getSelectedIndex();
			int clube2 = campoClube2.getSelectedIndex();
			Integer resultadoClube1 = null;
			Integer resultadoClube2 = null;
			if (!campoResultado1.getText().equals("")) {
				resultadoClube1 = Integer.parseInt(campoResultado1.getText());
			}
			if (!campoResultado2.getText().equals("")) {
				resultadoClube2 = Integer.parseInt(campoResultado2.getText());
			}
			try {
				fachada.atualizarRodada(new Rodada(id, valueCompeticao[competicao], numeroRodada, numeroJogo, dataHora, valueClube1[clube1], resultadoClube1, valueClube2[clube2], resultadoClube2));
				try {
					throw new AlteracaoEfetuadaComSucessoException();
				} catch (AlteracaoEfetuadaComSucessoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				this.dispose();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (ClubeJaCadastradoNessaRodadaException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (JogoJaCadastradoNessaRodadaException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (JogoNaoCadastradoNessaRodadaException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao alterar o jogo!");
			}
		}
	}
}

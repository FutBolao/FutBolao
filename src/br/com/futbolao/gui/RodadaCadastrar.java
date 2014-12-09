package br.com.futbolao.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import br.com.futbolao.clube.Clube;
import br.com.futbolao.competicao.Competicao;
import br.com.futbolao.exception.CadastroEfetuadoComSucessoException;
import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.ClubeJaCadastradoNessaRodadaException;
import br.com.futbolao.exception.ClubeNaoCadastradoException;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.RodadaJaCadastradaException;
import br.com.futbolao.exception.RodadaTravadaException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.rodada.Rodada;
import br.com.futbolao.util.FormataCampoApenasNumeros;
import br.com.futbolao.util.MascaraCampo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class RodadaCadastrar extends JInternalFrame {
	private Fachada fachada = null;
	private MascaraCampo mascara = new MascaraCampo();
	private JTextField campoNumeroRodada;
	private JTextField campoNumeroJogo;
	private JFormattedTextField campoData;
	private JFormattedTextField campoHora;
	@SuppressWarnings("rawtypes")
	private JComboBox campoCompeticao;
	private int[] valueCompeticao;
	@SuppressWarnings("rawtypes")
	private JComboBox campoClube1;
	private int[] valueClube1;
	@SuppressWarnings("rawtypes")
	private JComboBox campoClube2;
	private int[] valueClube2;

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
		
		campoNumeroRodada = new JTextField();
		campoNumeroRodada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNumeroRodada.setBounds(10, 103, 100, 20);
		painelForm.add(campoNumeroRodada);
		campoNumeroRodada.setDocument(new FormataCampoApenasNumeros(2));
		campoNumeroRodada.setColumns(10);
		
		JLabel lblIdJogo = new JLabel("N\u00BA Jogo:");
		lblIdJogo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdJogo.setBounds(120, 71, 62, 20);
		painelForm.add(lblIdJogo);
		
		campoNumeroJogo = new JTextField();
		campoNumeroJogo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoNumeroJogo.setBounds(120, 103, 90, 20);
		painelForm.add(campoNumeroJogo);
		campoNumeroJogo.setDocument(new FormataCampoApenasNumeros(2));
		campoNumeroJogo.setColumns(10);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblData.setBounds(250, 72, 50, 20);
		painelForm.add(lblData);
		
		campoData = new JFormattedTextField();
		campoData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoData.setBounds(220, 103, 100, 20);
		painelForm.add(campoData);
		mascara.getData().install(campoData);
		campoData.setColumns(10);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHora.setBounds(340, 72, 43, 20);
		painelForm.add(lblHora);
		
		campoHora = new JFormattedTextField();
		campoHora.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoHora.setBounds(330, 103, 90, 20);
		painelForm.add(campoHora);
		mascara.getHoraMinuto().install(campoHora);
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
		campoClube1.setBounds(10, 165, 410, 20);
		campoClube1.setMaximumRowCount(20);
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
		campoClube2.setBounds(10, 227, 410, 20);
		campoClube2.setMaximumRowCount(20);
		painelForm.add(campoClube2);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 275, 430, 48);
		getContentPane().add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrar();
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrar.setBounds(10, 11, 89, 23);
		painelBotoes.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBounds(109, 12, 89, 23);
		painelBotoes.add(btnLimpar);
		
		preencheCampos();

	}
	
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void limparCampos(){
		campoCompeticao.setSelectedIndex(0);
		campoNumeroRodada.setText("");
		campoNumeroJogo.setText("");
		campoData.setText("");
		campoHora.setText("");
		campoClube1.setSelectedIndex(0);
		campoClube2.setSelectedIndex(0);
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
			lista = fachada.listarCompeticao();
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
	
	private void preencheCampos(){
		listaCompeticao();
		listaClube();
	}
	
	private void cadastrar(){
		if(validaCampos()){
			int competicao = campoCompeticao.getSelectedIndex();
			int numeroRodada = Integer.parseInt(campoNumeroRodada.getText());
			int numeroJogo = Integer.parseInt(campoNumeroJogo.getText());
			String data = campoData.getText().substring(6, 10) + "-" + campoData.getText().substring(3, 5) + "-" + campoData.getText().substring(0, 2);
			String hora = campoHora.getText();
			String dataHora = data + " " + hora + ":00";
			int clube1 = campoClube1.getSelectedIndex();
			int clube2 = campoClube2.getSelectedIndex();
			try {
				fachada.cadastrarRodada(new Rodada(0, valueCompeticao[competicao], numeroRodada, numeroJogo, dataHora, valueClube1[clube1], valueClube2[clube2]));
				try {
					throw new CadastroEfetuadoComSucessoException();
				} catch (CadastroEfetuadoComSucessoException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				limparCampos();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (RodadaTravadaException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (ClubeJaCadastradoNessaRodadaException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (RodadaJaCadastradaException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao cadastrar a rodada!");
				e.printStackTrace();
			}
		}
	}
}

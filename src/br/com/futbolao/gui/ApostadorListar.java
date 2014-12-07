package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.futbolao.apostador.Apostador;
import br.com.futbolao.exception.ApostadorNaoCadastradoException;
import br.com.futbolao.exception.ConfirmacaoDeExclusaoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.fachada.Fachada;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ApostadorListar extends JInternalFrame {
	private JTextField campoProcurar;
	private JTable tabelaApostador;
	private DefaultTableModel tabelaModeloapostador;
	private String [] colunaTabelaApostador;
	Fachada fachada = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApostadorListar frame = new ApostadorListar();
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
	public ApostadorListar() {
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
		setTitle("Listar Apostador");
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 560, 503);
		getContentPane().setLayout(null);
		
		JPanel painelTabela = new JPanel();
		painelTabela.setBackground(Color.WHITE);
		painelTabela.setBounds(10, 11, 530, 446);
		getContentPane().add(painelTabela);
		painelTabela.setLayout(null);
		
		JLabel lblDigitenome = new JLabel("Digite o nome do apostador desejado ou deixe em branco para listar todos: ");
		lblDigitenome.setBounds(10, 11, 223, 14);
		lblDigitenome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		painelTabela.add(lblDigitenome);
		
		campoProcurar = new JTextField();
		campoProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoProcurar.setBounds(10, 36, 411, 20);
		painelTabela.add(campoProcurar);
		campoProcurar.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				procurar();
			}
		});
		btnProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProcurar.setBounds(431, 35, 89, 23);
		painelTabela.add(btnProcurar);
		
		JScrollPane scrollPaneListarApostador = new JScrollPane();
		scrollPaneListarApostador.setBounds(10, 67, 510, 336);
		painelTabela.add(scrollPaneListarApostador);
		
		tabelaApostador = new JTable();
		tabelaApostador.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colunaTabelaApostador = new String[] {"ID", "Nome", "CPF", "Telefone"};
		tabelaModeloapostador = new DefaultTableModel(new Object[][] {},colunaTabelaApostador){
			boolean[] columnEditables = new boolean[] {false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};		
		tabelaApostador.setModel(tabelaModeloapostador);		
		tabelaApostador.getColumnModel().getColumn(0).setPreferredWidth(59);
		tabelaApostador.getColumnModel().getColumn(1).setPreferredWidth(251);
		tabelaApostador.getColumnModel().getColumn(2).setPreferredWidth(123);
		tabelaApostador.getColumnModel().getColumn(3).setPreferredWidth(109);
		scrollPaneListarApostador.setViewportView(tabelaApostador);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 400, 510, 35);
		painelTabela.add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(0, 11, 101, 23);
		painelBotoes.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeletar.setBounds(111, 11, 104, 23);
		painelBotoes.add(btnDeletar);

	}
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	public void limparTabela(){
		this.tabelaModeloapostador.setNumRows(0);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void procurar(){
		try {
			limparTabela();
			String procurar = campoProcurar.getText();
			limparTabela();
			ArrayList<Apostador> lista = new ArrayList<>();
			if(procurar.equals("")){
				lista = fachada.listarApostador();
			}else{
				lista = fachada.procurarApostadorPorNome(procurar);
			}
			for(Apostador apostador : lista){
				Vector vector = new Vector<>();
				vector.add(apostador.getId());
				vector.add(apostador.getNome());
				vector.add(apostador.getCpf());
				vector.add(apostador.getTelefone());
				tabelaModeloapostador.addRow(vector);
			}			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
			e.printStackTrace();
		} catch (ApostadorNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar procurar o apostador!");
		}
	}
	
	private void alterar(){
		if (tabelaApostador.getSelectedRowCount() == 1) {
			int linha = tabelaApostador.getSelectedRow();
			long id = (long) tabelaApostador.getValueAt(linha, 0);
			ApostadorAlterar apostadorAlterar = new ApostadorAlterar(id);
			Principal.desktopPane.add(apostadorAlterar);
			apostadorAlterar.setVisible(true);
			apostadorAlterar.setPosicao();
		}
	}
	
	private void deletar(){
		if(tabelaApostador.getSelectedRowCount() == 1){
			try {
				throw new ConfirmacaoDeExclusaoException();
			} catch (ConfirmacaoDeExclusaoException ex) {
				int confirmacao = JOptionPane.showConfirmDialog(rootPane, ex.getMessage(), "Alerta", JOptionPane.YES_NO_OPTION);
				if (confirmacao == 0) {
					try {
						int linha = tabelaApostador.getSelectedRow();
						long id = (long) tabelaApostador.getValueAt(linha, 0);
						fachada.deletarApostador(id);
						procurar();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					} catch (ApostadorNaoCadastradoException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar deletar o apostador!");
					}
				}
			}
		}
	}
}

package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import br.com.futbolao.clube.Clube;
import br.com.futbolao.exception.ClubeNaoCadastradoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.exception.ConfirmacaoDeExclusaoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ClubeListar extends JInternalFrame {
	private Fachada fachada = null;
	private JTextField campoProcurar;
	private JTable tabelaClube;
	private DefaultTableModel modeloTabelaClube;
	private String[] colunaTabelaClube;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClubeListar frame = new ClubeListar();
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
	public ClubeListar() {
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
		setTitle("Listar Clubes");
		setClosable(true);
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);
		
		JPanel painelTabela = new JPanel();
		painelTabela.setBackground(Color.WHITE);
		painelTabela.setBounds(10, 11, 764, 404);
		getContentPane().add(painelTabela);
		painelTabela.setLayout(null);
		
		campoProcurar = new JTextField();
		campoProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoProcurar.setBounds(10, 40, 645, 23);
		painelTabela.add(campoProcurar);
		campoProcurar.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				procurar();
			}
		});
		btnProcurar.setBounds(665, 41, 89, 23);
		painelTabela.add(btnProcurar);
		
		JScrollPane scrollPaneClube = new JScrollPane();
		scrollPaneClube.setBounds(10, 74, 744, 330);
		painelTabela.add(scrollPaneClube);
		
		tabelaClube = new JTable();
		tabelaClube.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colunaTabelaClube = new String[] {"ID", "Nome", "Nome Completo", "Sigla", "Ativo", "Estado", "Pa\u00EDs"};
		modeloTabelaClube = new DefaultTableModel(new Object[][] {},colunaTabelaClube){
			boolean[] columnEditables = new boolean[] {false, false, false, false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabelaClube.setModel(modeloTabelaClube);
		tabelaClube.getColumnModel().getColumn(0).setPreferredWidth(59);
		tabelaClube.getColumnModel().getColumn(1).setPreferredWidth(163);
		tabelaClube.getColumnModel().getColumn(2).setPreferredWidth(226);
		tabelaClube.getColumnModel().getColumn(3).setPreferredWidth(32);
		tabelaClube.getColumnModel().getColumn(4).setPreferredWidth(37);
		tabelaClube.getColumnModel().getColumn(5).setPreferredWidth(102);
		tabelaClube.getColumnModel().getColumn(6).setPreferredWidth(91);
		scrollPaneClube.setViewportView(tabelaClube);
		
		JLabel lblTituloProcurar = new JLabel("Digite o nome do clube desejado ou deixe em branco para listar todos:");
		lblTituloProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTituloProcurar.setBounds(10, 10, 400, 19);
		painelTabela.add(lblTituloProcurar);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 415, 764, 44);
		getContentPane().add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 11, 89, 23);
		painelBotoes.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deletar();
			}
		});
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeletar.setBounds(109, 12, 89, 23);
		painelBotoes.add(btnDeletar);

	}

	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void limparTabela(){
		this.modeloTabelaClube.setNumRows(0);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void procurar(){
		try {
			limparTabela();
			String procurar = campoProcurar.getText();
			limparTabela();
			ArrayList<Clube> lista = new ArrayList<>();
			if (procurar.equals("")){
				lista = fachada.listarClube();
			} else {
				lista = fachada.procurarClubePorNome(procurar);
			}
			for (Clube clube: lista) {
				Vector vector = new Vector();
				vector.add(clube.getId());
				vector.add(clube.getNome());
				vector.add(clube.getNomeCompleto());
				vector.add(clube.getSigla());
				vector.add(clube.getAtivo());
				vector.add(clube.getEstado());
				vector.add(clube.getPais());
				modeloTabelaClube.addRow(vector);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (ClubeNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar procurar clube!");
		}	
	}
	
	private void deletar(){
		if (tabelaClube.getSelectedRowCount() == 1) {
			try {
				throw new ConfirmacaoDeExclusaoException();
			} catch (ConfirmacaoDeExclusaoException ex) {
				int confirmacao = JOptionPane.showConfirmDialog(rootPane, ex.getMessage(), "Alerta", JOptionPane.YES_NO_OPTION);
				if (confirmacao == 0) {
					try {
						int linha = tabelaClube.getSelectedRow();
						int id = (int)tabelaClube.getValueAt(linha, 0);
						fachada.deletarClube(id);
						procurar();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					} catch (ClubeNaoCadastradoException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar deletar clube!");
					}
				}
			}
		}
	}
	
}

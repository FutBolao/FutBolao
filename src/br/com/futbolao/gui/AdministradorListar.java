package br.com.futbolao.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.com.futbolao.administrador.Administrador;
import br.com.futbolao.exception.AdministradorNaoCadastradoException;
import br.com.futbolao.exception.ConfirmacaoDeExclusaoException;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.fachada.Fachada;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

@SuppressWarnings("serial")
public class AdministradorListar extends JInternalFrame {
	private Fachada fachada = null;
	private JTextField campoProcurar;
	private JTable tabelaAdministrador;
	private DefaultTableModel modeloTabelaAdministrador;
	private String[] colunaTabelaAdministrador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministradorListar frame = new AdministradorListar();
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
	public AdministradorListar() {
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
		setTitle("Listar Administradores");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel painelTabela = new JPanel();
		painelTabela.setBackground(Color.WHITE);
		painelTabela.setBounds(10, 11, 524, 409);
		getContentPane().add(painelTabela);
		painelTabela.setLayout(null);
		
		JLabel lblDigiteONome = new JLabel("Digite o nome do administrador desejado ou deixe em branco para listar todos:");
		lblDigiteONome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDigiteONome.setBounds(10, 10, 400, 19);
		painelTabela.add(lblDigiteONome);
		
		campoProcurar = new JTextField();
		campoProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoProcurar.setBounds(10, 40, 400, 20);
		painelTabela.add(campoProcurar);
		campoProcurar.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				procurar();
			}
		});
		btnProcurar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProcurar.setBounds(420, 39, 94, 23);
		painelTabela.add(btnProcurar);
		
		JScrollPane scrollPaneADM = new JScrollPane();
		scrollPaneADM.setBounds(10, 71, 504, 338);
		painelTabela.add(scrollPaneADM);
		
		tabelaAdministrador = new JTable();
		tabelaAdministrador.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colunaTabelaAdministrador = new String[] {"ID", "Nome", "CPF", "Ativo"};
		modeloTabelaAdministrador = new DefaultTableModel(new Object[][] {},colunaTabelaAdministrador){
			boolean[] columnEditables = new boolean[] {false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabelaAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tabelaAdministrador.setModel(modeloTabelaAdministrador);
		tabelaAdministrador.getColumnModel().getColumn(0).setPreferredWidth(52);
		tabelaAdministrador.getColumnModel().getColumn(1).setPreferredWidth(206);
		tabelaAdministrador.getColumnModel().getColumn(2).setPreferredWidth(140);
		tabelaAdministrador.getColumnModel().getColumn(3).setPreferredWidth(38);
		scrollPaneADM.setViewportView(tabelaAdministrador);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 419, 524, 40);
		getContentPane().add(painelBotoes);
		painelBotoes.setLayout(null);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterar();
			}
		});
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 11, 89, 23);
		painelBotoes.add(btnAlterar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemover.setBounds(109, 12, 89, 23);
		painelBotoes.add(btnRemover);
		setBounds(100, 100, 560, 500);

	}
	public void setPosicao() {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	
	private void limparTabela(){
		this.modeloTabelaAdministrador.setNumRows(0);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void procurar(){
		try {
			limparTabela();
			String procurar = campoProcurar.getText();
			limparTabela();
			ArrayList<Administrador> lista = new ArrayList<>();
			if(procurar.equals("")){
				lista = fachada.listarAdministrador();
			}else{
				lista = fachada.procurarAdministradorPorNome(procurar);
			}
			for(Administrador administrador : lista){
				Vector vector = new Vector<>();
				vector.add(administrador.getId());
				vector.add(administrador.getNome());
				vector.add(administrador.getCpf());
				vector.add(administrador.getAtivo());
				modeloTabelaAdministrador.addRow(vector);
			}			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
			e.printStackTrace();
		} catch (AdministradorNaoCadastradoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar procurar clube!");
		}
	}
	
	@SuppressWarnings("static-access")
	private void alterar(){
		if (tabelaAdministrador.getSelectedRowCount() == 1) {
			int linha = tabelaAdministrador.getSelectedRow();
			int id = (int) tabelaAdministrador.getValueAt(linha, 0);
			AdministradorAlterar administradorAlterar = new AdministradorAlterar(id);
			Principal principal = new Principal();
			principal.desktopPane.add(administradorAlterar);
			administradorAlterar.setVisible(true);
			administradorAlterar.setPosicao();
			
		}
		
	}
	
	private void deletar(){
		if(tabelaAdministrador.getSelectedRowCount() == 1){
			try {
				throw new ConfirmacaoDeExclusaoException();
			} catch (ConfirmacaoDeExclusaoException ex) {
				int confirmacao = JOptionPane.showConfirmDialog(rootPane, ex.getMessage(), "Alerta", JOptionPane.YES_NO_OPTION);
				if (confirmacao == 0) {
					try {
						int linha = tabelaAdministrador.getSelectedRow();
						int id = (int) tabelaAdministrador.getValueAt(linha, 0);
						fachada.deletarAdministrador(id);
						procurar();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					} catch (AdministradorNaoCadastradoException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao tentar deletar administrador!");
					}
					
				}
			}
		}
	}
}

package br.com.futbolao.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

@SuppressWarnings("serial")
public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenu mnAdministrador = new JMenu("Administrador");
		menuBar.add(mnAdministrador);
		
		JMenuItem mntmCadastrar = new JMenuItem("Cadastrar");
		mnAdministrador.add(mntmCadastrar);
		
		JMenuItem mntmProcurar = new JMenuItem("Listar");
		mnAdministrador.add(mntmProcurar);
		
		JMenu mnApostador = new JMenu("Apostador");
		menuBar.add(mnApostador);
		
		JMenuItem mntmCadastrar_1 = new JMenuItem("Cadastrar");
		mnApostador.add(mntmCadastrar_1);
		
		JMenuItem mntmProcurar_1 = new JMenuItem("Listar");
		mnApostador.add(mntmProcurar_1);
		
		JMenu mnApostas = new JMenu("Apostas");
		menuBar.add(mnApostas);
		
		JMenuItem mntmCadastrar_2 = new JMenuItem("Cadastrar");
		mnApostas.add(mntmCadastrar_2);
		
		JMenuItem mntmListar = new JMenuItem("Listar");
		mnApostas.add(mntmListar);
		
		JMenu mnGrupos = new JMenu("Grupos");
		mnApostas.add(mnGrupos);
		
		JMenuItem mntmCadastrar_3 = new JMenuItem("Cadastrar");
		mnGrupos.add(mntmCadastrar_3);
		
		JMenuItem mntmListar_1 = new JMenuItem("Listar");
		mnGrupos.add(mntmListar_1);
		
		JMenuItem mntmVerificarGanhadores = new JMenuItem("Verificar Ganhadores");
		mnApostas.add(mntmVerificarGanhadores);
		
		JMenu mnClubes = new JMenu("Clubes");
		menuBar.add(mnClubes);
		
		JMenuItem mntmCadastrar_4 = new JMenuItem("Cadastrar");
		mnClubes.add(mntmCadastrar_4);
		
		JMenuItem mntmListar_2 = new JMenuItem("Listar");
		mnClubes.add(mntmListar_2);
		
		JMenu mnCompeties = new JMenu("Competi\u00E7\u00F5es");
		menuBar.add(mnCompeties);
		
		JMenuItem mntmCadastrar_5 = new JMenuItem("Cadastrar");
		mnCompeties.add(mntmCadastrar_5);
		
		JMenuItem mntmListar_3 = new JMenuItem("Listar");
		mnCompeties.add(mntmListar_3);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBounds(149, 86, 199, 200);
		desktopPane.add(internalFrame);
		internalFrame.setVisible(true);
	}
}

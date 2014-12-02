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
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel;
import br.com.futbolao.exception.ErroAoInstanciarFachadaException;
import br.com.futbolao.exception.ErroAoInstanciarImagemException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.util.Imagem;
import br.com.futbolao.util.JDesktopPaneComBackground;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Principal extends JFrame {
	@SuppressWarnings("unused")
	private Fachada fachada = null;
	Imagem imagem = null;
	final JDesktopPane desktopPane;
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new SubstanceOfficeBlue2007LookAndFeel());
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
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			try {
				throw new ErroAoInstanciarFachadaException();
			} catch (ErroAoInstanciarFachadaException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			}
		}
		try {
			imagem = Imagem.getInstance();
		} catch (Exception e) {
			try {
				throw new ErroAoInstanciarImagemException();
			} catch (ErroAoInstanciarImagemException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			}
		}
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
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
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
		mntmCadastrar_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClubeCadastrar clubeCadastrar = new ClubeCadastrar();
				desktopPane.add(clubeCadastrar);
				clubeCadastrar.setVisible(true);
				clubeCadastrar.setPosicao();
			}
		});
		mnClubes.add(mntmCadastrar_4);
		
		JMenuItem mntmListar_2 = new JMenuItem("Listar");
		mnClubes.add(mntmListar_2);
		
		JMenu mnCompeties = new JMenu("Competi\u00E7\u00F5es");
		menuBar.add(mnCompeties);
		
		JMenuItem mntmCadastrar_5 = new JMenuItem("Cadastrar");
		mnCompeties.add(mntmCadastrar_5);
		
		JMenuItem mntmListar_3 = new JMenuItem("Listar");
		mnCompeties.add(mntmListar_3);
		
		desktopPane = new JDesktopPaneComBackground(imagem.getBackgroundTelaPrincipal());
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		
		
	}
}

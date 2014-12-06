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
		
		JMenuItem mntmAdministradorCadastrar = new JMenuItem("Cadastrar");
		mntmAdministradorCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdministradorCadastrar administradorCadastrar = new AdministradorCadastrar();
				desktopPane.add(administradorCadastrar);
				administradorCadastrar.setVisible(true);
				administradorCadastrar.setPosicao();
			}
		});
		mnAdministrador.add(mntmAdministradorCadastrar);
		
		JMenuItem mntmAdministradorListar = new JMenuItem("Listar");
		mntmAdministradorListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdministradorListar administradorListar = new AdministradorListar();
				desktopPane.add(administradorListar);
				administradorListar.setVisible(true);
				administradorListar.setPosicao();
			}
		});
		mnAdministrador.add(mntmAdministradorListar);
		
		JMenu mnApostador = new JMenu("Apostador");
		menuBar.add(mnApostador);
		
		JMenuItem mntmApostadorCadastrar = new JMenuItem("Cadastrar");
		mntmApostadorCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApostadorCadastrar apostadorCadastrar = new ApostadorCadastrar();
				desktopPane.add(apostadorCadastrar);
				apostadorCadastrar.setVisible(true);
				apostadorCadastrar.setPosicao();
			}
		});
		mnApostador.add(mntmApostadorCadastrar);
		
		JMenuItem mntmApostadorListar = new JMenuItem("Listar");
		mntmApostadorListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ApostadorListar apostadorListar = new ApostadorListar();
			desktopPane.add(apostadorListar);
			apostadorListar.setVisible(true);
			apostadorListar.setPosicao();
				
			}
		});
		mnApostador.add(mntmApostadorListar);
		
		JMenu mnAposta = new JMenu("Aposta");
		menuBar.add(mnAposta);
		
		JMenuItem mntmApostaCadastrar = new JMenuItem("Cadastrar");
		mnAposta.add(mntmApostaCadastrar);
		
		JMenuItem mntmApostaListar = new JMenuItem("Listar");
		mnAposta.add(mntmApostaListar);
		
		JMenu mnRodada = new JMenu("Rodada");
		mnAposta.add(mnRodada);
		
		JMenuItem mntmRodadaCadastrar = new JMenuItem("Cadastrar");
		mntmRodadaCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mnRodada.add(mntmRodadaCadastrar);
		
		JMenuItem mntmRodadaListar = new JMenuItem("Listar");
		mntmRodadaListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnRodada.add(mntmRodadaListar);
		
		JMenu mnGrupo = new JMenu("Grupo");
		mnAposta.add(mnGrupo);
		
		JMenuItem mntmGrupoCadastrar = new JMenuItem("Cadastrar");
		mnGrupo.add(mntmGrupoCadastrar);
		
		JMenuItem mntmGrupoListar = new JMenuItem("Listar");
		mnGrupo.add(mntmGrupoListar);
		
		JMenuItem mntmVerificarGanhadores = new JMenuItem("Verificar Ganhadores");
		mnAposta.add(mntmVerificarGanhadores);
		
		JMenu mnClubes = new JMenu("Clube");
		menuBar.add(mnClubes);
		
		JMenuItem mntmClubeCadastrar = new JMenuItem("Cadastrar");
		mntmClubeCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClubeCadastrar clubeCadastrar = new ClubeCadastrar();
				desktopPane.add(clubeCadastrar);
				clubeCadastrar.setVisible(true);
				clubeCadastrar.setPosicao();
			}
		});
		mnClubes.add(mntmClubeCadastrar);
		
		JMenuItem mntmClubeListar = new JMenuItem("Listar");
		mntmClubeListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClubeListar clubeListar = new ClubeListar();
				desktopPane.add(clubeListar);
				clubeListar.setVisible(true);
				clubeListar.setPosicao();
			}
		});
		mnClubes.add(mntmClubeListar);
		
		JMenu mnCompeties = new JMenu("Competi\u00E7\u00E3o");
		menuBar.add(mnCompeties);
		
		JMenuItem mntmCompeticaoCadastrar = new JMenuItem("Cadastrar");
		mntmCompeticaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CompeticaoCadastrar competicaoCadastrar = new CompeticaoCadastrar();
				desktopPane.add(competicaoCadastrar);
				competicaoCadastrar.setVisible(true);
				competicaoCadastrar.setPosicao();
			}
		});
		mnCompeties.add(mntmCompeticaoCadastrar);
		
		JMenuItem mntmCompeticaoListar = new JMenuItem("Listar");
		mntmCompeticaoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CompeticaoListar competicaoListar = new CompeticaoListar();
				desktopPane.add(competicaoListar);
				competicaoListar.setVisible(true);
				competicaoListar.setPosicao();
			}
		});
		mnCompeties.add(mntmCompeticaoListar);
		
		desktopPane = new JDesktopPaneComBackground(imagem.getBackgroundTelaPrincipal());
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		
		
	}
}

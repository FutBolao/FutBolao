package br.com.futbolao.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel;

import br.com.futbolao.exception.CampoInvalidoException;
import br.com.futbolao.exception.LoginNaoAutorizadoException;
import br.com.futbolao.fachada.Fachada;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class Login extends JFrame {
	
	private Fachada fachada = null;
	private JPanel contentPane;
	private JTextField campoUsuario;
	private JPasswordField campoSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new SubstanceOfficeBlue2007LookAndFeel());
					Login frame = new Login();
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
	public Login() {
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao instanciar a fachada!");
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 584, 279);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setForeground(new Color(0, 0, 0));
		lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsurio.setBounds(262, 167, 89, 14);
		panel.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(0, 0, 0));
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(262, 223, 89, 14);
		panel.add(lblSenha);
		
		campoUsuario = new JTextField();
		campoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoUsuario.setBounds(262, 192, 299, 20);
		panel.add(campoUsuario);
		campoUsuario.setColumns(10);
		
		campoSenha = new JPasswordField();
		campoSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		campoSenha.setColumns(10);
		campoSenha.setBounds(262, 248, 192, 20);
		panel.add(campoSenha);
		
		JButton btnNewButton = new JButton("Acessar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(464, 247, 97, 23);
		panel.add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/br/com/futbolao/imagem/fundoLogin.png")));
		label.setBounds(0, 0, 584, 280);
		panel.add(label);
	}
	
	@SuppressWarnings({"deprecation" })
	private boolean validaCampos(){
		String usuario = campoUsuario.getText();
		String senha = campoSenha.getText();
		if (usuario.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoUsuario.requestFocus();
				return false;
			}
		} else if (senha.equals("")) {
			try {
				throw new CampoInvalidoException();
			} catch (CampoInvalidoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
				campoSenha.requestFocus();
				return false;
			}
		} else {
			return true;
		}
	}
	
	@SuppressWarnings("deprecation")
	private void login() {
		String usuario = campoUsuario.getText();
		String senha = campoSenha.getText();
		if (validaCampos()) {
			try {
				if (fachada.login(usuario, senha)) {
					Principal principal = new Principal();
					principal.setVisible(true);
					this.dispose();
				} else {
					try {
						throw new LoginNaoAutorizadoException();
					} catch (LoginNaoAutorizadoException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					}
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado ao instanciar a fachada!");
			}
		}
	}
}

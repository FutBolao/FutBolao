package br.com.futbolao.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class Teste extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teste frame = new Teste();
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
	public Teste() {
		setBounds(100, 100, 450, 300);

	}

}

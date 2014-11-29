package br.com.futbolao.util;

public class Imagem {
	
	private static Imagem instance = null;
	private String backgroundTelaPrincipal;

	public Imagem() {
		super();
		this.backgroundTelaPrincipal = "/br/com/futbolao/imagem/fundo.jpg";
	}
	
	public static Imagem getInstance() throws Exception {
		if (Imagem.instance == null) {
			Imagem.instance = new Imagem();
		}
		return Imagem.instance;
	}

	public String getBackgroundTelaPrincipal() {
		return backgroundTelaPrincipal;
	}
}

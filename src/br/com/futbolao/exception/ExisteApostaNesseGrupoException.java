package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ExisteApostaNesseGrupoException extends Exception{

	public ExisteApostaNesseGrupoException() {
		super("Existe aposta cadastrada para esse grupo!"
				+ "\nN�o ser� poss�vel efetuar a exclus�o");
	}
	
	public ExisteApostaNesseGrupoException(String msg) {
		super(msg);
	}

}

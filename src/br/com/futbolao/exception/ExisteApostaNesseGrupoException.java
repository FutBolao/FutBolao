package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ExisteApostaNesseGrupoException extends Exception{

	public ExisteApostaNesseGrupoException() {
		super("Existe aposta cadastrada para esse grupo!"
				+ "\nNão será possível efetuar a exclusão");
	}
	
	public ExisteApostaNesseGrupoException(String msg) {
		super(msg);
	}

}

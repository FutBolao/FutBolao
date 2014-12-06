package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class SelecioneUmaOpcaoException extends Exception{

	public SelecioneUmaOpcaoException() {
		super("Selecione uma op��o!");
	}
	
	public SelecioneUmaOpcaoException(String msg) {
		super(msg);
	}

}

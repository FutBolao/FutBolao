package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class IdInvalidoException extends Exception{

	public IdInvalidoException() {
		super("CPF Inv�lido!");
	}
	
	
	public IdInvalidoException(String msg) {
		super(msg);
	}
	
	
}

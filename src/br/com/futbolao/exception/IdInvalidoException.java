package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class IdInvalidoException extends Exception{

	public IdInvalidoException() {
		super("CPF Inválido!");
	}
	
	
	public IdInvalidoException(String msg) {
		super(msg);
	}
	
	
}

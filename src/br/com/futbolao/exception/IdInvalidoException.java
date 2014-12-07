package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class IdInvalidoException extends Exception{

	public IdInvalidoException() {
		super("ID Inválido!");
	}
	
	public IdInvalidoException(String msg) {
		super(msg);
	}
	
	
}

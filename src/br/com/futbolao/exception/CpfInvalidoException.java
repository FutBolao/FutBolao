package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class CpfInvalidoException extends Exception{

	public CpfInvalidoException() {
		super("CPF Inv�lido!");
	}
	
	public CpfInvalidoException(String msg) {
		super(msg);
	}
	
	

}

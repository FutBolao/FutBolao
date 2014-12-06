package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class CpfInvalidoException extends Exception{

	public CpfInvalidoException() {
		super("CPF Inválido!");
	}
	
	public CpfInvalidoException(String msg) {
		super(msg);
	}
	
	

}

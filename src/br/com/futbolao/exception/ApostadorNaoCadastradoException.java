package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ApostadorNaoCadastradoException extends Exception{
	
	public ApostadorNaoCadastradoException() {
		super("Apostador não está cadastrado!");
	}
	
	public ApostadorNaoCadastradoException(String msg) {
		super(msg);
	}

}

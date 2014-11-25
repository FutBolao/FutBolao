package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ApostadorJaCadastradoException extends Exception{
	
	public ApostadorJaCadastradoException() {
		super("Apostador Já Cadastrado!");
	}
	
	public ApostadorJaCadastradoException(String msg) {
		super(msg);
	}

}

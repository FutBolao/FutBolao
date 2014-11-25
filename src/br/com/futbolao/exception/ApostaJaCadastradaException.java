package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ApostaJaCadastradaException extends Exception{
	
	public ApostaJaCadastradaException() {
		super("Aposta Já Cadastrada!");
	}
	
	public ApostaJaCadastradaException(String msg) {
		super(msg);
	}

}

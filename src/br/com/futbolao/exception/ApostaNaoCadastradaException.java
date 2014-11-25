package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ApostaNaoCadastradaException extends Exception{
	
	public ApostaNaoCadastradaException() {
		super("Aposta não está cadastrada!");
	}
	
	public ApostaNaoCadastradaException(String msg) {
		super(msg);
	}

}

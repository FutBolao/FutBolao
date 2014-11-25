package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ApostaNaoCadastradaException extends Exception{
	
	public ApostaNaoCadastradaException() {
		super("Aposta n�o est� cadastrada!");
	}
	
	public ApostaNaoCadastradaException(String msg) {
		super(msg);
	}

}

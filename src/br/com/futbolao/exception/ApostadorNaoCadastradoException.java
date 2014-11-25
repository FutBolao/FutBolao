package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ApostadorNaoCadastradoException extends Exception{
	
	public ApostadorNaoCadastradoException() {
		super("Apostador n�o est� cadastrado!");
	}
	
	public ApostadorNaoCadastradoException(String msg) {
		super(msg);
	}

}

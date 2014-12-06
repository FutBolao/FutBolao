package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class AlteracaoEfetuadaComSucessoException extends Exception{
	
	public AlteracaoEfetuadaComSucessoException() {
		super("Altera��o efetuada com sucesso!");
	}
	
	public AlteracaoEfetuadaComSucessoException(String msg) {
		super(msg);
	}

}

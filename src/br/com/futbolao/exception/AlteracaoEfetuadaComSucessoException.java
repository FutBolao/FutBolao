package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class AlteracaoEfetuadaComSucessoException extends Exception{
	
	public AlteracaoEfetuadaComSucessoException() {
		super("Alteração efetuada com sucesso!");
	}
	
	public AlteracaoEfetuadaComSucessoException(String msg) {
		super(msg);
	}

}

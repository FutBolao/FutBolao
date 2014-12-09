package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class RodadaDestravadaComSucessoException extends Exception{
	
	public RodadaDestravadaComSucessoException() {
		super("Rodada destravada com sucesso!");
	}
	
	public RodadaDestravadaComSucessoException(String msg) {
		super(msg);
	}

}

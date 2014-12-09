package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class RodadaTravadaException extends Exception{
	
	public RodadaTravadaException() {
		super("Rodada travada!"
				+ "\nNão será mais possível cadastrar, alterar ou deletar registros dessa rodada.");
	}
	
	public RodadaTravadaException(String msg) {
		super(msg);
	}

}

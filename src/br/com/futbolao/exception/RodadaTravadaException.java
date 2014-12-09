package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class RodadaTravadaException extends Exception{
	
	public RodadaTravadaException() {
		super("Rodada travada!"
				+ "\nN�o ser� mais poss�vel cadastrar, alterar ou deletar registros dessa rodada.");
	}
	
	public RodadaTravadaException(String msg) {
		super(msg);
	}

}

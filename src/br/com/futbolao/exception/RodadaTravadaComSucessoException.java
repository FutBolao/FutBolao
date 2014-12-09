package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class RodadaTravadaComSucessoException extends Exception{
	
	public RodadaTravadaComSucessoException() {
		super("Rodada travada com sucesso!"
				+ "\nN�o ser� mais poss�vel cadastrar, alterar ou deletar registros dessa rodada.");
	}
	
	public RodadaTravadaComSucessoException(String msg) {
		super(msg);
	}

}

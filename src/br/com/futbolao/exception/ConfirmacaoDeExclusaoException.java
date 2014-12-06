package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ConfirmacaoDeExclusaoException extends Exception{

	public ConfirmacaoDeExclusaoException() {
		super("Você de deseja realmente efetuar essa exclusão?");
	}
	
	public ConfirmacaoDeExclusaoException(String msg) {
		super(msg);
	}

}

package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ConfirmacaoDeExclusaoException extends Exception{

	public ConfirmacaoDeExclusaoException() {
		super("Voc� deseja realmente efetuar essa exclus�o?");
	}
	
	public ConfirmacaoDeExclusaoException(String msg) {
		super(msg);
	}

}

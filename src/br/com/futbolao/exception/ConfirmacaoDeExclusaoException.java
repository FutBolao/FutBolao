package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ConfirmacaoDeExclusaoException extends Exception{

	public ConfirmacaoDeExclusaoException() {
		super("Voc� de deseja realmente efetuar essa exclus�o?");
	}
	
	public ConfirmacaoDeExclusaoException(String msg) {
		super(msg);
	}

}

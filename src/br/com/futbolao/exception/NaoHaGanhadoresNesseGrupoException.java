package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class NaoHaGanhadoresNesseGrupoException extends Exception{

	public NaoHaGanhadoresNesseGrupoException() {
		super("N�o h� ganhadores no grupo!");
	}

	public NaoHaGanhadoresNesseGrupoException(String msg) {
		super(msg);
	}

}

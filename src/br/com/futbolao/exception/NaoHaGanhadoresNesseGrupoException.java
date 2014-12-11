package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class NaoHaGanhadoresNesseGrupoException extends Exception{

	public NaoHaGanhadoresNesseGrupoException() {
		super("Não há ganhadores no grupo!");
	}

	public NaoHaGanhadoresNesseGrupoException(String msg) {
		super(msg);
	}

}

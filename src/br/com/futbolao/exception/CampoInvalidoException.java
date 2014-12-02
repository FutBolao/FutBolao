package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class CampoInvalidoException extends Exception{

	public CampoInvalidoException() {
		super("Campo Inválido!");
	}

	public CampoInvalidoException(String msg) {
		super(msg);
	}

}

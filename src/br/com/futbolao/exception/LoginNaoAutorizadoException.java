package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class LoginNaoAutorizadoException extends Exception{

	public LoginNaoAutorizadoException() {
		super("Login n�o autorizado!");
	}

	public LoginNaoAutorizadoException(String msg) {
		super(msg);
	}

}

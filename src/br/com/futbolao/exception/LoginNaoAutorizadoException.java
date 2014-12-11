package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class LoginNaoAutorizadoException extends Exception{

	public LoginNaoAutorizadoException() {
		super("Login não autorizado!");
	}

	public LoginNaoAutorizadoException(String msg) {
		super(msg);
	}

}

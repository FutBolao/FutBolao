package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class AdministradorNaoCadastradoException extends Exception{
	
	public AdministradorNaoCadastradoException() {
		super("Administrador n�o est� cadastrado!");
	}
	
	public AdministradorNaoCadastradoException(String msg) {
		super(msg);
	}

}

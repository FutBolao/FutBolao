package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class AdministradorJaCadastradoException extends Exception{
	
	public AdministradorJaCadastradoException() {
		super("Administrador J� Cadastrado!");
	}
	
	public AdministradorJaCadastradoException(String msg) {
		super(msg);
	}

}

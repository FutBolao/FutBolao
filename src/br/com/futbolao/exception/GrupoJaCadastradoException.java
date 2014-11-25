package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class GrupoJaCadastradoException extends Exception{
	
	public GrupoJaCadastradoException() {
		super("Grupo Já Cadastrado!");
	}
	
	public GrupoJaCadastradoException(String msg) {
		super(msg);
	}

}

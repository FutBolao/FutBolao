package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class GrupoNaoCadastradoException extends Exception{
	
	public GrupoNaoCadastradoException() {
		super("Grupo n�o est� cadastrado!");
	}
	
	public GrupoNaoCadastradoException(String msg) {
		super(msg);
	}

}

package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class GrupoNaoCadastradoException extends Exception{
	
	public GrupoNaoCadastradoException() {
		super("Grupo não está cadastrado!");
	}
	
	public GrupoNaoCadastradoException(String msg) {
		super(msg);
	}

}

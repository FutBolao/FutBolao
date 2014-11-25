package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class PermissaoJaCadastradaException extends Exception{

	public PermissaoJaCadastradaException(){
		super("Permiss�o J� Cadastrada!");
	}
	
	public PermissaoJaCadastradaException(String msg){
		super(msg);
	}
}

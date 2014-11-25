package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class PermissaoJaCadastradaException extends Exception{

	public PermissaoJaCadastradaException(){
		super("Permissão Já Cadastrada!");
	}
	
	public PermissaoJaCadastradaException(String msg){
		super(msg);
	}
}

package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class PermissaoNaoCadastradaException extends Exception{

	public PermissaoNaoCadastradaException(){
		super("Permiss�o N�o Cadastrada!");
	}
	
	public PermissaoNaoCadastradaException(String msg){
		super(msg);
	}
}

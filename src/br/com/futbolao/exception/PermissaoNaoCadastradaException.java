package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class PermissaoNaoCadastradaException extends Exception{

	public PermissaoNaoCadastradaException(){
		super("Permissão Não Cadastrada!");
	}
	
	public PermissaoNaoCadastradaException(String msg){
		super(msg);
	}
}

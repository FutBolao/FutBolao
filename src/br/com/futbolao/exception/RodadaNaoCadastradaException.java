package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class RodadaNaoCadastradaException extends Exception{

	public RodadaNaoCadastradaException(){
		super("Rodada n�o cadastrada!");
	}
	
	public RodadaNaoCadastradaException(String msg){
		super(msg);
	}
}

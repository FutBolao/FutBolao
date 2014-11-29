package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class MovimentacaoJaCadastradaException extends Exception{

	public MovimentacaoJaCadastradaException(){
		super("Movimenta��o j� cadastrada!");
	}
	
	public MovimentacaoJaCadastradaException(String msg){
		super(msg);
	}
}

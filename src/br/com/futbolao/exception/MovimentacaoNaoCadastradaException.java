package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class MovimentacaoNaoCadastradaException extends Exception{

	public MovimentacaoNaoCadastradaException(){
		super("Movimenta��o j� cadastrada!");
	}
	
	public MovimentacaoNaoCadastradaException(String msg){
		super(msg);
	}
}

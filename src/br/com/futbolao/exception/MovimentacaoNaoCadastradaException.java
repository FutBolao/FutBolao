package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class MovimentacaoNaoCadastradaException extends Exception{

	public MovimentacaoNaoCadastradaException(){
		super("Movimentação já cadastrada!");
	}
	
	public MovimentacaoNaoCadastradaException(String msg){
		super(msg);
	}
}

package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class MovimentacaoJaCadastradaException extends Exception{

	public MovimentacaoJaCadastradaException(){
		super("Movimentação já cadastrada!");
	}
	
	public MovimentacaoJaCadastradaException(String msg){
		super(msg);
	}
}

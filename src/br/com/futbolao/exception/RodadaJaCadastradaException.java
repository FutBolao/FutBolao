package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class RodadaJaCadastradaException extends Exception{

	public RodadaJaCadastradaException(){
		super("Rodada j� cadastrada!");
	}
	
	public RodadaJaCadastradaException(String msg){
		super(msg);
	}
}

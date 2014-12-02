package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class PaisJaCadastradoException extends Exception{

	public PaisJaCadastradoException(){
		super("Pa�s j� cadastrado!");
	}
	
	public PaisJaCadastradoException(String msg){
		super(msg);
	}
}

package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class PaisJaCadastradoException extends Exception{

	public PaisJaCadastradoException(){
		super("País já cadastrado!");
	}
	
	public PaisJaCadastradoException(String msg){
		super(msg);
	}
}

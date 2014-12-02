package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class EstadoJaCadastradoException extends Exception{

	public EstadoJaCadastradoException(){
		super("Estado já cadastrado!");
	}
	
	public EstadoJaCadastradoException(String msg){
		super(msg);
	}
}

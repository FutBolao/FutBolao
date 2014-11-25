package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class CompeticaoJaCadastradaException extends Exception{

	public CompeticaoJaCadastradaException(){
		super("Competi��o J� Cadastrada!");
	}
	
	public CompeticaoJaCadastradaException(String msg){
		super(msg);
	}
	
}

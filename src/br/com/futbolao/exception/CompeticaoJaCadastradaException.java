package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class CompeticaoJaCadastradaException extends Exception{

	public CompeticaoJaCadastradaException(){
		super("Competição Já Cadastrada!");
	}
	
	public CompeticaoJaCadastradaException(String msg){
		super(msg);
	}
	
}

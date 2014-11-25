package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ClubeJaCadastradoException extends Exception{

	public ClubeJaCadastradoException(){
		super("Clube J� Cadastrado!");
	}
	
	public ClubeJaCadastradoException(String msg){
		super(msg);
	}
}

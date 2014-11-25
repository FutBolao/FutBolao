package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ClubeNaoCadastradoException extends Exception{

	public ClubeNaoCadastradoException(){
		super("Clube N�o Cadastrado!");
	}
	
	public ClubeNaoCadastradoException(String msg){
		super(msg);
	}
}

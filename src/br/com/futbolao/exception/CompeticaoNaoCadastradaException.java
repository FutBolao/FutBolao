package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class CompeticaoNaoCadastradaException extends Exception{

	public CompeticaoNaoCadastradaException(){
		super("Competi��o N�o Cadastrada!");
	}
	
	public CompeticaoNaoCadastradaException(String msg){
		super(msg);
	}
	
}

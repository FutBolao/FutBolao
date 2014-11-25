package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class CompeticaoNaoCadastradaException extends Exception{

	public CompeticaoNaoCadastradaException(){
		super("Competição Não Cadastrada!");
	}
	
	public CompeticaoNaoCadastradaException(String msg){
		super(msg);
	}
	
}

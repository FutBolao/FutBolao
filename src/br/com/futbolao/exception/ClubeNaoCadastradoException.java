package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ClubeNaoCadastradoException extends Exception{

	public ClubeNaoCadastradoException(){
		super("Clube Não Cadastrado!");
	}
	
	public ClubeNaoCadastradoException(String msg){
		super(msg);
	}
}

package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class PaisNaoCadastradoException extends Exception{

	public PaisNaoCadastradoException(){
		super("Pa�s n�o cadastrado!");
	}
	
	public PaisNaoCadastradoException(String msg){
		super(msg);
	}
}

package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class PaisNaoCadastradoException extends Exception{

	public PaisNaoCadastradoException(){
		super("País não cadastrado!");
	}
	
	public PaisNaoCadastradoException(String msg){
		super(msg);
	}
}

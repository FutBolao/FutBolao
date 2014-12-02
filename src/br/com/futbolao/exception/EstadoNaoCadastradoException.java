package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class EstadoNaoCadastradoException extends Exception{

	public EstadoNaoCadastradoException(){
		super("Estado não cadastrado!");
	}
	
	public EstadoNaoCadastradoException(String msg){
		super(msg);
	}
}

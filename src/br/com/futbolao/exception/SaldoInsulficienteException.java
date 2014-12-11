package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class SaldoInsulficienteException extends Exception{

	public SaldoInsulficienteException(){
		super("Competição Já Cadastrada!");
	}
	
	public SaldoInsulficienteException(String msg){
		super(msg);
	}
	
}

package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class SaldoInsulficienteException extends Exception{

	public SaldoInsulficienteException(){
		super("Competi��o J� Cadastrada!");
	}
	
	public SaldoInsulficienteException(String msg){
		super(msg);
	}
	
}

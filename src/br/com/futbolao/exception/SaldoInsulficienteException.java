package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class SaldoInsulficienteException extends Exception{

	public SaldoInsulficienteException(){
		super("Saldo insulficiente para realizar essa opera��o!");
	}
	
	public SaldoInsulficienteException(String msg){
		super(msg);
	}
	
}

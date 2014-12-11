package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class TotalDeApostasDoGrupoAtingidoException extends Exception{

	public TotalDeApostasDoGrupoAtingidoException(){
		super("Total de apostas do grupo foi atingido!"
				+ "\nTente efetuar a aposta em um grupo diferente.");
	}
	
	public TotalDeApostasDoGrupoAtingidoException(String msg){
		super(msg);
	}
}

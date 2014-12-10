package br.com.futbolao.exception;

public class ValorInvalidoException extends Exception{

	public ValorInvalidoException(){
		super("Insira um Valor Maior ou igual a R$ 10,00");
	}
	public ValorInvalidoException(String msg){
		super(msg);
	}
}

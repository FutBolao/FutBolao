package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ErroAoInstanciarImagemException extends Exception{

	public ErroAoInstanciarImagemException(){
		super("N�o foi poss�vel instanciar as imagens!");
	}
	
	public ErroAoInstanciarImagemException(String msg){
		super(msg);
	}
}

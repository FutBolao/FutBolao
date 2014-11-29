package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ErroAoInstanciarImagemException extends Exception{

	public ErroAoInstanciarImagemException(){
		super("Não foi possível instanciar as imagens!");
	}
	
	public ErroAoInstanciarImagemException(String msg){
		super(msg);
	}
}

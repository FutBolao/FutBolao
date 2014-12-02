package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ErroAoInstanciarFachadaException extends Exception{
	
	public ErroAoInstanciarFachadaException() {
		super("Ocorreu um erro inesperado ao instanciar a fachada!");
	}
	
	public ErroAoInstanciarFachadaException(String msg) {
		super(msg);
	}

}

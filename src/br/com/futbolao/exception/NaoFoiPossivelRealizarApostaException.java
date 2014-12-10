package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class NaoFoiPossivelRealizarApostaException extends Exception{
	
	public NaoFoiPossivelRealizarApostaException() {
		super("Não foi possível realizar a aposta!");
	}
	
	public NaoFoiPossivelRealizarApostaException(String msg) {
		super(msg);
	}

}

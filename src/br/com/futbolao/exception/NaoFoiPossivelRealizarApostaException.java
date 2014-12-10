package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class NaoFoiPossivelRealizarApostaException extends Exception{
	
	public NaoFoiPossivelRealizarApostaException() {
		super("N�o foi poss�vel realizar a aposta!");
	}
	
	public NaoFoiPossivelRealizarApostaException(String msg) {
		super(msg);
	}

}

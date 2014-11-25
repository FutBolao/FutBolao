package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class NomeVazioException extends RuntimeException {
	public NomeVazioException() {
		super("O campo nome est� vazio, favor preencher!");
	}
	
	public NomeVazioException(String Message) {
		super(Message);
	}
}

package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class PreenchaTodosOsResultadosException extends Exception{
	
	public PreenchaTodosOsResultadosException() {
		super("Preencha todos os resultados para poder finalizar a aposta!");
	}
	
	public PreenchaTodosOsResultadosException(String msg) {
		super(msg);
	}

}

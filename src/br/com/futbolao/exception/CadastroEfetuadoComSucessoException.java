package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class CadastroEfetuadoComSucessoException extends Exception{
	
	public CadastroEfetuadoComSucessoException() {
		super("Cadastrado efetuado com sucesso!");
	}
	
	public CadastroEfetuadoComSucessoException(String msg) {
		super(msg);
	}

}

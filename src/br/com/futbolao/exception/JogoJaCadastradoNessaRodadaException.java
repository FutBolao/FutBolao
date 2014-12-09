package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class JogoJaCadastradoNessaRodadaException extends Exception{

	public JogoJaCadastradoNessaRodadaException(){
		super("Jogo j� cadastrado nessa rodada!");
	}
	
	public JogoJaCadastradoNessaRodadaException(String msg){
		super(msg);
	}
}

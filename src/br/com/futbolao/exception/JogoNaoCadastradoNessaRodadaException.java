package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class JogoNaoCadastradoNessaRodadaException extends Exception{

	public JogoNaoCadastradoNessaRodadaException(){
		super("Jogo n�o cadastrado nessa rodada!");
	}
	
	public JogoNaoCadastradoNessaRodadaException(String msg){
		super(msg);
	}
}

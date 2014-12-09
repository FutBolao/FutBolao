package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class JogoNaoCadastradoNessaRodadaException extends Exception{

	public JogoNaoCadastradoNessaRodadaException(){
		super("Jogo não cadastrado nessa rodada!");
	}
	
	public JogoNaoCadastradoNessaRodadaException(String msg){
		super(msg);
	}
}

package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class ClubeJaCadastradoNessaRodadaException extends Exception{

	public ClubeJaCadastradoNessaRodadaException(){
		super("Clube já cadastrado nessa rodada!");
	}
	
	public ClubeJaCadastradoNessaRodadaException(String msg){
		super(msg);
	}
}

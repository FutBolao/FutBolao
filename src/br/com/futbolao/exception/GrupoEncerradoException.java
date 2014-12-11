package br.com.futbolao.exception;

@SuppressWarnings("serial")
public class GrupoEncerradoException extends Exception{

	public GrupoEncerradoException(){
		super("Grupo encerrado!");
	}
	
	public GrupoEncerradoException(String msg){
		super(msg);
	}
	
}

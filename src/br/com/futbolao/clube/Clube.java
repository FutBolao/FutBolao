package br.com.futbolao.clube;

public class Clube {
	
	private int id;
	private String nome;
	private String sigla;
	private char ativo;
	
	
	public Clube(int id, String nome, String sigla, char ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.ativo = ativo;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getSigla() {
		return sigla;
	}


	public void setSigla(String sigla) {
		this.sigla = sigla;
	}


	public char getAtivo() {
		return ativo;
	}


	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}


	@Override
	public String toString() {
		return "Clube [id=" + id + ", nome=" + nome + ", sigla=" + sigla
				+ ", ativo=" + ativo + "]";
	}
	
	
	
}

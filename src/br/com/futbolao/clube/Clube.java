package br.com.futbolao.clube;

public class Clube {
	
	private int id;
	private String nome;
	private String nomeCompleto;
	private String sigla;
	private char ativo;
	
	public Clube(int id, String nome, String nomeCompleto, String sigla, char ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.nomeCompleto = nomeCompleto;
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
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
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
		return "Clube [id=" + id + ", nome=" + nome + ", nomeCompleto="
				+ nomeCompleto + ", sigla=" + sigla + ", ativo=" + ativo + "]";
	}	
}
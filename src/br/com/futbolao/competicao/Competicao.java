package br.com.futbolao.competicao;

public class Competicao {
	
	private int id;
	private String nome;
	private int qtdRodadas;
	private char ativo;
	
	public Competicao(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Competicao(int id, String nome, int qtdRodadas, char ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.qtdRodadas = qtdRodadas;
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

	public int getQtdRodadas() {
		return qtdRodadas;
	}

	public void setQtdRodadas(int qtdRodadas) {
		this.qtdRodadas = qtdRodadas;
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Competicao [id=" + id + ", nome=" + nome + ", qtdRodadas="
				+ qtdRodadas + ", ativo=" + ativo + "]";
	}
	
	
	

}

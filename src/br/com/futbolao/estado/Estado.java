package br.com.futbolao.estado;

public class Estado {
	
	private int id;
	private String nome;
	private String uf;
	private int idPais;
	private String nomePais;
	
	public Estado(int id, String nome, String uf, int idPais) {
		super();
		this.id = id;
		this.nome = nome;
		this.uf = uf;
		this.idPais = idPais;
	}

	public Estado(int id, String nome, String uf, int idPais, String nomePais) {
		super();
		this.id = id;
		this.nome = nome;
		this.uf = uf;
		this.idPais = idPais;
		this.nomePais = nomePais;
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

	@Override
	public String toString() {
		return "Estado [id=" + id + ", nome=" + nome + ", uf=" + uf
				+ ", idPais=" + idPais + ", nomePais=" + nomePais + "]";
	}
	
}
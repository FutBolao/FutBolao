package br.com.futbolao.ganhadores;

public class Ganhador {
	
	private long idApostador;
	private String nomeApostador;
	private long idGrupo;
	private int pontos;
	private double valor;
	
	public Ganhador(long id_apostador, String nome_apostador, long id_grupo,
			int pontos, double valor) {
		super();
		this.idApostador = id_apostador;
		this.nomeApostador = nome_apostador;
		this.idGrupo = id_grupo;
		this.pontos = pontos;
		this.valor = valor;
	}

	public long getIdApostador() {
		return idApostador;
	}

	public void setIdApostador(long idApostador) {
		this.idApostador = idApostador;
	}

	public String getNomeApostador() {
		return nomeApostador;
	}

	public void setNomeApostador(String nomeApostador) {
		this.nomeApostador = nomeApostador;
	}

	public long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Ganhador [id_apostador=" + idApostador + ", nome_apostador="
				+ nomeApostador + ", id_grupo=" + idGrupo + ", pontos="
				+ pontos + ", valor=" + valor + "]";
	}

}
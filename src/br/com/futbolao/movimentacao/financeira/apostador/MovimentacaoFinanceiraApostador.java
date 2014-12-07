package br.com.futbolao.movimentacao.financeira.apostador;

public class MovimentacaoFinanceiraApostador {
	
	private long id;
	private long idApostador;
	private String nomeApostador;
	private String tipoMovimentacao;
	private double valor;
	private String dataHora;
	
	public MovimentacaoFinanceiraApostador(long id, long idApostador,
			String nomeApostador, String tipoMovimentacao, double valor,
			String dataHora) {
		super();
		this.id = id;
		this.idApostador = idApostador;
		this.nomeApostador = nomeApostador;
		this.tipoMovimentacao = tipoMovimentacao;
		this.valor = valor;
		this.dataHora = dataHora;
	}

	public MovimentacaoFinanceiraApostador(long id, long idApostador,
			String tipoMovimentacao, double valor, String dataHora) {
		super();
		this.id = id;
		this.idApostador = idApostador;
		this.tipoMovimentacao = tipoMovimentacao;
		this.valor = valor;
		this.dataHora = dataHora;
	}
	
	public MovimentacaoFinanceiraApostador(long id, long idApostador,
			String tipoMovimentacao, double valor) {
		super();
		this.id = id;
		this.idApostador = idApostador;
		this.tipoMovimentacao = tipoMovimentacao;
		this.valor = valor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	@Override
	public String toString() {
		return "MovimentacaoFinanceiraApostador [id=" + id + ", idApostador="
				+ idApostador + ", nomeApostador=" + nomeApostador
				+ ", tipoMovimentacao=" + tipoMovimentacao + ", valor=" + valor
				+ ", dataHora=" + dataHora + "]";
	}
	
}

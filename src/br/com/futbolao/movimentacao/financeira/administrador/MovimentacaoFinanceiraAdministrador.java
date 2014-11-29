package br.com.futbolao.movimentacao.financeira.administrador;

public class MovimentacaoFinanceiraAdministrador {
	
	private long id;
	private long idAdministrador;
	private String nomeAdministrador;
	private String tipoMovimentacao;
	private double valor;
	private String dataHora;
	
	public MovimentacaoFinanceiraAdministrador(long id, long idApostador,
			String nomeApostador, String tipoMovimentacao, double valor,
			String dataHora) {
		super();
		this.id = id;
		this.idAdministrador = idApostador;
		this.nomeAdministrador = nomeApostador;
		this.tipoMovimentacao = tipoMovimentacao;
		this.valor = valor;
		this.dataHora = dataHora;
	}

	public MovimentacaoFinanceiraAdministrador(long id, long idApostador,
			String tipoMovimentacao, double valor, String dataHora) {
		super();
		this.id = id;
		this.idAdministrador = idApostador;
		this.tipoMovimentacao = tipoMovimentacao;
		this.valor = valor;
		this.dataHora = dataHora;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdApostador() {
		return idAdministrador;
	}

	public void setIdApostador(long idApostador) {
		this.idAdministrador = idApostador;
	}

	public String getNomeApostador() {
		return nomeAdministrador;
	}

	public void setNomeApostador(String nomeApostador) {
		this.nomeAdministrador = nomeApostador;
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
		return "MovimentacaoFinanceiraApostador [id=" + id + ", idAdministrador="
				+ idAdministrador + ", nomeAdministrador=" + nomeAdministrador
				+ ", tipoMovimentacao=" + tipoMovimentacao + ", valor=" + valor
				+ ", dataHora=" + dataHora + "]";
	}
	
}

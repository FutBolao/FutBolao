package br.com.futbolao.movimentacao.financeira.administrador;

public class MovimentacaoFinanceiraAdministrador {
	
	private long id;
	private long idAdministrador;
	private String nomeAdministrador;
	private String tipoMovimentacao;
	private double valor;
	private String dataHora;
	
	public MovimentacaoFinanceiraAdministrador(long id, long idAdministrador,
			String nomeAdministrador, String tipoMovimentacao, double valor,
			String dataHora) {
		super();
		this.id = id;
		this.idAdministrador = idAdministrador;
		this.nomeAdministrador = nomeAdministrador;
		this.tipoMovimentacao = tipoMovimentacao;
		this.valor = valor;
		this.dataHora = dataHora;
	}

	public MovimentacaoFinanceiraAdministrador(long id, long idAdministrador,
			String tipoMovimentacao, double valor, String dataHora) {
		super();
		this.id = id;
		this.idAdministrador = idAdministrador;
		this.tipoMovimentacao = tipoMovimentacao;
		this.valor = valor;
		this.dataHora = dataHora;
	}
	
	public MovimentacaoFinanceiraAdministrador(long id, long idAdministrador,
			String tipoMovimentacao, double valor) {
		super();
		this.id = id;
		this.idAdministrador = idAdministrador;
		this.tipoMovimentacao = tipoMovimentacao;
		this.valor = valor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(long idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public String getNomeAdministrador() {
		return nomeAdministrador;
	}

	public void setNomeAdministrador(String nomeAdministrador) {
		this.nomeAdministrador = nomeAdministrador;
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
		return "MovimentacaoFinanceiraAdministrador [id=" + id + ", idAdministrador="
				+ idAdministrador + ", nomeAdministrador=" + nomeAdministrador
				+ ", tipoMovimentacao=" + tipoMovimentacao + ", valor=" + valor
				+ ", dataHora=" + dataHora + "]";
	}
	
}

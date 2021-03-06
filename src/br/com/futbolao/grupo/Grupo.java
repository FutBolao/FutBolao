package br.com.futbolao.grupo;

public class Grupo {
	
	private long id;
	private double valorAposta;
	private long limiteApostas;
	private int limiteApostasPorApostador;
	private int percentualLucroAdministrador;
	private String dataEncerramentoAposta;
	private String dataAtual;
	private int idCompeticao;
	private String nomeCompeticao;
	private int idRodada;
	private int pontuacaoPorResultado;
	private int pontuacaoPorPlacar;
	private long totalApostas;
	private double totalValorApostas;
		
	public Grupo(long id, double valorAposta, long limiteApostas,
			int limiteApostasPorApostador, int percentualLucroAdministrador,
			String dataEncerramentoAposta, int idCompeticao, int idRodada,
			int pontuacaoPorResultado, int pontuacaoPorPlacar) {
		super();
		this.id = id;
		this.valorAposta = valorAposta;
		this.limiteApostas = limiteApostas;
		this.limiteApostasPorApostador = limiteApostasPorApostador;
		this.percentualLucroAdministrador = percentualLucroAdministrador;
		this.dataEncerramentoAposta = dataEncerramentoAposta;
		this.idCompeticao = idCompeticao;
		this.idRodada = idRodada;
		this.pontuacaoPorResultado = pontuacaoPorResultado;
		this.pontuacaoPorPlacar = pontuacaoPorPlacar;
	}
	
	public Grupo(long id, double valorAposta, long limiteApostas,
			int limiteApostasPorApostador, int percentualLucroAdministrador,
			String dataEncerramentoAposta, int idCompeticao, String nomeCompeticao, int idRodada,
			int pontuacaoPorResultado, int pontuacaoPorPlacar, String dataAtual, long totalApostas, double totalValorAposta) {
		super();
		this.id = id;
		this.valorAposta = valorAposta;
		this.limiteApostas = limiteApostas;
		this.limiteApostasPorApostador = limiteApostasPorApostador;
		this.percentualLucroAdministrador = percentualLucroAdministrador;
		this.dataEncerramentoAposta = dataEncerramentoAposta;
		this.idCompeticao = idCompeticao;
		this.nomeCompeticao = nomeCompeticao;
		this.idRodada = idRodada;
		this.pontuacaoPorResultado = pontuacaoPorResultado;
		this.pontuacaoPorPlacar = pontuacaoPorPlacar;
		this.dataAtual = dataAtual;
		this.totalApostas = totalApostas;
		this.totalValorApostas = totalValorAposta;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public double getValorAposta() {
		return valorAposta;
	}

	public void setValorAposta(double valorAposta) {
		this.valorAposta = valorAposta;
	}

	public long getLimiteApostas() {
		return limiteApostas;
	}

	public void setLimiteApostas(long limiteApostas) {
		this.limiteApostas = limiteApostas;
	}

	public int getLimiteApostasPorApostador() {
		return limiteApostasPorApostador;
	}

	public void setLimiteApostasPorApostador(int limiteApostasPorApostador) {
		this.limiteApostasPorApostador = limiteApostasPorApostador;
	}

	public int getPercentualLucroAdministrador() {
		return percentualLucroAdministrador;
	}

	public void setPercentualLucroAdministrador(int percentualLucroAdministrador) {
		this.percentualLucroAdministrador = percentualLucroAdministrador;
	}

	public String getDataEncerramentoAposta() {
		return dataEncerramentoAposta;
	}

	public void setDataEncerramentoAposta(String dataEncerramentoAposta) {
		this.dataEncerramentoAposta = dataEncerramentoAposta;
	}

	public int getIdCompeticao() {
		return idCompeticao;
	}

	public void setIdCompeticao(int idCompeticao) {
		this.idCompeticao = idCompeticao;
	}
	
	public String getNomeCompeticao() {
		return nomeCompeticao;
	}

	public void setNomeCompeticao(String nomeCompeticao) {
		this.nomeCompeticao = nomeCompeticao;
	}
	
	public int getIdRodada() {
		return idRodada;
	}

	public void setIdRodada(int idRodada) {
		this.idRodada = idRodada;
	}

	public int getPontuacaoPorResultado() {
		return pontuacaoPorResultado;
	}

	public void setPontuacaoPorResultado(int pontuacaoPorResultado) {
		this.pontuacaoPorResultado = pontuacaoPorResultado;
	}

	public int getPontuacaoPorPlacar() {
		return pontuacaoPorPlacar;
	}

	public void setPontuacaoPorPlacar(int pontuacaoPorPlacar) {
		this.pontuacaoPorPlacar = pontuacaoPorPlacar;
	}
	
	public String getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}
	
	public long getTotalApostas() {
		return totalApostas;
	}

	public void setTotalApostas(long totalApostas) {
		this.totalApostas = totalApostas;
	}
	
	public double getTotalValorApostas() {
		return totalValorApostas;
	}

	public void setTotalValorApostas(double totalValorApostas) {
		this.totalValorApostas = totalValorApostas;
	}

	@Override
	public String toString() {
		return "Aposta [id=" + id + ", valorAposta=" + valorAposta
				+ ", limiteApostas=" + limiteApostas
				+ ", limiteApostasPorApostador=" + limiteApostasPorApostador
				+ ", percentualLucroAdministrador="
				+ percentualLucroAdministrador + ", dataEncerramentoAposta="
				+ dataEncerramentoAposta + ",idRodada=" + idRodada + " ,idRodada=" + idRodada
				+ ", pontuacaoPorResultado=" + pontuacaoPorResultado
				+ ", pontuacaoPorPlacar=" + pontuacaoPorPlacar + ",dataAtual=" + dataAtual + "]";
	}

}

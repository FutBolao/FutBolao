package br.com.futbolao.grupo;

public class Grupo {
	
	private long id;
	private String nome;
	private double valorAposta;
	private long limiteApostas;
	private int limiteApostasPorApostador;
	private int percentualLucroAdministrador;
	private String dataEncerramentoAposta;
	private int idCompeticao;
	private int idRodada;
	private int pontuacaoPorResultado;
	private int pontuacaoPorPlacar;
		
	public Grupo(long id, String nome, double valorAposta, long limiteApostas,
			int limiteApostasPorApostador, int percentualLucroAdministrador,
			String dataEncerramentoAposta, int idCompeticao, int idRodada,
			int pontuacaoPorResultado, int pontuacaoPorPlacar) {
		super();
		this.id = id;
		this.nome = nome;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	@Override
	public String toString() {
		return "Aposta [id=" + id + ", nome=" + nome + ", valorAposta=" + valorAposta
				+ ", limiteApostas=" + limiteApostas
				+ ", limiteApostasPorApostador=" + limiteApostasPorApostador
				+ ", percentualLucroAdministrador="
				+ percentualLucroAdministrador + ", dataEncerramentoAposta="
				+ dataEncerramentoAposta + ",idRodada=" + idRodada + " ,idRodada=" + idRodada
				+ ", pontuacaoPorResultado=" + pontuacaoPorResultado
				+ ", pontuacaoPorPlacar=" + pontuacaoPorPlacar + "]";
	}

}

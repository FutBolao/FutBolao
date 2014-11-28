package br.com.futbolao.rodada;

public class Rodada {
	
	private long id;
	private int idCompeticao;
	private String nomeCompeticao;
	private int numeroRodada;
	private int idJogo;
	private String dataHora;
	private int clube1;
	private String nomeClube1;
	private int resultadoClube1;
	private int clube2;
	private String nomeClube2;
	private int resultadoClube2;
	
	public Rodada(long id, int idCompeticao, int numeroRodada, int idJogo,
			String dataHora, int clube1, int resultadoClube1, int clube2,
			int resultadoClube2) {
		super();
		this.id = id;
		this.idCompeticao = idCompeticao;
		this.numeroRodada = numeroRodada;
		this.idJogo = idJogo;
		this.dataHora = dataHora;
		this.clube1 = clube1;
		this.resultadoClube1 = resultadoClube1;
		this.clube2 = clube2;
		this.resultadoClube2 = resultadoClube2;
	}

	public Rodada(long id, int idCompeticao, String nomeCompeticao,
			int numeroRodada, int idJogo, String dataHora, int clube1,
			String nomeClube1, int resultadoClube1, int clube2,
			String nomeClube2, int resultadoClube2) {
		super();
		this.id = id;
		this.idCompeticao = idCompeticao;
		this.nomeCompeticao = nomeCompeticao;
		this.numeroRodada = numeroRodada;
		this.idJogo = idJogo;
		this.dataHora = dataHora;
		this.clube1 = clube1;
		this.nomeClube1 = nomeClube1;
		this.resultadoClube1 = resultadoClube1;
		this.clube2 = clube2;
		this.nomeClube2 = nomeClube2;
		this.resultadoClube2 = resultadoClube2;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getNumeroRodada() {
		return numeroRodada;
	}

	public void setNumeroRodada(int numeroRodada) {
		this.numeroRodada = numeroRodada;
	}

	public int getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(int idJogo) {
		this.idJogo = idJogo;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public int getClube1() {
		return clube1;
	}

	public void setClube1(int clube1) {
		this.clube1 = clube1;
	}

	public String getNomeClube1() {
		return nomeClube1;
	}

	public void setNomeClube1(String nomeClube1) {
		this.nomeClube1 = nomeClube1;
	}

	public int getResultadoClube1() {
		return resultadoClube1;
	}

	public void setResultadoClube1(int resultadoClube1) {
		this.resultadoClube1 = resultadoClube1;
	}

	public int getClube2() {
		return clube2;
	}

	public void setClube2(int clube2) {
		this.clube2 = clube2;
	}

	public String getNomeClube2() {
		return nomeClube2;
	}

	public void setNomeClube2(String nomeClube2) {
		this.nomeClube2 = nomeClube2;
	}

	public int getResultadoClube2() {
		return resultadoClube2;
	}

	public void setResultadoClube2(int resultadoClube2) {
		this.resultadoClube2 = resultadoClube2;
	}

	@Override
	public String toString() {
		return "Rodada [id=" + id + ", idCompeticao=" + idCompeticao
				+ ", nomeCompeticao=" + nomeCompeticao + ", numeroRodada="
				+ numeroRodada + ", idJogo=" + idJogo + ", dataHora="
				+ dataHora + ", clube1=" + clube1 + ", nomeClube1="
				+ nomeClube1 + ", resultadoClube1=" + resultadoClube1
				+ ", clube2=" + clube2 + ", nomeClube2=" + nomeClube2
				+ ", resultadoClube2=" + resultadoClube2 + "]";
	}

}

package br.com.futbolao.aposta;

public class Aposta {
	
	private long id;
	private long idApostador;
	private String nomeApostador;
	private long idGrupo;
	private String dataAposta;
	private int idJogo;
	private int clube1;
	private String nomeClube1;
	private int resultadoClube1;
	private int clube2;
	private String nomeClube2;
	private int resultadoClube2;
	private double valorAposta;
	private char ativa;
	
	public Aposta(long id, long idApostador, long idGrupo,
			int idJogo, int clube1, int resultadoClube1, int clube2,
			int resultadoClube2) {
		super();
		this.id = id;
		this.idApostador = idApostador;
		this.idGrupo = idGrupo;
		this.idJogo = idJogo;
		this.clube1 = clube1;
		this.resultadoClube1 = resultadoClube1;
		this.clube2 = clube2;
		this.resultadoClube2 = resultadoClube2;
	}

	public Aposta(long id, long idApostador, String nomeApostador,
			long idGrupo, String dataAposta, int idJogo, int clube1,
			String nomeClube1, int resultadoClube1, int clube2,
			String nomeClube2, int resultadoClube2) {
		super();
		this.id = id;
		this.idApostador = idApostador;
		this.nomeApostador = nomeApostador;
		this.idGrupo = idGrupo;
		this.dataAposta = dataAposta;
		this.idJogo = idJogo;
		this.clube1 = clube1;
		this.nomeClube1 = nomeClube1;
		this.resultadoClube1 = resultadoClube1;
		this.clube2 = clube2;
		this.nomeClube2 = nomeClube2;
		this.resultadoClube2 = resultadoClube2;
	}
	
	public Aposta(long id, long idApostador, String nomeApostador, 
			long idGrupo, double valorAposta, String dataAposta, char ativa){
		super();	
		this.id = id;
		this.idApostador = idApostador;
		this.nomeApostador = nomeApostador;
		this.idGrupo = idGrupo;
		this.valorAposta = valorAposta;
		this.dataAposta = dataAposta;
		this.ativa = ativa;
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

	public long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getDataAposta() {
		return dataAposta;
	}

	public void setDataAposta(String dataAposta) {
		this.dataAposta = dataAposta;
	}

	public int getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(int idJogo) {
		this.idJogo = idJogo;
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
		return "Aposta [id=" + id + ", idApostador=" + idApostador
				+ ", nomeApostador=" + nomeApostador + ", idGrupo=" + idGrupo
				+ ", dataAposta=" + dataAposta + ", idJogo=" + idJogo
				+ ", clube1=" + clube1 + ", nomeClube1=" + nomeClube1
				+ ", resultadoClube1=" + resultadoClube1 + ", clube2=" + clube2
				+ ", nomeClube2=" + nomeClube2 + ", resultadoClube2="
				+ resultadoClube2 + "]";
	}
	
}

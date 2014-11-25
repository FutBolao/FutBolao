package br.com.futbolao.apostador;

import br.com.futbolao.pessoa.Pessoa;
import br.com.futbolao.util.Endereco;

public class Apostador extends Pessoa {
	
	private long id;
	private String clube;

	public Apostador(long id, String nome, String cpf, char sexo, String telefone,
			String email, Endereco endereco, String dataDeNascimento,
			String usuario, String senha, String clube) {
		super(nome, cpf, sexo, telefone, email, endereco, dataDeNascimento,
				usuario, senha);
		this.id = id;
		this.clube = clube;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getClube() {
		return clube;
	}

	public void setClube(String clube) {
		this.clube = clube;
	}

	@Override
	public String toString() {
		return "Apostador [id=" + id + ", clube=" + clube + ", nome=" + nome
				+ ", cpf=" + cpf + ", sexo=" + sexo + ", telefone=" + telefone
				+ ", email=" + email + ", endereco=" + endereco
				+ ", dataDeNascimento=" + dataDeNascimento + ", usuario="
				+ usuario + ", senha=" + senha + "]";
	}



}

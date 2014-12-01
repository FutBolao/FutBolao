package br.com.futbolao.administrador;

import br.com.futbolao.pessoa.Pessoa;
import br.com.futbolao.util.Endereco;

public class Administrador extends Pessoa {
	
	private int id;
	private char ativo;

	public Administrador(int id, String nome, String cpf, char sexo, String telefone,
			String email, Endereco endereco, String dataDeNascimento,
			String usuario, String senha, char ativo) {
		super(nome, cpf, sexo, telefone, email, endereco, dataDeNascimento,
				usuario, senha);
		this.id = id;
		this.ativo = ativo;
	}
	
	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Apostador [id=" + id + ", ativo=" + ativo + ", nome=" + nome
				+ ", cpf=" + cpf + ", sexo=" + sexo + ", telefone=" + telefone
				+ ", email=" + email + ", endereco=" + endereco
				+ ", dataDeNascimento=" + dataDeNascimento + ", usuario="
				+ usuario + ", senha=" + senha + ", ativo=" + ativo + "]";
	}



}

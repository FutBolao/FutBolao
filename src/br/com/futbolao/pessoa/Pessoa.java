package br.com.futbolao.pessoa;

import br.com.futbolao.util.Endereco;

public abstract class Pessoa {
	
	protected String nome;
	protected String cpf;
	protected char sexo;
	protected String telefone;
	protected String email;
	protected Endereco endereco;
	protected String dataDeNascimento;
	protected String usuario;
	protected String senha;

	public Pessoa(String nome, String cpf, char sexo, String telefone,
			String email, Endereco endereco, String dataDeNascimento,
			String usuario, String senha) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.dataDeNascimento = dataDeNascimento;
		this.usuario = usuario;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", sexo=" + sexo
				+ ", telefone=" + telefone + ", email=" + email + ", endereco="
				+ endereco + ", dataDeNascimento=" + dataDeNascimento
				+ ", usuario=" + usuario + ", senha=" + senha + ", ativo=]";
	}

}

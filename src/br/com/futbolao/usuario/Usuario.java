package br.com.futbolao.usuario;

import br.com.futbolao.pessoa.Pessoa;
import br.com.futbolao.util.Endereco;

public class Usuario extends Pessoa {
	
	private char privilegio;

	public Usuario(String nome, String cpf, char sexo, String telefone,
			String email, Endereco endereco, String dataDeNascimento,
			String usuario, String senha, char privilegio) {
		super(nome, cpf, sexo, telefone, email, endereco, dataDeNascimento,
				usuario, senha);
		this.privilegio = privilegio;
	}

	public char getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(char privilegio) {
		this.privilegio = privilegio;
	}

	@Override
	public String toString() {
		return "Administrador [privilegio=" + privilegio + "]";
	}

}

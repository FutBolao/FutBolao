package br.com.futbolao.permissao;

public class Permissao {
	
	private int idAdminstrador;
	private int permissao;
	private int modulo;
	
	public Permissao(int idAdminstrador, int permissao, int modulo) {
		super();
		this.idAdminstrador = idAdminstrador;
		this.permissao = permissao;
		this.modulo = modulo;
	}

	public int getIdAdminstrador() {
		return idAdminstrador;
	}

	public void setIdAdminstrador(int idAdminstrador) {
		this.idAdminstrador = idAdminstrador;
	}

	public int getPermissao() {
		return permissao;
	}

	public void setPermissao(int permissao) {
		this.permissao = permissao;
	}

	public int getModulo() {
		return modulo;
	}

	public void setModulo(int modulo) {
		this.modulo = modulo;
	}

	@Override
	public String toString() {
		return "Permissao [idAdminstrador=" + idAdminstrador + ", permissao="
				+ permissao + ", modulo=" + modulo + "]";
	}
	
}

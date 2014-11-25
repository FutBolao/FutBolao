package br.com.futbolao.permissao;

import java.sql.SQLException;
import br.com.futbolao.exception.PermissaoJaCadastradaException;
import br.com.futbolao.exception.PermissaoNaoCadastradaException;


public class ControladorPermissao {

	private IRepositorioPermissao repositorio;
	
	public ControladorPermissao() throws Exception{
		this.repositorio = new RepositorioPermissao();
	}
	
	public void cadastrar(Permissao permissao) throws SQLException, PermissaoJaCadastradaException, Exception{	
			if (permissao != null) {
				repositorio.cadastrar(permissao);
			}
	}
	
	public Permissao listar(int administrador, int modulo) throws SQLException, PermissaoNaoCadastradaException, Exception{
		Permissao permissao = null;
		if (administrador != 0 && modulo != 0) {
			permissao = repositorio.listar(administrador, modulo);
		}else {
			throw new PermissaoNaoCadastradaException();
		}
		return permissao;
	}
	
	
	public void atualizar(Permissao permissao) throws SQLException, PermissaoNaoCadastradaException, Exception{
			if (permissao != null) {
				repositorio.atualizar(permissao);
			}
	}
	
	public void deletar(int administrador) throws SQLException, PermissaoNaoCadastradaException, Exception{
		if (administrador > 0) {
			repositorio.deletar(administrador);
		}else {
			throw new PermissaoNaoCadastradaException();
		}
	}
}

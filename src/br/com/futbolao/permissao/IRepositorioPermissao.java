package br.com.futbolao.permissao;

import java.sql.SQLException;
import br.com.futbolao.exception.PermissaoJaCadastradaException;
import br.com.futbolao.exception.PermissaoNaoCadastradaException;

public interface IRepositorioPermissao {

	public void cadastrar(Permissao permissao) throws SQLException, PermissaoJaCadastradaException, Exception;
	public Permissao listar(int administrador, int modulo) throws SQLException, PermissaoNaoCadastradaException, Exception;
	public void atualizar(Permissao permissao) throws SQLException, PermissaoNaoCadastradaException, Exception;
	public void deletar(int administrador) throws SQLException, PermissaoNaoCadastradaException, Exception;
	public boolean existe(int administrador, int modulo) throws SQLException, Exception;
}

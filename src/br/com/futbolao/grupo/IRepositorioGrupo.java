package br.com.futbolao.grupo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.GrupoJaCadastradoException;
import br.com.futbolao.exception.GrupoNaoCadastradoException;

public interface IRepositorioGrupo {

	public void cadastrar(Grupo grupo) throws SQLException, GrupoJaCadastradoException, Exception;
	public ArrayList<Grupo> procurarPorNome(String nome) throws SQLException, GrupoNaoCadastradoException, Exception;
	public Grupo procurarPorId(long id) throws SQLException, GrupoNaoCadastradoException, Exception;
	public ArrayList<Grupo> listar() throws SQLException, GrupoNaoCadastradoException, Exception;
	public void atualizar(Grupo grupo) throws SQLException, GrupoNaoCadastradoException, Exception;
	public void deletar(long id) throws SQLException, GrupoNaoCadastradoException, Exception;
	public boolean existe(Grupo grupo) throws SQLException, Exception;
}

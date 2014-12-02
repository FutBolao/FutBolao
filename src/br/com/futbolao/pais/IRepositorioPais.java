package br.com.futbolao.pais;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.PaisJaCadastradoException;
import br.com.futbolao.exception.PaisNaoCadastradoException;

public interface IRepositorioPais {

	public void cadastrar(Pais pais) throws SQLException, PaisJaCadastradoException, Exception;
	public ArrayList<Pais> procurarPorNome(String nome) throws SQLException, PaisNaoCadastradoException, Exception;
	public ArrayList<Pais> listar() throws SQLException, PaisNaoCadastradoException, Exception;
	public void atualizar(Pais pais) throws SQLException, PaisNaoCadastradoException, Exception;
	public void deletar(int id) throws SQLException, PaisNaoCadastradoException, Exception;
	public boolean existe(String nome) throws SQLException, Exception;
}

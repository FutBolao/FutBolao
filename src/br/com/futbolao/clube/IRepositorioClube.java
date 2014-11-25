package br.com.futbolao.clube;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.ClubeJaCadastradoException;
import br.com.futbolao.exception.ClubeNaoCadastradoException;

public interface IRepositorioClube {

	public void cadastrar(Clube clube) throws SQLException, ClubeJaCadastradoException, Exception;
	public ArrayList<Clube> procurarPorNome(String nome) throws SQLException, ClubeNaoCadastradoException, Exception;
	public ArrayList<Clube> listar() throws SQLException, ClubeNaoCadastradoException, Exception;
	public void atualizar(Clube clube) throws SQLException, ClubeNaoCadastradoException, Exception;
	public void deletar(int id) throws SQLException, ClubeNaoCadastradoException, Exception;
	public boolean existe(String nome) throws SQLException, Exception;
}

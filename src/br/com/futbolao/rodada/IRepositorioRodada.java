package br.com.futbolao.rodada;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.RodadaJaCadastradaException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;

public interface IRepositorioRodada {
	public void cadastrar(Rodada rodada) throws SQLException, RodadaJaCadastradaException, Exception;
	public ArrayList<Rodada> listar() throws SQLException, RodadaNaoCadastradaException, Exception;
	public <T> ArrayList<T> procurar(int idCompeticao, int numeroDaRodada) throws SQLException, RodadaNaoCadastradaException, Exception;
	public void atualizar(Rodada rodada) throws SQLException, RodadaNaoCadastradaException, Exception;
	public void deletar(long id) throws SQLException, RodadaNaoCadastradaException, Exception;
	public boolean existe(int idCompeticao, int numeroDaRodada) throws SQLException, Exception;
}

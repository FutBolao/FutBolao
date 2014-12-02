package br.com.futbolao.estado;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.EstadoJaCadastradoException;
import br.com.futbolao.exception.EstadoNaoCadastradoException;

public interface IRepositorioEstado {

	public void cadastrar(Estado estado) throws SQLException, EstadoJaCadastradoException, Exception;
	public ArrayList<Estado> procurarPorNome(String nome) throws SQLException, EstadoNaoCadastradoException, Exception;
	public ArrayList<Estado> procurarPorPais(int idPais) throws SQLException, EstadoNaoCadastradoException, Exception;
	public void atualizar(Estado estado) throws SQLException, EstadoNaoCadastradoException, Exception;
	public void deletar(int id) throws SQLException, EstadoNaoCadastradoException, Exception;
	public boolean existe(String nome, int idPais) throws SQLException, Exception;
}

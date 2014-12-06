package br.com.futbolao.competicao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.CompeticaoJaCadastradaException;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;

public interface IRepositorioCompeticao {

	public void cadastrar(Competicao competicao) throws SQLException, CompeticaoJaCadastradaException, Exception;
	public ArrayList<Competicao> listar(String complemento) throws SQLException, CompeticaoNaoCadastradaException, Exception;
	public ArrayList<Competicao> listar() throws SQLException, CompeticaoNaoCadastradaException, Exception;
	public void atualizar(Competicao competicao) throws SQLException, CompeticaoNaoCadastradaException, Exception;
	public void deletar(int id) throws SQLException, CompeticaoNaoCadastradaException, Exception;
	public boolean existe(String nome) throws SQLException, Exception;
}

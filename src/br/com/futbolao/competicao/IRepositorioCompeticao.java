package br.com.futbolao.competicao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.CompeticaoJaCadastradaException;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;

public interface IRepositorioCompeticao {

	public void cadastrar(Competicao competicao) throws SQLException, CompeticaoJaCadastradaException, Exception;
	public ArrayList<Competicao> listar(String complemento) throws SQLException, CompeticaoNaoCadastradaException, Exception;
	public ArrayList<Competicao> procurarPorNome(String nome) throws SQLException, CompeticaoNaoCadastradaException, Exception;
	public Competicao procurarPorId(int id) throws SQLException, CompeticaoNaoCadastradaException, Exception;
	public ArrayList<Competicao> listarCompeticaoComRodada() throws SQLException, CompeticaoNaoCadastradaException, Exception;
	public ArrayList<Competicao> listarCompeticaoComGrupo(char ativo) throws SQLException, CompeticaoNaoCadastradaException, Exception;
	public ArrayList<Competicao> listar(char ativo) throws SQLException, CompeticaoNaoCadastradaException, Exception;
	public void atualizar(Competicao competicao) throws SQLException, CompeticaoNaoCadastradaException, Exception;
	public void deletar(int id) throws SQLException, CompeticaoNaoCadastradaException, Exception;
	public boolean existe(String nome) throws SQLException, Exception;
}

package br.com.futbolao.competicao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.CompeticaoJaCadastradaException;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;
import br.com.futbolao.exception.IdInvalidoException;
import br.com.futbolao.exception.NomeVazioException;

public class ControladorCompeticao {

private IRepositorioCompeticao repositorio;
	
	public ControladorCompeticao() throws Exception{
		this.repositorio = new RepositorioCompeticao();
	}
	
	public void cadastrar(Competicao competicao) throws SQLException, CompeticaoJaCadastradaException, NomeVazioException, Exception{
		if (!competicao.getNome().equals("")) {
			repositorio.cadastrar(competicao);
		} else {
			throw new NomeVazioException();
		}
	}
	
	public ArrayList<Competicao> listar(String nome) throws SQLException, CompeticaoNaoCadastradaException, Exception{
		ArrayList<Competicao> competicoes = new ArrayList<Competicao>();
		if (!nome.equals("")) {
			competicoes = repositorio.listar(" and nome like '" + nome + "'");
		}else {
			throw new CompeticaoNaoCadastradaException();
		}
		return competicoes;
	}
	
	public ArrayList<Competicao> procurarPorNome(String nome) throws SQLException, NomeVazioException, Exception{
		ArrayList<Competicao> competicoes = new ArrayList<Competicao>();
		if (!nome.equals("")) {
			competicoes = repositorio.procurarPorNome(nome);
		}else {
			throw new NomeVazioException();
		}
		return competicoes;
	}
	
	public ArrayList<Competicao> listar() throws SQLException, CompeticaoNaoCadastradaException, Exception{
		return repositorio.listar();
	}
	
	public Competicao procurarPorId(int id) throws SQLException, CompeticaoNaoCadastradaException, Exception{
		Competicao competicao = null;
		if(id > 0){
			competicao = repositorio.procurarPorId(id);
		}else{
			throw new IdInvalidoException();
		}
		return competicao;
	}
	
	public void atualizar(Competicao competicao) throws SQLException, CompeticaoNaoCadastradaException, Exception{	
			if (competicao != null) {
				repositorio.atualizar(competicao);
			}
	}
	
	public void deletar(int id) throws SQLException, CompeticaoNaoCadastradaException, Exception{
		if (id > 0) {
			repositorio.deletar(id);
		}else {
			throw new CompeticaoNaoCadastradaException();
		}
	}
}	

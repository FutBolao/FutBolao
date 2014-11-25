package br.com.futbolao.competicao;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.futbolao.exception.CompeticaoJaCadastradaException;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;
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

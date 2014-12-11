package br.com.futbolao.rodada;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.ClubeJaCadastradoNessaRodadaException;
import br.com.futbolao.exception.JogoJaCadastradoNessaRodadaException;
import br.com.futbolao.exception.JogoNaoCadastradoNessaRodadaException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;
import br.com.futbolao.exception.RodadaTravadaException;


public class ControladorRodada {

	private IRepositorioRodada repositorio;
	
	public ControladorRodada() throws Exception{
		this.repositorio = new RepositorioRodada();
	}
	
	public void cadastrar(Rodada rodada) throws SQLException, RodadaTravadaException, ClubeJaCadastradoNessaRodadaException, JogoJaCadastradoNessaRodadaException, Exception{	
			if (rodada != null) {
				repositorio.cadastrar(rodada);
			}
	}
	
	public ArrayList<Rodada> procurar(int idCompeticao, int numeroDaRodada) throws SQLException, RodadaNaoCadastradaException, Exception{
		return repositorio.procurar(idCompeticao, numeroDaRodada);
	}
	
	public ArrayList<Rodada> listar() throws SQLException, RodadaNaoCadastradaException, Exception{
		return repositorio.listar();
	}

	public ArrayList<Integer> listarPorCompeticao(int idCompeticao, char trava) throws SQLException, RodadaNaoCadastradaException, Exception{
		return repositorio.listarPorCompeticao(idCompeticao, trava);
	}
	
	public ArrayList<Integer> listarPorCompeticaoComGrupo(int idCompeticao, char ativo) throws SQLException, RodadaNaoCadastradaException, Exception{
		return repositorio.listarPorCompeticaoComGrupo(idCompeticao, ativo);
	}
	
	public Rodada procurarPorId(long id) throws SQLException, RodadaNaoCadastradaException, Exception{
		return repositorio.procurarPorId(id);
	}
	
	public void atualizar(Rodada rodada) throws SQLException, ClubeJaCadastradoNessaRodadaException, JogoJaCadastradoNessaRodadaException, JogoNaoCadastradoNessaRodadaException, Exception{
			if (rodada != null) {
				repositorio.atualizar(rodada);
			}
	}
	
	public void travar(int idCompeticao, int numeroDaRodada) throws SQLException, Exception{
		repositorio.travar(idCompeticao, numeroDaRodada);
	}

	public void destravar(int idCompeticao, int numeroDaRodada) throws SQLException, Exception{
		repositorio.destravar(idCompeticao, numeroDaRodada);
	}
	
	public void deletar(long id) throws SQLException, RodadaNaoCadastradaException, Exception{
		if (id > 0) {
			repositorio.deletar(id);
		}else {
			throw new RodadaNaoCadastradaException();
		}
	}
}

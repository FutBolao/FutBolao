package br.com.futbolao.movimentacao.financeira.apostador;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.futbolao.exception.RodadaJaCadastradaException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;


public class ControladorMovimentacaoFinanceiraApostador {

	private IRepositorioMovimentacaoFinanceiraApostador repositorio;
	
	public ControladorMovimentacaoFinanceiraApostador() throws Exception{
		this.repositorio = new RepositorioMovimentacaoFinanceiraApostador();
	}
	
	public void cadastrar(MovimentacaoFinanceiraApostador movimentacaoFinanceiraApostador) throws SQLException, RodadaJaCadastradaException, Exception{	
			if (movimentacaoFinanceiraApostador != null) {
				repositorio.cadastrar(movimentacaoFinanceiraApostador);
			}
	}
	
	public ArrayList<MovimentacaoFinanceiraApostador> procurar(int idCompeticao, int numeroDaRodada) throws SQLException, RodadaNaoCadastradaException, Exception{
		return repositorio.procurar(idCompeticao, numeroDaRodada);
	}
	
	public ArrayList<MovimentacaoFinanceiraApostador> listar() throws SQLException, RodadaNaoCadastradaException, Exception{
		return repositorio.listar();
	}
	
	
	public void atualizar(MovimentacaoFinanceiraApostador movimentacaoFinanceiraApostador) throws SQLException, RodadaNaoCadastradaException, Exception{
			if (movimentacaoFinanceiraApostador != null) {
				repositorio.atualizar(movimentacaoFinanceiraApostador);
			}
	}
	
	public void deletar(int id) throws SQLException, RodadaNaoCadastradaException, Exception{
		if (id > 0) {
			repositorio.deletar(id);
		}else {
			throw new RodadaNaoCadastradaException();
		}
	}
}

package br.com.futbolao.movimentacao.financeira.apostador;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.futbolao.exception.MovimentacaoNaoCadastradaException;


public class ControladorMovimentacaoFinanceiraApostador {

	private IRepositorioMovimentacaoFinanceiraApostador repositorio;
	
	public ControladorMovimentacaoFinanceiraApostador() throws Exception{
		this.repositorio = new RepositorioMovimentacaoFinanceiraApostador();
	}
	
	public void cadastrar(MovimentacaoFinanceiraApostador movimentacaoFinanceiraApostador) throws SQLException, Exception{	
			if (movimentacaoFinanceiraApostador != null) {
				repositorio.cadastrar(movimentacaoFinanceiraApostador);
			}
	}
	
	public ArrayList<MovimentacaoFinanceiraApostador> procurar(int idApostador, String tipoMovimentacao) throws SQLException, MovimentacaoNaoCadastradaException, Exception{
		return repositorio.procurar(idApostador, tipoMovimentacao);
	}
	
	public ArrayList<MovimentacaoFinanceiraApostador> listar() throws SQLException, MovimentacaoNaoCadastradaException, Exception{
		return repositorio.listar();
	}
	
	
	public void atualizar(MovimentacaoFinanceiraApostador movimentacaoFinanceiraApostador) throws SQLException, MovimentacaoNaoCadastradaException, Exception{
			if (movimentacaoFinanceiraApostador != null) {
				repositorio.atualizar(movimentacaoFinanceiraApostador);
			}
	}
	
	public void deletar(int id) throws SQLException, MovimentacaoNaoCadastradaException, Exception{
		if (id > 0) {
			repositorio.deletar(id);
		}else {
			throw new MovimentacaoNaoCadastradaException();
		}
	}
}

package br.com.futbolao.movimentacao.financeira.administrador;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.futbolao.exception.MovimentacaoNaoCadastradaException;


public class ControladorMovimentacaoFinanceiraAdministrador {

	private IRepositorioMovimentacaoFinanceiraAdministrador repositorio;
	
	public ControladorMovimentacaoFinanceiraAdministrador() throws Exception{
		this.repositorio = new RepositorioMovimentacaoFinanceiraAdministrador();
	}
	
	public void cadastrar(MovimentacaoFinanceiraAdministrador movimentacaoFinanceiraAdministrador) throws SQLException, Exception{	
			if (movimentacaoFinanceiraAdministrador != null) {
				repositorio.cadastrar(movimentacaoFinanceiraAdministrador);
			}
	}
	
	public ArrayList<MovimentacaoFinanceiraAdministrador> procurar(int idApostador, String tipoMovimentacao) throws SQLException, MovimentacaoNaoCadastradaException, Exception{
		return repositorio.procurar(idApostador, tipoMovimentacao);
	}
	
	public ArrayList<MovimentacaoFinanceiraAdministrador> listar() throws SQLException, MovimentacaoNaoCadastradaException, Exception{
		return repositorio.listar();
	}
	
	
	public void atualizar(MovimentacaoFinanceiraAdministrador movimentacaoFinanceiraAdministrador) throws SQLException, MovimentacaoNaoCadastradaException, Exception{
			if (movimentacaoFinanceiraAdministrador != null) {
				repositorio.atualizar(movimentacaoFinanceiraAdministrador);
			}
	}
	
	public void deletar(long id) throws SQLException, MovimentacaoNaoCadastradaException, Exception{
		if (id > 0) {
			repositorio.deletar(id);
		}else {
			throw new MovimentacaoNaoCadastradaException();
		}
	}
}

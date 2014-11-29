package br.com.futbolao.movimentacao.financeira.administrador;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.futbolao.exception.MovimentacaoNaoCadastradaException;

public interface IRepositorioMovimentacaoFinanceiraAdministrador {
	public void cadastrar(MovimentacaoFinanceiraAdministrador movimentacaoFinanceiraAdministrador) throws SQLException, Exception;
	public ArrayList<MovimentacaoFinanceiraAdministrador> listar() throws SQLException, MovimentacaoNaoCadastradaException, Exception;
	public ArrayList<MovimentacaoFinanceiraAdministrador> procurar(int idApostador, String tipoMovimentacao) throws SQLException, MovimentacaoNaoCadastradaException, Exception;
	public void atualizar(MovimentacaoFinanceiraAdministrador movimentacaoFinanceiraAdministrador) throws SQLException, MovimentacaoNaoCadastradaException, Exception;
	public void deletar(long id) throws SQLException, MovimentacaoNaoCadastradaException, Exception;
	public boolean existe(int id) throws SQLException, Exception;
}

package br.com.futbolao.movimentacao.financeira.apostador;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.futbolao.exception.MovimentacaoJaCadastradaException;
import br.com.futbolao.exception.MovimentacaoNaoCadastradaException;

public interface IRepositorioMovimentacaoFinanceiraApostador {
	public void cadastrar(MovimentacaoFinanceiraApostador movimentacaoFinanceiraApostador) throws SQLException, MovimentacaoJaCadastradaException, Exception;
	public ArrayList<MovimentacaoFinanceiraApostador> listar() throws SQLException, MovimentacaoNaoCadastradaException, Exception;
	public ArrayList<MovimentacaoFinanceiraApostador> procurar(int idCompeticao, int numeroDaRodada) throws SQLException, MovimentacaoNaoCadastradaException, Exception;
	public void atualizar(MovimentacaoFinanceiraApostador movimentacaoFinanceiraApostador) throws SQLException, MovimentacaoNaoCadastradaException, Exception;
	public void deletar(long id) throws SQLException, MovimentacaoNaoCadastradaException, Exception;
	public boolean existe(int idCompeticao, int numeroDaRodada) throws SQLException, Exception;
}

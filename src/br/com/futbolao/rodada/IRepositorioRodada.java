package br.com.futbolao.rodada;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.ClubeJaCadastradoNessaRodadaException;
import br.com.futbolao.exception.JogoJaCadastradoNessaRodadaException;
import br.com.futbolao.exception.JogoNaoCadastradoNessaRodadaException;
import br.com.futbolao.exception.RodadaTravadaException;

public interface IRepositorioRodada {
	public void cadastrar(Rodada rodada) throws SQLException, RodadaTravadaException, ClubeJaCadastradoNessaRodadaException, JogoJaCadastradoNessaRodadaException, Exception;
	public ArrayList<Rodada> listar() throws SQLException, JogoNaoCadastradoNessaRodadaException, Exception;
	public Rodada procurarPorId(long id) throws SQLException, JogoNaoCadastradoNessaRodadaException, Exception;
	public ArrayList<Rodada> procurar(int idCompeticao, int numeroDaRodada) throws SQLException, JogoNaoCadastradoNessaRodadaException, Exception;
	public ArrayList<Integer> listarPorCompeticao(int idCompeticao, char trava) throws SQLException, JogoNaoCadastradoNessaRodadaException, Exception;
	public ArrayList<Integer> listarPorCompeticaoComGrupo(int idCompeticao) throws SQLException, JogoNaoCadastradoNessaRodadaException, Exception;
	public void atualizar(Rodada rodada) throws SQLException, ClubeJaCadastradoNessaRodadaException, JogoJaCadastradoNessaRodadaException, JogoNaoCadastradoNessaRodadaException, Exception;
	public void travar(int idCompeticao, int numeroRodada) throws SQLException, Exception;
	public void destravar(int idCompeticao, int numeroRodada) throws SQLException, Exception;
	public void deletar(long id) throws SQLException, JogoNaoCadastradoNessaRodadaException, Exception;
	public boolean existe(long id, int idCompeticao, int numeroDaRodada, int idJogo) throws SQLException, Exception;
}

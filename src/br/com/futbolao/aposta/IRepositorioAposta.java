package br.com.futbolao.aposta;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.ApostaNaoCadastradaException;

public interface IRepositorioAposta {
	
	public void cadastrar(ArrayList<Aposta> aposta) throws SQLException, ApostaNaoCadastradaException, Exception;
	public ArrayList<Aposta> procurarPorApostador(long idApostador, char ativa) throws SQLException, ApostaNaoCadastradaException, Exception;
	public ArrayList<Aposta> procurarPorGrupo(long idGrupo, char ativa) throws SQLException, ApostaNaoCadastradaException, Exception;
	public void deletar(long id) throws SQLException, ApostaNaoCadastradaException, Exception;

}

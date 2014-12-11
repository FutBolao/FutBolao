package br.com.futbolao.aposta;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.futbolao.exception.ApostaNaoCadastradaException;

public class ControladorAposta {

	private IRepositorioAposta repositorio;
	
	public ControladorAposta() throws Exception{
		this.repositorio = new RepositorioAposta();
	}
	
	public void cadastrar(ArrayList<Aposta> aposta) throws SQLException, ApostaNaoCadastradaException, Exception{	
			if (aposta != null) {
				repositorio.cadastrar(aposta);
			}
	}
	
	public ArrayList<Aposta> procurarPorApostador(long idApostador, char ativa) throws SQLException, ApostaNaoCadastradaException, Exception{
		ArrayList<Aposta> aposta = new ArrayList<Aposta>();
		if (idApostador > 0 && ativa != ' ') {
			aposta = repositorio.procurarPorApostador(idApostador, ativa);
		}else {
			throw new ApostaNaoCadastradaException();
		}
		return aposta;
	}

	public ArrayList<Aposta> procurarPorGrupo(long idGrupo, char ativa) throws SQLException, ApostaNaoCadastradaException, Exception{
		ArrayList<Aposta> aposta = new ArrayList<Aposta>();
		if (idGrupo > 0 && ativa != ' ') {
			aposta = repositorio.procurarPorGrupo(idGrupo, ativa);
		}else {
			throw new ApostaNaoCadastradaException();
		}
		return aposta;
	}

	
	public void deletar(long id) throws SQLException, ApostaNaoCadastradaException, Exception{
		if (id > 0) {
			repositorio.deletar(id);
		}else {
			throw new ApostaNaoCadastradaException();
		}
	}
}

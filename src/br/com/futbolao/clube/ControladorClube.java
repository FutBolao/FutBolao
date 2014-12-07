package br.com.futbolao.clube;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.ClubeJaCadastradoException;
import br.com.futbolao.exception.ClubeNaoCadastradoException;
import br.com.futbolao.exception.IdInvalidoException;
import br.com.futbolao.exception.NomeVazioException;


public class ControladorClube {

	private IRepositorioClube repositorio;
	
	public ControladorClube() throws Exception{
		this.repositorio = new RepositorioClube();
	}
	
	public void cadastrar(Clube clube) throws SQLException, ClubeJaCadastradoException, Exception{	
			if (clube != null) {
				repositorio.cadastrar(clube);
			}
	}
	
	public ArrayList<Clube> procurarPorNome(String nome) throws SQLException, NomeVazioException, Exception{
		ArrayList<Clube> clubes = new ArrayList<Clube>();
		if (!nome.equals("")) {
			clubes = repositorio.procurarPorNome(nome);
		}else {
			throw new NomeVazioException();
		}
		return clubes;
	}

	public Clube procurarPorId(int id) throws SQLException, IdInvalidoException, Exception{
		Clube clube = null;
		if (id > 0) {
			clube = repositorio.procurarPorId(id);
		}else {
			throw new IdInvalidoException();
		}
		return clube;
	}
	
	public ArrayList<Clube> listar(char ativo) throws SQLException, NomeVazioException, Exception{
		return repositorio.listar(ativo);
	}
	
	
	public void atualizar(Clube clube) throws SQLException, ClubeNaoCadastradoException, Exception{
			if (clube != null) {
				repositorio.atualizar(clube);
			}
	}
	
	public void deletar(int id) throws SQLException, ClubeNaoCadastradoException, Exception{
		if (id > 0) {
			repositorio.deletar(id);
		}else {
			throw new ClubeNaoCadastradoException();
		}
	}
}

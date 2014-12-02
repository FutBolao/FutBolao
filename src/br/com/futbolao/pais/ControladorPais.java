package br.com.futbolao.pais;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.PaisJaCadastradoException;
import br.com.futbolao.exception.PaisNaoCadastradoException;
import br.com.futbolao.exception.NomeVazioException;


public class ControladorPais {

	private IRepositorioPais repositorio;
	
	public ControladorPais() throws Exception{
		this.repositorio = new RepositorioPais();
	}
	
	public void cadastrar(Pais pais) throws SQLException, PaisJaCadastradoException, Exception{	
			if (pais != null) {
				repositorio.cadastrar(pais);
			}
	}
	
	public ArrayList<Pais> procurarPorNome(String nome) throws SQLException, NomeVazioException, Exception{
		ArrayList<Pais> paises = new ArrayList<Pais>();
		if (!nome.equals("")) {
			paises = repositorio.procurarPorNome(nome);
		}else {
			throw new NomeVazioException();
		}
		return paises;
	}
	
	public ArrayList<Pais> listar() throws SQLException, NomeVazioException, Exception{
		return repositorio.listar();
	}
	
	
	public void atualizar(Pais pais) throws SQLException, PaisNaoCadastradoException, Exception{
			if (pais != null) {
				repositorio.atualizar(pais);
			}
	}
	
	public void deletar(int id) throws SQLException, PaisNaoCadastradoException, Exception{
		if (id > 0) {
			repositorio.deletar(id);
		}else {
			throw new PaisNaoCadastradoException();
		}
	}
}

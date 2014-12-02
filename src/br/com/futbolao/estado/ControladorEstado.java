package br.com.futbolao.estado;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.PaisJaCadastradoException;
import br.com.futbolao.exception.PaisNaoCadastradoException;
import br.com.futbolao.exception.NomeVazioException;


public class ControladorEstado {

	private IRepositorioEstado repositorio;
	
	public ControladorEstado() throws Exception{
		this.repositorio = new RepositorioEstado();
	}
	
	public void cadastrar(Estado estado) throws SQLException, PaisJaCadastradoException, Exception{	
			if (estado != null) {
				repositorio.cadastrar(estado);
			}
	}
	
	public ArrayList<Estado> procurarPorNome(String nome) throws SQLException, NomeVazioException, Exception{
		ArrayList<Estado> paises = new ArrayList<Estado>();
		if (!nome.equals("")) {
			paises = repositorio.procurarPorNome(nome);
		}else {
			throw new NomeVazioException();
		}
		return paises;
	}
	
	public void atualizar(Estado estado) throws SQLException, PaisNaoCadastradoException, Exception{
			if (estado != null) {
				repositorio.atualizar(estado);
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

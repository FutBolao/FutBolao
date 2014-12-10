package br.com.futbolao.grupo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.ExisteApostaNesseGrupoException;
import br.com.futbolao.exception.GrupoJaCadastradoException;
import br.com.futbolao.exception.GrupoNaoCadastradoException;
import br.com.futbolao.exception.IdInvalidoException;
import br.com.futbolao.exception.NomeVazioException;


public class ControladorGrupo {

	private IRepositorioGrupo repositorio;
	
	public ControladorGrupo() throws Exception{
		this.repositorio = new RepositorioGrupo();
	}
	
	public void cadastrar(Grupo grupo) throws SQLException, GrupoJaCadastradoException, Exception{	
			if (grupo != null) {
				repositorio.cadastrar(grupo);
			}
	}
	
	public ArrayList<Grupo> procurarPorCompeticao(int idCompeticao, int numeroRodada) throws SQLException, GrupoNaoCadastradoException, Exception{
		ArrayList<Grupo> grupos = new ArrayList<Grupo>();
			grupos = repositorio.procurarPorCompeticao(idCompeticao, numeroRodada);
		return grupos;
	}
	
	public ArrayList<Grupo> listar() throws SQLException, NomeVazioException, Exception{
		return repositorio.listar();
	}
	
	public Grupo procurarPorId(long id) throws SQLException, GrupoNaoCadastradoException, Exception{
		Grupo grupo = null;
		if(id > 0){
			grupo = repositorio.procurarPorId(id);
		}else{
			throw new IdInvalidoException();
		}
		return grupo;
	}
	
	public void atualizar(Grupo grupo) throws SQLException, GrupoNaoCadastradoException, Exception{
			if (grupo != null) {
				repositorio.atualizar(grupo);
			}
	}
	
	public void deletar(long id) throws SQLException, ExisteApostaNesseGrupoException, GrupoNaoCadastradoException, Exception{
		if (id > 0) {
			repositorio.deletar(id);
		}else {
			throw new GrupoNaoCadastradoException();
		}
	}
}

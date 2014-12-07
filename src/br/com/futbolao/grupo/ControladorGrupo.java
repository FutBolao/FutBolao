package br.com.futbolao.grupo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.competicao.Competicao;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;
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
	
	public ArrayList<Grupo> procurarPorNome(String nome) throws SQLException, NomeVazioException, Exception{
		ArrayList<Grupo> grupos = new ArrayList<Grupo>();
		if (!nome.equals("")) {
			grupos = repositorio.procurarPorNome(nome);
		}else {
			throw new NomeVazioException();
		}
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
	
	public void deletar(long id) throws SQLException, GrupoNaoCadastradoException, Exception{
		if (id > 0) {
			repositorio.deletar(id);
		}else {
			throw new GrupoNaoCadastradoException();
		}
	}
}

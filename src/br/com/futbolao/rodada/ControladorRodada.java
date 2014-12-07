package br.com.futbolao.rodada;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.futbolao.exception.RodadaJaCadastradaException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;


public class ControladorRodada {

	private IRepositorioRodada repositorio;
	
	public ControladorRodada() throws Exception{
		this.repositorio = new RepositorioRodada();
	}
	
	public void cadastrar(Rodada rodada) throws SQLException, RodadaJaCadastradaException, Exception{	
			if (rodada != null) {
				repositorio.cadastrar(rodada);
			}
	}
	
	public <T> ArrayList<T> procurar(int idCompeticao, int numeroDaRodada) throws SQLException, RodadaNaoCadastradaException, Exception{
		return repositorio.procurar(idCompeticao, numeroDaRodada);
	}
	
	public ArrayList<Rodada> listar() throws SQLException, RodadaNaoCadastradaException, Exception{
		return repositorio.listar();
	}
	
	
	public void atualizar(Rodada rodada) throws SQLException, RodadaNaoCadastradaException, Exception{
			if (rodada != null) {
				repositorio.atualizar(rodada);
			}
	}
	
	public void deletar(int id) throws SQLException, RodadaNaoCadastradaException, Exception{
		if (id > 0) {
			repositorio.deletar(id);
		}else {
			throw new RodadaNaoCadastradaException();
		}
	}
}

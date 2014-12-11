package br.com.futbolao.ganhadores;

import java.sql.SQLException;
import java.util.ArrayList;


public class ControladorGanhador {

	private IRepositorioGanhador repositorio;
	
	public ControladorGanhador() throws Exception{
		this.repositorio = new RepositorioGanhador();
	}
	
	public void verificar(long idGrupo) throws SQLException, Exception{	
		this.repositorio.verificar(idGrupo);

	}
	
	public ArrayList<Ganhador> listar(long idGrupo) throws SQLException, Exception{
		return this.repositorio.listar(idGrupo);
	}
}

package br.com.futbolao.ganhadores;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepositorioGanhador {

	public void verificar(long idGrupo) throws SQLException, Exception;
	public ArrayList<Ganhador>listar(long idGrupo) throws SQLException, Exception;
}

package br.com.futbolao.administrador;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.futbolao.exception.AdministradorJaCadastradoException;
import br.com.futbolao.exception.AdministradorNaoCadastradoException;

public interface IRepositorioAdministrador {
	
	public void cadastrar(Administrador administrador) throws SQLException, AdministradorJaCadastradoException, Exception;
	public Administrador procurarPorId(long id) throws SQLException, AdministradorNaoCadastradoException, Exception;
	public ArrayList<Administrador> procurarPorNome(String nome) throws SQLException, AdministradorNaoCadastradoException, Exception;
	public ArrayList<Administrador> listar() throws SQLException, AdministradorNaoCadastradoException, Exception;
	public void atualizar(Administrador administrador) throws SQLException, AdministradorNaoCadastradoException, Exception;
	public void deletar(long id) throws SQLException, AdministradorNaoCadastradoException, Exception;
	public boolean existe(String cpf) throws SQLException, Exception;
}

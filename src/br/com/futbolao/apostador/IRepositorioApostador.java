package br.com.futbolao.apostador;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.ApostadorJaCadastradoException;
import br.com.futbolao.exception.ApostadorNaoCadastradoException;

public interface IRepositorioApostador {
	
	public void cadastrar(Apostador apostador) throws SQLException, ApostadorJaCadastradoException, Exception;
	public Apostador procurarPorCpf(String cpf) throws SQLException, ApostadorNaoCadastradoException, Exception;
	public ArrayList<Apostador> procurarPorNome(String nome) throws SQLException, ApostadorNaoCadastradoException, Exception;
	public ArrayList<Apostador> listar() throws SQLException, ApostadorNaoCadastradoException, Exception;
	public void atualizar(Apostador apostador) throws SQLException, ApostadorNaoCadastradoException, Exception;
	public void deletar(long id) throws SQLException, ApostadorNaoCadastradoException, Exception;
	public boolean existe(String cpf) throws SQLException, Exception;
}

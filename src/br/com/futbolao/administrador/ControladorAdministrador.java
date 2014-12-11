package br.com.futbolao.administrador;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.AdministradorJaCadastradoException;
import br.com.futbolao.exception.AdministradorNaoCadastradoException;
import br.com.futbolao.exception.CpfInvalidoException;
import br.com.futbolao.exception.IdInvalidoException;
import br.com.futbolao.exception.NomeVazioException;
import br.com.futbolao.util.Validacao;

public class ControladorAdministrador {

	private IRepositorioAdministrador repositorio;
	public ControladorAdministrador() throws Exception{
		this.repositorio = new RepositorioAdministrador();
	}
	
	public void cadastrar(Administrador administrador) throws SQLException, AdministradorJaCadastradoException, NomeVazioException, CpfInvalidoException, Exception{
		if (administrador != null) {
			if (Validacao.validaCPF(administrador.getCpf())) {
				if (!administrador.getNome().equals("")) {
					administrador.setCpf(administrador.getCpf().replace('.',' ').replace('-',' ').replaceAll(" ", ""));
					administrador.setDataDeNascimento(administrador.getDataDeNascimento().substring(6, 10) + "-" 
							 + administrador.getDataDeNascimento().substring(3, 5) + "-" 
							 + administrador.getDataDeNascimento().substring(0, 2));
					repositorio.cadastrar(administrador);
				} else {
					throw new NomeVazioException();
				}
			} else {
				throw new CpfInvalidoException();
			}
		}
	}
	
	public Administrador procurarPorId(long id) throws IdInvalidoException, SQLException, AdministradorNaoCadastradoException, Exception{
		Administrador retorno = null;
		if (id > 0) {
			retorno = repositorio.procurarPorId(id);
		}else {
			throw new IdInvalidoException();
		}
		return retorno;
	}
	
	public ArrayList<Administrador> procurarPorNome(String nome) throws NomeVazioException, SQLException, AdministradorNaoCadastradoException, Exception{
		ArrayList<Administrador> retorno = new ArrayList<Administrador>();
		if (!nome.equals("")) {
			retorno = repositorio.procurarPorNome(nome);
		}else {
			throw new NomeVazioException();
		}
		return retorno;
	}
	
	public ArrayList<Administrador> listar() throws SQLException, AdministradorNaoCadastradoException, Exception{
		return repositorio.listar();
	}
	
	public void atualizar(Administrador administrador) throws CpfInvalidoException, SQLException, AdministradorNaoCadastradoException, Exception{
		if (administrador != null) {
			if (Validacao.validaCPF(administrador.getCpf())) {
				administrador.setCpf(administrador.getCpf().replace('.',' ').replace('-',' ').replaceAll(" ", ""));
				administrador.setDataDeNascimento(administrador.getDataDeNascimento().substring(6, 10) + "-" 
						 + administrador.getDataDeNascimento().substring(3, 5) + "-" 
						 + administrador.getDataDeNascimento().substring(0, 2));
				repositorio.atualizar(administrador);
			} else {
				throw new CpfInvalidoException();
			}
		}
	}
	
	public void deletar(long id) throws CpfInvalidoException, SQLException, AdministradorNaoCadastradoException, Exception{
		if (id > 0) {
			repositorio.deletar(id);
		}else {
			throw new AdministradorNaoCadastradoException();
		}
	}
	
	public boolean login(String usuario, String senha) throws SQLException, Exception{
			return repositorio.login(usuario, senha);
	}
	
}

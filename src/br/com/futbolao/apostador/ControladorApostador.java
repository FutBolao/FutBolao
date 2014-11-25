package br.com.futbolao.apostador;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.exception.ApostadorJaCadastradoException;
import br.com.futbolao.exception.ApostadorNaoCadastradoException;
import br.com.futbolao.exception.CpfInvalidoException;
import br.com.futbolao.exception.NomeVazioException;
import br.com.futbolao.validacao.Validacao;

public class ControladorApostador {

	private IRepositorioApostador repositorio;
	public ControladorApostador() throws Exception{
		this.repositorio = new RepositorioApostador();
	}
	
	public void cadastrar(Apostador apostador) throws SQLException, ApostadorJaCadastradoException, NomeVazioException, CpfInvalidoException, Exception{
		if (apostador != null) {
			if (Validacao.validaCPF(apostador.getCpf())) {
				if (!apostador.getNome().equals("")) {
					repositorio.cadastrar(apostador);
				} else {
					throw new NomeVazioException();
				}
			} else {
				throw new CpfInvalidoException();
			}
		}
	}
	
	public Apostador procurarPorCpf(String cpf) throws CpfInvalidoException, SQLException, ApostadorNaoCadastradoException, Exception{
		Apostador retorno = null;
		if (Validacao.validaCPF(cpf)) {
			retorno = repositorio.procurarPorCpf(cpf);
		}else {
			throw new CpfInvalidoException();
		}
		return retorno;
	}
	
	public ArrayList<Apostador> procurarPorNome(String nome) throws NomeVazioException, SQLException, ApostadorNaoCadastradoException, Exception{
		ArrayList<Apostador> retorno = new ArrayList<Apostador>();
		if (!nome.equals("")) {
			retorno = repositorio.procurarPorNome(nome);
		}else {
			throw new NomeVazioException();
		}
		return retorno;
	}
	
	public ArrayList<Apostador> listar() throws NomeVazioException, SQLException, ApostadorNaoCadastradoException, Exception{
		return repositorio.listar();
	}
	
	public void atualizar(Apostador apostador) throws CpfInvalidoException, SQLException, ApostadorNaoCadastradoException, Exception{
		if (apostador != null) {
			if (Validacao.validaCPF(apostador.getCpf())) {
				repositorio.atualizar(apostador);
			} else {
				throw new CpfInvalidoException();
			}
		}
	}
	
	public void deletar(long id) throws CpfInvalidoException, SQLException, ApostadorNaoCadastradoException, Exception{
		if (id > 0) {
			repositorio.deletar(id);
		}else {
			throw new ApostadorNaoCadastradoException();
		}
	}
	
}

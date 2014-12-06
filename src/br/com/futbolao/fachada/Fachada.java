package br.com.futbolao.fachada;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.administrador.Administrador;
import br.com.futbolao.administrador.ControladorAdministrador;
import br.com.futbolao.apostador.Apostador;
import br.com.futbolao.apostador.ControladorApostador;
import br.com.futbolao.clube.Clube;
import br.com.futbolao.clube.ControladorClube;
import br.com.futbolao.competicao.Competicao;
import br.com.futbolao.competicao.ControladorCompeticao;
import br.com.futbolao.exception.AdministradorJaCadastradoException;
import br.com.futbolao.exception.AdministradorNaoCadastradoException;
import br.com.futbolao.exception.ApostadorJaCadastradoException;
import br.com.futbolao.exception.ApostadorNaoCadastradoException;
import br.com.futbolao.exception.ClubeJaCadastradoException;
import br.com.futbolao.exception.ClubeNaoCadastradoException;
import br.com.futbolao.exception.CompeticaoJaCadastradaException;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;
import br.com.futbolao.exception.CpfInvalidoException;
import br.com.futbolao.exception.NomeVazioException;
import br.com.futbolao.exception.PermissaoJaCadastradaException;
import br.com.futbolao.exception.PermissaoNaoCadastradaException;
import br.com.futbolao.permissao.ControladorPermissao;
import br.com.futbolao.permissao.Permissao;

public class Fachada {
	
	private static Fachada instance = null;
	private ControladorApostador controladorApostador;
	private ControladorAdministrador controladorAdministrador;
	private ControladorClube controladorClube;
	private ControladorCompeticao controladorCompeticao;
	private ControladorPermissao controladorPermissao;
	
	public Fachada() throws Exception {
		this.controladorApostador = new ControladorApostador();
		this.controladorAdministrador = new ControladorAdministrador();
		this.controladorClube = new ControladorClube();
		this.controladorCompeticao = new ControladorCompeticao();
		this.controladorPermissao = new ControladorPermissao();
	}
	
	public static Fachada getInstance() throws Exception {
		if (Fachada.instance == null) {
			Fachada.instance = new Fachada();
		}
		return Fachada.instance;
	}
	
	//Métodos do Apostador
	public void cadastrarApostador(Apostador apostador) throws SQLException, ApostadorJaCadastradoException, NomeVazioException, CpfInvalidoException, Exception{
		apostador.setCpf(apostador.getCpf().replace('.',' ').replace('-',' ').replaceAll(" ", ""));
		apostador.setTelefone(apostador.getTelefone().replace('(', ' ').replace(')', ' ').replace('-', ' '));
		apostador.setDataDeNascimento(apostador.getDataDeNascimento().substring(6, 10) + "-" 
				 + apostador.getDataDeNascimento().substring(3, 5) + "-" 
				 + apostador.getDataDeNascimento().substring(0, 2));
		this.controladorApostador.cadastrar(apostador);
	}
	
	public Apostador listarApostadorPorCpf(String cpf) throws CpfInvalidoException, SQLException, ApostadorNaoCadastradoException, Exception{
		return this.controladorApostador.procurarPorCpf(cpf);
		
	}
	
	public ArrayList<Apostador> procurarApostadorPorNome(String nome) throws NomeVazioException, SQLException, ApostadorNaoCadastradoException, Exception{
		return this.controladorApostador.procurarPorNome(nome);
		
	}
	
	public ArrayList<Apostador> listarApostador() throws NomeVazioException, SQLException, ApostadorNaoCadastradoException, Exception{
		return this.controladorApostador.listar();
		
	}
	
	public void atualizaApostador(Apostador apostador) throws NomeVazioException, CpfInvalidoException, SQLException, ApostadorNaoCadastradoException, Exception{
		apostador.setCpf(apostador.getCpf().replace('.',' ').replace('-',' ').replaceAll(" ", ""));
		apostador.setTelefone(apostador.getTelefone().replace('(', ' ').replace(')', ' ').replace('-', ' '));
		apostador.setDataDeNascimento(apostador.getDataDeNascimento().substring(6, 10) + "-" 
				 + apostador.getDataDeNascimento().substring(3, 5) + "-" 
				 + apostador.getDataDeNascimento().substring(0, 2));
		this.controladorApostador.atualizar(apostador);
	}
	
	public void deletarApostador(long id) throws CpfInvalidoException, SQLException, ApostadorNaoCadastradoException, Exception{
		this.controladorApostador.deletar(id);
	}
	
	//Métodos do Administrador
	public void cadastrarAdministrador(Administrador administrador) throws SQLException, AdministradorJaCadastradoException, NomeVazioException, CpfInvalidoException, Exception{
		administrador.setCpf(administrador.getCpf().replace('.',' ').replace('-',' ').replaceAll(" ", ""));
		administrador.setTelefone(administrador.getTelefone().replace('(', ' ').replace(')', ' ').replace('-', ' '));
		administrador.setDataDeNascimento(administrador.getDataDeNascimento().substring(6, 10) + "-" 
				 + administrador.getDataDeNascimento().substring(3, 5) + "-" 
				 + administrador.getDataDeNascimento().substring(0, 2));
		this.controladorAdministrador.cadastrar(administrador);
	}
		
	public Administrador procurarAdministradorPorCpf(String cpf) throws CpfInvalidoException, SQLException, AdministradorNaoCadastradoException, Exception{
		return this.controladorAdministrador.procurarPorCpf(cpf);
		
	}
	
	public ArrayList<Administrador> procurarAdministradorPorNome(String nome) throws NomeVazioException, SQLException, AdministradorNaoCadastradoException, Exception{
		return this.controladorAdministrador.procurarPorNome(nome);
		
	}
	
	public ArrayList<Administrador> listarAdministrador() throws NomeVazioException, SQLException, AdministradorNaoCadastradoException, Exception{
		return this.controladorAdministrador.listar();
		
	}
	
	public void atualizaAdministrador(Administrador administrador) throws NomeVazioException, CpfInvalidoException, SQLException, AdministradorNaoCadastradoException, Exception{
		administrador.setCpf(administrador.getCpf().replace('.',' ').replace('-',' ').replaceAll(" ", ""));
		administrador.setTelefone(administrador.getTelefone().replace('(', ' ').replace(')', ' ').replace('-', ' '));
		administrador.setDataDeNascimento(administrador.getDataDeNascimento().substring(6, 10) + "-" 
				 + administrador.getDataDeNascimento().substring(3, 5) + "-" 
				 + administrador.getDataDeNascimento().substring(0, 2));
		this.controladorAdministrador.atualizar(administrador);
	}
	
	public void deletarAdministrador(int id) throws CpfInvalidoException, SQLException, AdministradorNaoCadastradoException, Exception{
		this.controladorAdministrador.deletar(id);
	}
	
	//Métodos do Clube
	public void cadastrarClube(Clube clube) throws SQLException, ClubeJaCadastradoException, NomeVazioException, Exception{
		this.controladorClube.cadastrar(clube);
	}
	
	public ArrayList<Clube> procurarClubePorNome(String nome) throws SQLException, ClubeNaoCadastradoException, Exception{
		return this.controladorClube.procurarPorNome(nome);		
	}

	public Clube procurarClubePorId(int id) throws SQLException, ClubeNaoCadastradoException, Exception{
		return this.controladorClube.procurarPorId(id);		
	}
	
	public ArrayList<Clube> listarClube() throws SQLException, ClubeNaoCadastradoException, Exception{
		return this.controladorClube.listar();		
	}
	
	public void atualizaClube(Clube clube) throws NomeVazioException, SQLException, ClubeNaoCadastradoException, Exception{
		this.controladorClube.atualizar(clube);
	}
	
	public void deletarClube(int id) throws SQLException, ClubeNaoCadastradoException, Exception{
		this.controladorClube.deletar(id);
	}
	
	//Métodos da Competição
	public void cadastrarCompeticao(Competicao competicao) throws SQLException, CompeticaoJaCadastradaException, NomeVazioException, Exception{
		this.controladorCompeticao.cadastrar(competicao);
	}
	
	public ArrayList<Competicao> listarCompeticaoPorNome(String nome) throws SQLException, CompeticaoNaoCadastradaException, Exception{
		return this.controladorCompeticao.listar(nome);		
	}
	
	public void atualizaCompeticao(Competicao competicao) throws NomeVazioException, SQLException, CompeticaoNaoCadastradaException, Exception{
		this.controladorCompeticao.atualizar(competicao);
	}
	
	public void deletarCompeticao(int id) throws SQLException, CompeticaoNaoCadastradaException, Exception{
		this.controladorCompeticao.deletar(id);
	}
	
	//Métodos da Permissao
	public void cadastrarPermissao(Permissao permissao) throws SQLException, PermissaoJaCadastradaException, Exception{
		this.controladorPermissao.cadastrar(permissao);
	}
	
	public Permissao listar(int administrador, int modulo) throws SQLException, PermissaoNaoCadastradaException, Exception{
		return this.controladorPermissao.listar(administrador, modulo);		
	}
	
	public void atualizaPermissao(Permissao permissao) throws SQLException, PermissaoNaoCadastradaException, Exception{
		this.controladorPermissao.atualizar(permissao);
	}
	
	public void deletarPermissao(int administrador) throws SQLException, PermissaoNaoCadastradaException, Exception{
		this.controladorPermissao.deletar(administrador);
	}
}

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
import br.com.futbolao.exception.GrupoJaCadastradoException;
import br.com.futbolao.exception.GrupoNaoCadastradoException;
import br.com.futbolao.exception.IdInvalidoException;
import br.com.futbolao.exception.NomeVazioException;
import br.com.futbolao.exception.PermissaoJaCadastradaException;
import br.com.futbolao.exception.PermissaoNaoCadastradaException;
import br.com.futbolao.exception.RodadaJaCadastradaException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;
import br.com.futbolao.grupo.ControladorGrupo;
import br.com.futbolao.grupo.Grupo;
import br.com.futbolao.permissao.ControladorPermissao;
import br.com.futbolao.permissao.Permissao;
import br.com.futbolao.rodada.ControladorRodada;
import br.com.futbolao.rodada.Rodada;

public class Fachada {
	
	private static Fachada instance = null;
	private ControladorApostador controladorApostador;
	private ControladorAdministrador controladorAdministrador;
	private ControladorClube controladorClube;
	private ControladorCompeticao controladorCompeticao;
	private ControladorPermissao controladorPermissao;
	private ControladorRodada controladorRodada;
	private ControladorGrupo controladorGrupo;
	
	public Fachada() throws Exception {
		this.controladorApostador = new ControladorApostador();
		this.controladorAdministrador = new ControladorAdministrador();
		this.controladorClube = new ControladorClube();
		this.controladorCompeticao = new ControladorCompeticao();
		this.controladorPermissao = new ControladorPermissao();
		this.controladorRodada = new ControladorRodada();
		this.controladorGrupo = new ControladorGrupo();
	}
	
	public static Fachada getInstance() throws Exception {
		if (Fachada.instance == null) {
			Fachada.instance = new Fachada();
		}
		return Fachada.instance;
	}
	
	//Métodos do Apostador
	public void cadastrarApostador(Apostador apostador) throws SQLException, ApostadorJaCadastradoException, NomeVazioException, CpfInvalidoException, Exception{
		this.controladorApostador.cadastrar(apostador);
	}
	
	public Apostador procurarApostadorPorId(long id) throws CpfInvalidoException, SQLException, ApostadorNaoCadastradoException, Exception{
		return this.controladorApostador.procurarPorId(id);
		
	}
	
	public ArrayList<Apostador> procurarApostadorPorNome(String nome) throws NomeVazioException, SQLException, ApostadorNaoCadastradoException, Exception{
		return this.controladorApostador.procurarPorNome(nome);
		
	}
	
	public ArrayList<Apostador> listarApostador() throws NomeVazioException, SQLException, ApostadorNaoCadastradoException, Exception{
		return this.controladorApostador.listar();
		
	}
	
	public void atualizaApostador(Apostador apostador) throws NomeVazioException, CpfInvalidoException, SQLException, ApostadorNaoCadastradoException, Exception{
		this.controladorApostador.atualizar(apostador);
	}
	
	public void deletarApostador(long id) throws CpfInvalidoException, SQLException, ApostadorNaoCadastradoException, Exception{
		this.controladorApostador.deletar(id);
	}
	
	//Métodos do Administrador
	public void cadastrarAdministrador(Administrador administrador) throws SQLException, AdministradorJaCadastradoException, NomeVazioException, CpfInvalidoException, Exception{
		this.controladorAdministrador.cadastrar(administrador);
	}
		
	public Administrador procurarAdministradorPorId(long id) throws IdInvalidoException, SQLException, AdministradorNaoCadastradoException, Exception{
		return this.controladorAdministrador.procurarPorId(id);
	}
	
	public ArrayList<Administrador> procurarAdministradorPorNome(String nome) throws NomeVazioException, SQLException, AdministradorNaoCadastradoException, Exception{
		return this.controladorAdministrador.procurarPorNome(nome);
		
	}
	
	public ArrayList<Administrador> listarAdministrador() throws NomeVazioException, SQLException, AdministradorNaoCadastradoException, Exception{
		return this.controladorAdministrador.listar();
		
	}
	
	public void atualizaAdministrador(Administrador administrador) throws NomeVazioException, CpfInvalidoException, SQLException, AdministradorNaoCadastradoException, Exception{
		this.controladorAdministrador.atualizar(administrador);
	}
	
	public void deletarAdministrador(long id) throws CpfInvalidoException, SQLException, AdministradorNaoCadastradoException, Exception{
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
	
	public ArrayList<Clube> listarClube(char ativo) throws SQLException, ClubeNaoCadastradoException, Exception{
		return this.controladorClube.listar(ativo);		
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
	
	public ArrayList<Competicao> procurarCompeticaoPorNome(String nome) throws SQLException, CompeticaoNaoCadastradaException, Exception{
		return this.controladorCompeticao.procurarPorNome(nome);		
	}
	
	public Competicao procurarCompeticaoPorId(int id) throws SQLException, CompeticaoNaoCadastradaException, Exception{
		return this.controladorCompeticao.procurarPorId(id);
	}
	
	public ArrayList<Competicao> listarCompeticao() throws SQLException, CompeticaoNaoCadastradaException, Exception{
		return this.controladorCompeticao.listar();		
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
	
	
	//Métodos da Rodada
	public void cadastrarRodada(Rodada rodada) throws SQLException, RodadaJaCadastradaException, Exception{
		this.controladorRodada.cadastrar(rodada);
	}
	
	public ArrayList<Rodada> procurarRodada(int idCompeticao, int numeroDaRodada) throws SQLException, RodadaNaoCadastradaException, Exception{
		return this.controladorRodada.procurar(idCompeticao, numeroDaRodada);
	}
	
	public ArrayList<Rodada> listarRodada() throws SQLException, RodadaNaoCadastradaException, Exception{
		return this.controladorRodada.listar();
	}
	
	public void atualizarRodada(Rodada rodada) throws SQLException, RodadaNaoCadastradaException, Exception{
		this.controladorRodada.atualizar(rodada);
	}
	
	public void deletarRodada(int id) throws SQLException, RodadaNaoCadastradaException, Exception{
		this.controladorRodada.deletar(id);
	}
	
	
	//Métodos do Grupo
	public void cadastrarGrupo(Grupo grupo) throws SQLException, GrupoJaCadastradoException, Exception{
		this.controladorGrupo.cadastrar(grupo);
	}
	
	public ArrayList<Grupo> procurarPorNome(String nome) throws SQLException, NomeVazioException, Exception{
		return this.controladorGrupo.procurarPorNome(nome);
	}
	
	public Grupo procurarPorId(long id) throws SQLException, GrupoNaoCadastradoException, Exception{
		return this.controladorGrupo.procurarPorId(id);
	}
	
	public void atualizar(Grupo grupo) throws SQLException, GrupoNaoCadastradoException, Exception{
		this.controladorGrupo.atualizar(grupo);
	}
	
	public void deletar(long id) throws SQLException, GrupoNaoCadastradoException, Exception{
		this.controladorGrupo.deletar(id);
	}
	
}

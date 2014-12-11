package br.com.futbolao.competicao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.futbolao.conexao.Conexao;
import br.com.futbolao.conexao.DataBase;
import br.com.futbolao.exception.CompeticaoJaCadastradaException;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;

public class RepositorioCompeticao implements IRepositorioCompeticao{

	private static final String NOME_TABELA = "competicao";
	private static final String NOME_VIEW_RODADA = "vw_rodada";
	private static final String NOME_VIEW_GRUPO = "vw_grupo";
	private Connection connection;
	private int dataBase = 0;
	
	//construtor padrão, onde seleciona o banco mysql caso não seja executado o construtor com argumento
	public RepositorioCompeticao() throws Exception{
		this.connection = Conexao.getConexao(DataBase.MYSQL);
		this.dataBase = DataBase.MYSQL;
	}
	
	//construtor com argumento, que recebe como argumento o tipo de banco a ser executado.
	public RepositorioCompeticao(int dataBase) throws Exception{
		this.connection = Conexao.getConexao(dataBase);
		this.dataBase = dataBase;
	}	

	public void cadastrar(Competicao competicao) throws SQLException, CompeticaoJaCadastradaException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		if (existe(competicao.getNome()) == false){
			sql = "INSERT INTO " + NOME_TABELA + " (nome, quantidade_de_rodadas, ativo) VALUES (?,?,?);";
			if (this.dataBase == DataBase.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			ps.setString(1, competicao.getNome());
			ps.setInt(2, competicao.getQtdRodadas());
			ps.setString(3, String.valueOf(competicao.getAtivo()));
			ps.execute();
			rs = ps.getGeneratedKeys();
			int id = 0;
			// Pegando o identificador gerado a partir do último insert
			while (rs.next()) {
				id = rs.getInt(1);
			}
			competicao.setId(id);
		} else {
			throw new CompeticaoJaCadastradaException();
		}
		ps.close();
		rs.close();
	}
	
	// método para listar competição que tem rodada cadastrada.
	public ArrayList<Competicao> listarCompeticaoComRodada() throws SQLException, CompeticaoNaoCadastradaException, Exception {
		ArrayList<Competicao> competicoes = new ArrayList<Competicao>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT DISTINCT id_competicao, nome_competicao FROM " + NOME_VIEW_RODADA + " ";
		sql += "WHERE id IS NOT NULL AND trava='N'";
		sql += " ORDER BY id_competicao DESC;";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		//se a consulta tiver algum resultado entro no loop e o executo adicionando o
		// resultado de cada linha ao array de clube, até que haja linhas.
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) {
			Competicao competicao = new Competicao(rs.getInt("id_competicao"), rs.getString("nome_competicao"));
			competicoes.add(competicao);
			}
		}else{
			throw new CompeticaoNaoCadastradaException();
		}
		ps.close();
		rs.close();
		return competicoes;
	}
	
	// método para listar competição que tem grupo cadastrado.
	public ArrayList<Competicao> listarCompeticaoComGrupo(char ativo) throws SQLException, CompeticaoNaoCadastradaException, Exception {
		ArrayList<Competicao> competicoes = new ArrayList<Competicao>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		String complemento = "";
		if (ativo == 'S') {
			complemento = ">=";
		} else {
			complemento = "<";
		}
		sql = "SELECT DISTINCT id_competicao, nome_competicao FROM " + NOME_VIEW_GRUPO + " ";
		sql += "WHERE id IS NOT NULL AND data_encerramento_aposta " + complemento + " CURDATE()";
		sql += " ORDER BY id_competicao DESC;";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		//se a consulta tiver algum resultado entro no loop e o executo adicionando o
		// resultado de cada linha ao array de clube, até que haja linhas.
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) {
			Competicao competicao = new Competicao(rs.getInt("id_competicao"), rs.getString("nome_competicao"));
			competicoes.add(competicao);
			}
		}else{
			throw new CompeticaoNaoCadastradaException();
		}
		ps.close();
		rs.close();
		return competicoes;
	}

	// método para listar competição.
	public ArrayList<Competicao> listar(String complemento) throws SQLException, CompeticaoNaoCadastradaException, Exception {
		ArrayList<Competicao> competicoes = new ArrayList<Competicao>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + NOME_TABELA + " ";
		sql += "WHERE id IS NOT NULL";
		sql += complemento;
		sql += " ORDER BY id DESC;";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		//se a consulta tiver algum resultado entro no loop e o executo adicionando o
		// resultado de cada linha ao array de clube, até que haja linhas.
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) {
			Competicao competicao = new Competicao(rs.getInt("id"), rs.getString("nome"), rs.getInt("quantidade_de_rodadas"), rs.getString("ativo").charAt(0));
			competicoes.add(competicao);
			}
		}else{
			throw new CompeticaoNaoCadastradaException();
		}
		ps.close();
		rs.close();
		return competicoes;
	}
	
	public ArrayList<Competicao> procurarPorNome(String nome) throws SQLException, CompeticaoNaoCadastradaException, Exception{
		return listar(" and nome like '%" + nome + "%'");
	}
	
	public Competicao procurarPorId(int id) throws SQLException, CompeticaoNaoCadastradaException, Exception{
		return listar(" and id =" + id).get(0);
	}
	
	public ArrayList<Competicao> listar(char ativo) throws SQLException, CompeticaoNaoCadastradaException, Exception {
		if(ativo == ' '){
			return listar("");	
		}else{
			return listar(" and ativo='" + ativo + "'");
		}
	}

	// método para atualizar competição.
	public void atualizar(Competicao competicao) throws SQLException, CompeticaoNaoCadastradaException, Exception {
		if(competicao != null){
			PreparedStatement ps = null;
			String sql = "";
			// instrução de update da competição.
			sql = "UPDATE " + NOME_TABELA + " SET nome=?, quantidade_de_rodadas=?, ativo=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, competicao.getNome());
			ps.setInt(2, competicao.getQtdRodadas());
			ps.setString(3, String.valueOf(competicao.getAtivo()));
			ps.setInt(4, competicao.getId());
			Integer resultado = ps.executeUpdate();
			// se a atualizaçãp for efetuada com êxito o atributo resultado terá um valor diferente de 0, caso contrario levanta uma exception
			if (resultado == 0) throw new CompeticaoNaoCadastradaException();
			// fecha a conexão
			ps.close();
		}
	}

	// método para deletar competição.
	public void deletar(int id) throws SQLException, CompeticaoNaoCadastradaException, Exception {
		PreparedStatement ps = null;
		String sql = "DELETE FROM " + NOME_TABELA + " WHERE id=?";
		ps = connection.prepareStatement(sql);
		ps.setLong(1, id);
		Integer resultado = ps.executeUpdate();
		ps.close();
		if(resultado == 0) throw new CompeticaoNaoCadastradaException();
	}

	// método para verificar se existe competição pelo nome.
	public boolean existe(String nome) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE nome=?";
		boolean resposta = false;		
		ps = connection.prepareStatement(sql);
		ps.setString(1, nome);
		rs = ps.executeQuery();
		if(rs.next()){
			resposta = true;
		}
		ps.close();
		rs.close();
		return resposta;
	}
}

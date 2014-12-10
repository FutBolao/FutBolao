package br.com.futbolao.grupo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.futbolao.conexao.Conexao;
import br.com.futbolao.conexao.DataBase;
import br.com.futbolao.exception.ExisteApostaNesseGrupoException;
import br.com.futbolao.exception.GrupoJaCadastradoException;
import br.com.futbolao.exception.GrupoNaoCadastradoException;

public class RepositorioGrupo implements IRepositorioGrupo{
	
	private static final String NOME_TABELA = "grupo";
	private static final String NOME_VIEW = "vw_grupo";
	private static final String NOME_TABELA_APOSTA = "aposta";
	private Connection connection;
	private int dataBase = 0;
	
	//construtor padrão, onde seleciona o banco mysql caso não seja executado o construtor com argumento
	public RepositorioGrupo() throws Exception{
		this.connection = Conexao.getConexao(DataBase.MYSQL);
		this.dataBase = DataBase.MYSQL;
	}
	
	//construtor com argumento, que recebe como argumento o tipo de banco a ser executado.
	public RepositorioGrupo(int dataBase) throws Exception{
		this.connection = Conexao.getConexao(dataBase);
		this.dataBase = dataBase;
	}

	public void cadastrar(Grupo grupo) throws SQLException,	GrupoJaCadastradoException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		if (existe(grupo) == false){
			sql = "INSERT INTO " + NOME_TABELA + " (valor_aposta, limite_apostas, limite_apostas_por_apostador, percentual_lucro_administrador,"
					+ "data_encerramento_aposta, id_competicao, id_rodada, pontuacao_por_resultado, pontuacao_por_placar) VALUES (?,?,?,?,?,?,?,?,?);";
			if (this.dataBase == DataBase.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			ps.setDouble(1, grupo.getValorAposta());
			ps.setLong(2, grupo.getLimiteApostas());
			ps.setInt(3, grupo.getLimiteApostasPorApostador());
			ps.setInt(4, grupo.getPercentualLucroAdministrador());
			ps.setString(5, grupo.getDataEncerramentoAposta());
			ps.setInt(6, grupo.getIdCompeticao());
			ps.setInt(7, grupo.getIdRodada());
			ps.setInt(8, grupo.getPontuacaoPorResultado());
			ps.setInt(9, grupo.getPontuacaoPorPlacar());
			ps.execute();
			rs = ps.getGeneratedKeys();
			int id = 0;
			// Pegando o identificador gerado a partir do último insert
			while (rs.next()) {
				id = rs.getInt(1);
			}
			grupo.setId(id);
		} else {
			throw new GrupoJaCadastradoException();
		}
		ps.close();
		rs.close();
	}
	
	// método para listar clubes.
	private ArrayList<Grupo> listar(String complemento) throws SQLException, GrupoNaoCadastradoException, Exception {
		ArrayList<Grupo> grupos = new ArrayList<Grupo>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + NOME_VIEW + " ";
		sql += "WHERE id IS NOT NULL";
		sql += complemento;
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		//se a consulta tiver algum resultado entro no loop e o executo adicionando o
		// resultado de cada linha ao array de grupo, até que haja linhas.
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) {
				Grupo grupo = new Grupo(rs.getLong("id"),
						rs.getDouble("valor_aposta"),
						rs.getLong("limite_apostas"),
						rs.getInt("limite_apostas_por_apostador"),
						rs.getInt("percentual_lucro_administrador"),
						rs.getString("data_encerramento_aposta").substring(8, 10) + "/" + 
						rs.getString("data_encerramento_aposta").substring(5, 7) + "/" + 
						rs.getString("data_encerramento_aposta").substring(0, 4),
						rs.getInt("id_competicao"), rs.getString("nome_competicao"), rs.getInt("id_rodada"),
						rs.getInt("pontuacao_por_resultado"),
						rs.getInt("pontuacao_por_placar"));
				grupos.add(grupo);
			}
		}else{
			throw new GrupoNaoCadastradoException();
		}
		ps.close();
		rs.close();
		return grupos;
	}
	
	// método para listar grupo.
	public ArrayList<Grupo> procurarPorCompeticao(int idCompeticao, int numeroRodada) throws SQLException, GrupoNaoCadastradoException, Exception{
		return listar(" AND id_competicao =" + idCompeticao + " AND id_rodada=" + numeroRodada);
	}
	
	public Grupo procurarPorId(long id) throws SQLException, GrupoNaoCadastradoException, Exception{
		return listar(" AND id =" + id).get(0);
	}
	
	// método para listar grupo.
	public ArrayList<Grupo> listar() throws SQLException, GrupoNaoCadastradoException, Exception{
		return listar("");
	}

	// método para atualizar grupo.
	public void atualizar(Grupo grupo) throws SQLException,	GrupoNaoCadastradoException, Exception {
		if(grupo != null){
			PreparedStatement ps = null;
			String sql = "";
			// instrução de update do grupo.
			sql = "UPDATE " + NOME_TABELA + " SET valor_aposta=?, limite_apostas=?, limite_apostas_por_apostador=?,"
					+ "percentual_lucro_administrador=?, data_encerramento_aposta=?, id_competicao=?, id_rodada=?, pontuacao_por_resultado=?,"
					+ "pontuacao_por_placar=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setDouble(1, grupo.getValorAposta());
			ps.setLong(2, grupo.getLimiteApostas());
			ps.setInt(3, grupo.getLimiteApostasPorApostador());
			ps.setInt(4, grupo.getPercentualLucroAdministrador());
			ps.setString(5, grupo.getDataEncerramentoAposta());
			ps.setInt(6, grupo.getIdCompeticao());
			ps.setInt(7, grupo.getIdRodada());
			ps.setInt(8, grupo.getPontuacaoPorResultado());
			ps.setInt(9, grupo.getPontuacaoPorPlacar());
			ps.setLong(10, grupo.getId());
			Integer resultado = ps.executeUpdate();
			// se a atualizaçãp for efetuada com êxito o atributo resultado terá um valor diferente de 0, caso contrario levanta uma exception
			if (resultado == 0) throw new GrupoNaoCadastradoException();
			// fecha a conexão
			ps.close();
		}
	}
	
	// método para deletar grupo.
	public void deletar(long id) throws SQLException, ExisteApostaNesseGrupoException, GrupoNaoCadastradoException, Exception {
		if (existeAposta(id) == false) {
			PreparedStatement ps = null;
			String sql = "DELETE FROM " + NOME_TABELA + " WHERE id=?";
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			Integer resultado = ps.executeUpdate();
			ps.close();
			if(resultado == 0) throw new GrupoNaoCadastradoException();
		} else {
			throw new ExisteApostaNesseGrupoException();
		}
	}
	
	// método para verificar se existe grupo.
	public boolean existe(Grupo grupo) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE valor_aposta=? and limite_apostas=? and limite_apostas_por_apostador=? and "
				+ "id_competicao=? and id_rodada=? and pontuacao_por_resultado=? and pontuacao_por_placar=?";
		boolean resposta = false;		
		ps = connection.prepareStatement(sql);
		ps.setDouble(1, grupo.getValorAposta());
		ps.setLong(2, grupo.getLimiteApostas());
		ps.setInt(3, grupo.getLimiteApostasPorApostador());
		ps.setInt(4, grupo.getIdCompeticao());
		ps.setInt(5, grupo.getIdRodada());
		ps.setInt(6, grupo.getPontuacaoPorResultado());
		ps.setInt(7, grupo.getPontuacaoPorPlacar());
		rs = ps.executeQuery();
		if(rs.next()){
			resposta = true;
		}
		ps.close();
		rs.close();
		return resposta;
	}
	
	// método para verificar se existe apostas já realizadas no grupo.
	public boolean existeAposta(long grupo) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA_APOSTA + " WHERE id_grupo=" + grupo;
		boolean resposta = false;		
		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next()){
			resposta = true;
		}
		ps.close();
		rs.close();
		return resposta;
	}
	
}

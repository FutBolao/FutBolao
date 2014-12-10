package br.com.futbolao.aposta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.conexao.Conexao;
import br.com.futbolao.conexao.DataBase;
import br.com.futbolao.exception.ApostaNaoCadastradaException;

public class RepositorioAposta implements IRepositorioAposta{

	private static final String NOME_TABELA = "aposta";
	private static final String NOME_VIEW = "vw_aposta";
	private Connection connection;
	@SuppressWarnings("unused")
	private int dataBase = 0;
	
	//construtor padrão, onde seleciona o banco mysql caso não seja executado o construtor com argumento
	public RepositorioAposta() throws Exception{
		this.connection = Conexao.getConexao(DataBase.MYSQL);
		this.dataBase = DataBase.MYSQL;
	}

	//construtor com argumento, que recebe como argumento o tipo de banco a ser executado.
	public RepositorioAposta(int dataBase) throws Exception{
		this.connection = Conexao.getConexao(dataBase);
		this.dataBase = dataBase;
	}
	
	public void cadastrar(ArrayList<Aposta> aposta) throws SQLException, ApostaNaoCadastradaException, Exception {
		PreparedStatement ps = null;
		long id = maxId();
		boolean cadastrou = true;
		String sql = "INSERT INTO " + NOME_TABELA + " (id, id_apostador, id_grupo, id_jogo, clube1, resultado_clube1, clube2, resultado_clube2)"
				   + " VALUES (?,?,?,?,?,?,?,?);";
		try {
			connection.setAutoCommit(false);
			for (Aposta jogo: aposta){
				ps = this.connection.prepareStatement(sql);
				ps.setLong(1, id);
				ps.setLong(2, jogo.getIdApostador());
				ps.setLong(3, jogo.getIdGrupo());
				ps.setInt(4, jogo.getIdJogo());
				ps.setInt(5, jogo.getClube1());
				ps.setInt(6, jogo.getResultadoClube1());
				ps.setInt(7, jogo.getClube2());
				ps.setInt(8, jogo.getResultadoClube2());
				ps.execute();
			}
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			cadastrou = false;
		} catch (Exception e) {
			connection.rollback();
			cadastrou = false;
		} finally {
			connection.setAutoCommit(true);
		}
		if (cadastrou ==  false) throw new  ApostaNaoCadastradaException();
		ps.close();
	}
	
	private long maxId() throws SQLException, Exception{
		long id = 1;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT MAX(id)+1 AS id FROM " + NOME_TABELA + " ";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getObject("id") != null) {
					id = rs.getLong("id");
				}
			}
		ps.close();
		rs.close();
		return id;
	}
	
	private ArrayList<Aposta> listar(String complemento) throws SQLException, ApostaNaoCadastradaException, Exception {
		ArrayList<Aposta> apostas = new ArrayList<Aposta>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + NOME_VIEW + " ";
		sql += "WHERE id IS NOT NULL";
		sql += complemento;
		sql += " ORDER BY id DESC";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		//se a consulta tiver algum resultado entro no loop e o executo adicionando o
		// resultado de cada linha ao array de clube, até que haja linhas.
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) {
				Aposta aposta = new Aposta(rs.getLong("id"), rs.getLong("id_apostador"), rs.getString("nome_apostador"), rs.getLong("id_grupo"), 
						rs.getString("data_aposta"), rs.getInt("id_jogo"), rs.getInt("clube1"), rs.getString("nome_clube1"), 
						rs.getInt("resultado_clube1"), rs.getInt("clube2"), rs.getString("nome_clube2"), rs.getInt("resultado_clube2"));
				apostas.add(aposta);
			}
		}else{
			throw new ApostaNaoCadastradaException();
		}
		ps.close();
		rs.close();
		return apostas;
	}

	public ArrayList<Aposta> procurarPorApostador(long idApostador, char ativa) throws SQLException, ApostaNaoCadastradaException, Exception {
		return listar(" AND id_apostador=" + idApostador + " AND ativa='" + ativa + "'");
	}

	public ArrayList<Aposta> procurarPorGrupo(long idGrupo, char ativa) throws SQLException, ApostaNaoCadastradaException, Exception {
		return listar(" AND id_grupo=" + idGrupo + " AND ativa='" + ativa + "'");
	}

	public void deletar(long id) throws SQLException, ApostaNaoCadastradaException, Exception {
		PreparedStatement ps = null;
		String sql = "UPDATE " + NOME_TABELA + " SET ativa='S' WHERE id=?;";
		ps = this.connection.prepareStatement(sql);
		Integer resultado = ps.executeUpdate();
		// se a atualização for efetuada com êxito o atributo resultado terá um valor diferente de 0, caso contrario levanta uma exception
		if (resultado == 0) throw new ApostaNaoCadastradaException();
		// fecha a conexão
		ps.close();
	}

}

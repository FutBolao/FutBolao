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
		sql += "WHERE id_aposta IS NOT NULL";
		sql += complemento;
		sql += " ORDER BY id_aposta DESC";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) {
				Aposta aposta = new Aposta(rs.getLong("id_aposta"), rs.getLong("id_apostador"), rs.getString("nome_apostador"), rs.getLong("id_grupo"), 
						rs.getDouble("valor"), 
						rs.getString("data_aposta").substring(8, 10) + "/" + rs.getString("data_aposta").substring(5, 7) + "/"
								   + rs.getString("data_aposta").substring(0, 4) + " " + rs.getString("data_aposta").substring(11, 16), 
								   rs.getString("ativa").charAt(0));
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
		String sql = "UPDATE " + NOME_TABELA + " SET ativa='N' WHERE id=" + id;
		ps = this.connection.prepareStatement(sql);
		Integer resultado = ps.executeUpdate();
		// se a atualização for efetuada com êxito o atributo resultado terá um valor diferente de 0, caso contrario levanta uma exception
		if (resultado == 0) throw new ApostaNaoCadastradaException();
		// fecha a conexão
		ps.close();
	}
	
	// método para verificar se existe apostas já realizadas no grupo.
	public long totalDeApostasPoGrupo(long id_apostador, long id_grupo) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(id) AS total_apostas FROM " + NOME_TABELA + " WHERE id_apostador=" + id_apostador + " AND id_grupo" + id_grupo;
		long resposta = 0;		
		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next()){
			resposta = rs.getLong("total_apostas");
		}
		ps.close();
		rs.close();
		return resposta;
	}

}

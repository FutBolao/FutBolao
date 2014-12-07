package br.com.futbolao.rodada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import br.com.futbolao.conexao.Conexao;
import br.com.futbolao.conexao.DataBase;
import br.com.futbolao.exception.RodadaJaCadastradaException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;

public class RepositorioRodada implements IRepositorioRodada {
	
	public static final String NOME_TABELA = "rodada";
	public static final String NOME_VIEW = "vw_rodada";
	private Connection connection;
	private int dataBase = 0;
	
	//construtor padrão, onde seleciona o banco mysql caso não seja executado o construtor com argumento
	public RepositorioRodada() throws Exception{
		this.connection = Conexao.getConexao(DataBase.MYSQL);
		this.dataBase = DataBase.MYSQL;
	}
	
	//construtor com argumento, que recebe como argumento o tipo de banco a ser executado.
	public RepositorioRodada(int dataBase) throws Exception{
		this.connection = Conexao.getConexao(dataBase);
		this.dataBase = dataBase;
	}

	@Override
	public void cadastrar(Rodada rodada) throws SQLException, RodadaJaCadastradaException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		if (existe(rodada.getIdCompeticao(), rodada.getNumeroRodada()) == false){
			sql = "INSERT INTO " + NOME_TABELA + " (id_competicao, numero_rodada, id_jogo, data_hora, clube1, clube2) VALUES (?,?,?,?,?,?);";
			if (this.dataBase == DataBase.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			ps.setInt(1, rodada.getIdCompeticao());
			ps.setInt(2, rodada.getNumeroRodada());
			ps.setInt(3, rodada.getIdJogo());
			ps.setString(4, rodada.getDataHora());
			ps.setInt(5, rodada.getClube1());
			ps.setInt(6, rodada.getClube2());
			ps.execute();
			rs = ps.getGeneratedKeys();
			long id = 0;
			// Pegando o identificador gerado a partir do último insert
			while (rs.next()) {
				id = rs.getLong(1);
			}
			rodada.setId(id);
		} else {
			throw new RodadaJaCadastradaException();
		}
		ps.close();
		rs.close();
	}

	// método para listar rodadas.
	private ArrayList<Rodada> listar(String complemento) throws SQLException, RodadaNaoCadastradaException, Exception {
		ArrayList<Rodada> rodadas = new ArrayList<Rodada>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + NOME_VIEW + " ";
		sql += "WHERE id IS NOT NULL";
		sql += complemento;
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		//se a consulta tiver algum resultado entro no loop e o executo adicionando o
		// resultado de cada linha ao array de rodada, até que haja linhas.
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) {
			Rodada rodada = new Rodada(rs.getLong("id"), rs.getInt("id_competicao"), rs.getString("nome_competicao"), rs.getInt("numero_rodada"), rs.getInt("id_jogo"), 
							rs.getString("data_hora"), rs.getInt("clube1"), rs.getString("nome_clube1"),rs.getInt("resultado_clube1"), 
							rs.getInt("clube2"), rs.getString("nome_clube2"), rs.getInt("resultado_clube2"));
			rodadas.add(rodada);
			}
		}else{
			throw new RodadaNaoCadastradaException();
		}
		ps.close();
		rs.close();
		return rodadas;
	}
	
	public ArrayList<Rodada> listar() throws SQLException, RodadaNaoCadastradaException, Exception {
		return listar("");
	}

	public ArrayList<Rodada> procurar(int idCompeticao, int numeroDaRodada) throws SQLException, RodadaNaoCadastradaException, Exception {
		if (numeroDaRodada == 0) {
			return listar(" and id_competicao=" + idCompeticao);
		}else{
			return listar(" and id_competicao=" + idCompeticao + " and numero_rodada=" + numeroDaRodada);
		}
	}

	public void atualizar(Rodada rodada) throws SQLException, RodadaNaoCadastradaException, Exception {
		if(rodada != null){
			PreparedStatement ps = null;
			String sql = "";
			// instrução de update da rodada
			sql = "UPDATE " + NOME_TABELA + " SET id_competicao=?, numero_rodada=?, id_jogo=?, data_hora=?, clube1=?, resultado_clube1=?, clube2=?, resultado_clube2=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setInt(1, rodada.getIdCompeticao());
			ps.setInt(2, rodada.getNumeroRodada());
			ps.setInt(3, rodada.getIdJogo());
			ps.setString(4, rodada.getDataHora());
			ps.setInt(5, rodada.getClube1());
			ps.setInt(6, rodada.getResultadoClube1());
			ps.setInt(7, rodada.getClube2());
			ps.setInt(8, rodada.getResultadoClube2());
			ps.setLong(9, rodada.getId());
			Integer resultado = ps.executeUpdate();
			// se a atualização for efetuada com êxito o atributo resultado terá um valor diferente de 0, caso contrario levanta uma exception
			if (resultado == 0) throw new RodadaNaoCadastradaException();
			// fecha a conexão
			ps.close();
		}
	}

	public void deletar(long id) throws SQLException, RodadaNaoCadastradaException, Exception {
		PreparedStatement ps = null;
		String sql = "DELETE FROM " + NOME_TABELA + " WHERE id=?";
		ps = connection.prepareStatement(sql);
		ps.setLong(1, id);
		Integer resultado = ps.executeUpdate();
		ps.close();
		if(resultado == 0) throw new RodadaNaoCadastradaException();
	}

	public boolean existe(int idCompeticao, int numeroDaRodada) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE id_competicao=? and numero_rodada=?";
		boolean resposta = false;		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, idCompeticao);
		ps.setInt(2, numeroDaRodada);
		rs = ps.executeQuery();
		if(rs.next()){
			resposta = true;
		}
		ps.close();
		rs.close();
		return resposta;
	}
}
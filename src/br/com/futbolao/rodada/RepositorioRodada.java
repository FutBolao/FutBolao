package br.com.futbolao.rodada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.futbolao.conexao.Conexao;
import br.com.futbolao.conexao.DataBase;
import br.com.futbolao.exception.ClubeJaCadastradoNessaRodadaException;
import br.com.futbolao.exception.JogoJaCadastradoNessaRodadaException;
import br.com.futbolao.exception.JogoNaoCadastradoNessaRodadaException;
import br.com.futbolao.exception.RodadaJaCadastradaException;
import br.com.futbolao.exception.RodadaNaoCadastradaException;
import br.com.futbolao.exception.RodadaTravadaException;

public class RepositorioRodada implements IRepositorioRodada {
	
	private static final String NOME_TABELA = "rodada";
	private static final String NOME_VIEW = "vw_rodada";
	private static final String NOME_VIEW_GRUPO = "vw_grupo";
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
	public void cadastrar(Rodada rodada) throws SQLException, RodadaTravadaException, ClubeJaCadastradoNessaRodadaException, JogoJaCadastradoNessaRodadaException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		if (existeTrava(rodada.getIdCompeticao(), rodada.getNumeroRodada()) ==  false){
			if (existeClubeNaRodada(rodada.getId(), rodada.getIdCompeticao(), rodada.getNumeroRodada(), rodada.getClube1(), rodada.getClube2()) == false) {
				if (existe(rodada.getId(), rodada.getIdCompeticao(), rodada.getNumeroRodada(), rodada.getIdJogo()) == false){
					sql = "INSERT INTO " + NOME_TABELA + " (id_competicao, numero_rodada, id_jogo, data_hora, clube1, clube2) VALUES (?,?,?,?,?);";
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
					throw new JogoJaCadastradoNessaRodadaException();
				}
			} else {
				throw new ClubeJaCadastradoNessaRodadaException();
			}
		} else {
			throw new RodadaTravadaException();
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
		sql += " ORDER BY id_competicao ASC, numero_rodada DESC, id_jogo ASC;";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		//se a consulta tiver algum resultado entro no loop e o executo adicionando o
		// resultado de cada linha ao array de rodada, até que haja linhas.
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) {
				Integer resultadoClube1;
				Integer resultadoClube2;
				if(rs.getObject("resultado_clube1") == null){
					resultadoClube1 = null;
				} else {
					resultadoClube1 = rs.getInt("resultado_clube1");
				}
				if(rs.getObject("resultado_clube2") == null){
					resultadoClube2 = null;
				} else {
					resultadoClube2 = rs.getInt("resultado_clube2");
				}
				Rodada rodada = new Rodada(rs.getLong("id"), rs.getInt("id_competicao"), rs.getString("nome_competicao"),
										   rs.getInt("numero_rodada"), rs.getInt("id_jogo"), 
										   rs.getString("data_hora").substring(8, 10) + "/" + rs.getString("data_hora").substring(5, 7) + "/"
										   + rs.getString("data_hora").substring(0, 4) + " " + rs.getString("data_hora").substring(11, 16), 
										   rs.getInt("clube1"), rs.getString("nome_clube1"), resultadoClube1, 
										   rs.getInt("clube2"), rs.getString("nome_clube2"), resultadoClube2, rs.getString("trava").charAt(0));
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
	
	public Rodada procurarPorId(long id) throws SQLException, RodadaNaoCadastradaException, Exception {
		return listar(" and id=" + id).get(0);
	}

	public ArrayList<Rodada> procurar(int idCompeticao, int numeroDaRodada) throws SQLException, RodadaNaoCadastradaException, Exception {
			return listar(" and id_competicao=" + idCompeticao + " and numero_rodada=" + numeroDaRodada);
	}
	
	// método para listar rodadas por competicao.
	public ArrayList<Integer> listarPorCompeticao(int competicao, char trava) throws SQLException, RodadaNaoCadastradaException, Exception {
		ArrayList<Integer> rodadas = new ArrayList<Integer>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		String complemento = "";
		if (trava != ' ') {
			complemento = " AND trava='" + trava +"'";
		}
		sql = "SELECT DISTINCT numero_rodada FROM " + NOME_TABELA + " ";
		sql += "WHERE id_competicao=" + competicao + complemento;
		sql += " ORDER BY numero_rodada DESC;";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		//se a consulta tiver algum resultado entro no loop e o executo adicionando o
		// resultado de cada linha ao array de rodada, até que haja linhas.
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) {
			rodadas.add(rs.getInt("numero_rodada"));
			}
		}else{
			throw new RodadaNaoCadastradaException();
		}
		ps.close();
		rs.close();
		return rodadas;
	}
	
	// método para listar rodadas por competicao.
	public ArrayList<Integer> listarPorCompeticaoComGrupo(int competicao) throws SQLException, RodadaNaoCadastradaException, Exception {
		ArrayList<Integer> rodadas = new ArrayList<Integer>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT DISTINCT id_rodada FROM " + NOME_VIEW_GRUPO + " ";
		sql += "WHERE id_competicao=" + competicao;
		sql += " ORDER BY id_rodada DESC;";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		//se a consulta tiver algum resultado entro no loop e o executo adicionando o
		// resultado de cada linha ao array de rodada, até que haja linhas.
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) {
			rodadas.add(rs.getInt("id_rodada"));
			}
		}else{
			throw new RodadaNaoCadastradaException();
		}
		ps.close();
		rs.close();
		return rodadas;
	}

	public void atualizar(Rodada rodada) throws SQLException, ClubeJaCadastradoNessaRodadaException, RodadaJaCadastradaException, RodadaNaoCadastradaException, Exception {
		if(rodada != null){
			if (existeClubeNaRodada(rodada.getId(), rodada.getIdCompeticao(), rodada.getNumeroRodada(), rodada.getClube1(), rodada.getClube2()) == false) {
				if (existe(rodada.getId(), rodada.getIdCompeticao(), rodada.getNumeroRodada(), rodada.getIdJogo()) == false){
				PreparedStatement ps = null;
				String sql = "";
				// instrução de update da rodada
				sql = "UPDATE " + NOME_TABELA + " SET id_jogo=?, data_hora=?, clube1=?, resultado_clube1=?, clube2=?, resultado_clube2=? WHERE id=?;";
				ps = this.connection.prepareStatement(sql);
				ps.setInt(1, rodada.getIdJogo());
				ps.setString(2, rodada.getDataHora());
				ps.setInt(3, rodada.getClube1());
				ps.setObject(4, rodada.getResultadoClube1());
				ps.setInt(5, rodada.getClube2());
				ps.setObject(6, rodada.getResultadoClube2());
				ps.setLong(7, rodada.getId());
				Integer resultado = ps.executeUpdate();
				// se a atualização for efetuada com êxito o atributo resultado terá um valor diferente de 0, caso contrario levanta uma exception
				if (resultado == 0) throw new JogoNaoCadastradoNessaRodadaException();
				// fecha a conexão
				ps.close();
				} else {
					throw new JogoJaCadastradoNessaRodadaException();
				}
			} else {
				throw new ClubeJaCadastradoNessaRodadaException();
			}
		}
	}
	
	public void travar(int idCompeticao, int numeroRodada) throws SQLException, Exception {
		if(idCompeticao != 0 && numeroRodada != 0){
			PreparedStatement ps = null;
			String sql = "";
			// instrução de update da rodada
			sql = "UPDATE " + NOME_TABELA + " SET trava='S' WHERE id_competicao=? and numero_rodada=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setInt(1, idCompeticao);
			ps.setInt(2, numeroRodada);
			ps.executeUpdate();
			ps.close();
		}
	}
	
	public void destravar(int idCompeticao, int numeroRodada) throws SQLException, Exception {
		if(idCompeticao != 0 && numeroRodada != 0){
			PreparedStatement ps = null;
			String sql = "";
			// instrução de update da rodada
			sql = "UPDATE " + NOME_TABELA + " SET trava='N' WHERE id_competicao=? and numero_rodada=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setInt(1, idCompeticao);
			ps.setInt(2, numeroRodada);
			ps.executeUpdate();
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

	public boolean existe(long id, int idCompeticao, int numeroDaRodada, int idJogo) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + NOME_TABELA + " WHERE id_competicao=? and numero_rodada=? and id_jogo=?";
		boolean resposta = false;		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, idCompeticao);
		ps.setInt(2, numeroDaRodada);
		ps.setInt(3, idJogo);
		rs = ps.executeQuery();
		if(rs.next()){
			if (id != 0 && id == rs.getLong("id")){
				resposta = false;
			}else{
				resposta = true;
			}
		}
		ps.close();
		rs.close();
		return resposta;
	}
	
	private boolean existeTrava(int idCompeticao, int numeroDaRodada) throws SQLException, Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + NOME_TABELA + " WHERE id_competicao=? and numero_rodada=? and trava='S'";
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
	
	private boolean existeClubeNaRodada(long id, int idCompeticao, int numeroDaRodada, int Clube1, int Clube2) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE id_competicao=? and numero_rodada=? and (clube1=? or clube2=? or clube1=? or clube2=?)";
		boolean resposta = false;		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, idCompeticao);
		ps.setInt(2, numeroDaRodada);
		ps.setInt(3, Clube1);
		ps.setInt(4, Clube1);
		ps.setInt(5, Clube2);
		ps.setInt(6, Clube2);
		rs = ps.executeQuery();
		if(rs.next()){
			if (id == 0) {
				resposta = true;
			}else if (id != 0 && id != rs.getLong("id")){
				resposta = true;
			}else if (id != 0 && id == rs.getLong("id") && (Clube1 == rs.getInt("clube1") && Clube2 == rs.getInt("clube2")) || 
					(Clube2 == rs.getInt("clube1") && Clube1 == rs.getInt("clube2")) ){
				resposta = false;
			}else{
				resposta = true;
			}
		}
		ps.close();
		rs.close();
		return resposta;
	}
}
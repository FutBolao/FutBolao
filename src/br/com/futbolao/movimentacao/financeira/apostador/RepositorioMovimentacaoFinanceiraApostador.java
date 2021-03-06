package br.com.futbolao.movimentacao.financeira.apostador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import br.com.futbolao.conexao.Conexao;
import br.com.futbolao.conexao.DataBase;
import br.com.futbolao.exception.MovimentacaoNaoCadastradaException;

public class RepositorioMovimentacaoFinanceiraApostador implements IRepositorioMovimentacaoFinanceiraApostador {
	
	public static final String NOME_TABELA = "mov_fin_apostador";
	public static final String NOME_VIEW = "vw_mov_fin_apostador";
	private Connection connection;
	private int dataBase = 0;
	
	//construtor padr�o, onde seleciona o banco mysql caso n�o seja executado o construtor com argumento
	public RepositorioMovimentacaoFinanceiraApostador() throws Exception{
		this.connection = Conexao.getConexao(DataBase.MYSQL);
		this.dataBase = DataBase.MYSQL;
	}
	
	//construtor com argumento, que recebe como argumento o tipo de banco a ser executado.
	public RepositorioMovimentacaoFinanceiraApostador(int dataBase) throws Exception{
		this.connection = Conexao.getConexao(dataBase);
		this.dataBase = dataBase;
	}

	@Override
	public void cadastrar(MovimentacaoFinanceiraApostador movimentacaoFinanceiraApostador) 
			throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
			sql = "INSERT INTO " + NOME_TABELA + " (id_apostador, tipo_movimentacao, valor) VALUES (?,?,?);";
			if (this.dataBase == DataBase.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			ps.setLong(1, movimentacaoFinanceiraApostador.getIdApostador());
			ps.setString(2, movimentacaoFinanceiraApostador.getTipoMovimentacao());
			ps.setDouble(3, movimentacaoFinanceiraApostador.getValor());
			ps.execute();
			rs = ps.getGeneratedKeys();
			int id = 0;
			// Pegando o identificador gerado a partir do �ltimo insert
			while (rs.next()) {
				id = rs.getInt(1);
			}
			movimentacaoFinanceiraApostador.setId(id);
		ps.close();
		rs.close();
	}

	// m�todo para listar rodadas.
	private ArrayList<MovimentacaoFinanceiraApostador> listar(String complemento) throws SQLException, MovimentacaoNaoCadastradaException, Exception {
		ArrayList<MovimentacaoFinanceiraApostador> movimentacaoFinanceiraApostadores = new ArrayList<MovimentacaoFinanceiraApostador>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + NOME_VIEW + " ";
		sql += "WHERE id IS NOT NULL";
		sql += complemento;
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		//se a consulta tiver algum resultado entro no loop e o executo adicionando o
		// resultado de cada linha ao array de rodada, at� que haja linhas.
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) { 
				MovimentacaoFinanceiraApostador movimentacaoFinanceiraApostador = new MovimentacaoFinanceiraApostador
						(rs.getLong("id"), rs.getLong("id_apostador"), rs.getString("nome_apostador"), rs.getString("tipo_movimentacao"), 
						rs.getDouble("valor"), rs.getString("data_hora").substring(8, 10) + "/" + rs.getString("data_hora").substring(5, 7) + "/"
								   + rs.getString("data_hora").substring(0, 4) + " " + rs.getString("data_hora").substring(11, 16));
				movimentacaoFinanceiraApostadores.add(movimentacaoFinanceiraApostador);
			}
		}else{
			throw new MovimentacaoNaoCadastradaException();
		}
		ps.close();
		rs.close();
		return movimentacaoFinanceiraApostadores;
	}
	
	public ArrayList<MovimentacaoFinanceiraApostador> listar() throws SQLException, MovimentacaoNaoCadastradaException, Exception {
		return listar("");
	}

	public ArrayList<MovimentacaoFinanceiraApostador> procurar(int idApostador, String tipoMovimentacao) throws SQLException, MovimentacaoNaoCadastradaException, Exception {
		if (tipoMovimentacao.equals("")) {
			return listar(" and id_apostador=" + idApostador);
		}else{
			return listar(" and id_apostador=" + idApostador + " and tipo_movimentacao=" + tipoMovimentacao);
		}
	}

	public void atualizar(MovimentacaoFinanceiraApostador movimentacaoFinanceiraApostador) throws SQLException, MovimentacaoNaoCadastradaException, Exception {
		if(movimentacaoFinanceiraApostador != null){
			PreparedStatement ps = null;
			String sql = "";
			// instru��o de update da rodada
			sql = "UPDATE " + NOME_TABELA + " SET id_apostador=?, tipo_movimentacao=?, valor=?, data_hora=NOW() WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setLong(1, movimentacaoFinanceiraApostador.getIdApostador());
			ps.setString(2, movimentacaoFinanceiraApostador.getTipoMovimentacao());
			ps.setDouble(3, movimentacaoFinanceiraApostador.getValor());
			ps.setLong(4, movimentacaoFinanceiraApostador.getId());
			Integer resultado = ps.executeUpdate();
			// se a atualiza��o for efetuada com �xito o atributo resultado ter� um valor diferente de 0, caso contrario levanta uma exception
			if (resultado == 0) throw new MovimentacaoNaoCadastradaException();
			// fecha a conex�o
			ps.close();
		}
	}

	public void deletar(long id) throws SQLException, MovimentacaoNaoCadastradaException, Exception {
		PreparedStatement ps = null;
		String sql = "DELETE FROM " + NOME_TABELA + " WHERE id=?";
		ps = connection.prepareStatement(sql);
		ps.setLong(1, id);
		Integer resultado = ps.executeUpdate();
		ps.close();
		if(resultado == 0) throw new MovimentacaoNaoCadastradaException();
	}

	public boolean existe(int id) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE id=?";
		boolean resposta = false;		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		if(rs.next()){
			resposta = true;
		}
		ps.close();
		rs.close();
		return resposta;
	}
}
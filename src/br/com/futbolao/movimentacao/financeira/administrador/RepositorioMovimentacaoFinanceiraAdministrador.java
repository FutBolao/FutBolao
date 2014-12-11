package br.com.futbolao.movimentacao.financeira.administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import br.com.futbolao.conexao.Conexao;
import br.com.futbolao.conexao.DataBase;
import br.com.futbolao.exception.MovimentacaoNaoCadastradaException;

public class RepositorioMovimentacaoFinanceiraAdministrador implements IRepositorioMovimentacaoFinanceiraAdministrador {
	
	public static final String NOME_TABELA = "mov_fin_admin";
	public static final String NOME_VIEW = "vw_mov_fin_admin";
	public static final String NOME_VIEW_CAIXA = "vw_caixa";
	private Connection connection;
	private int dataBase = 0;
	
	//construtor padrão, onde seleciona o banco mysql caso não seja executado o construtor com argumento
	public RepositorioMovimentacaoFinanceiraAdministrador() throws Exception{
		this.connection = Conexao.getConexao(DataBase.MYSQL);
		this.dataBase = DataBase.MYSQL;
	}
	
	//construtor com argumento, que recebe como argumento o tipo de banco a ser executado.
	public RepositorioMovimentacaoFinanceiraAdministrador(int dataBase) throws Exception{
		this.connection = Conexao.getConexao(dataBase);
		this.dataBase = dataBase;
	}

	@Override
	public void cadastrar(MovimentacaoFinanceiraAdministrador movimentacaoFinanceiraAdministrador) 
			throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
			sql = "INSERT INTO " + NOME_TABELA + " (id_administrador, tipo_movimentacao, valor) VALUES (?,?,?);";
			if (this.dataBase == DataBase.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			ps.setLong(1, movimentacaoFinanceiraAdministrador.getIdAdministrador());
			ps.setString(2, movimentacaoFinanceiraAdministrador.getTipoMovimentacao());
			ps.setDouble(3, movimentacaoFinanceiraAdministrador.getValor());
			ps.execute();
			rs = ps.getGeneratedKeys();
			int id = 0;
			// Pegando o identificador gerado a partir do último insert
			while (rs.next()) {
				id = rs.getInt(1);
			}
			movimentacaoFinanceiraAdministrador.setId(id);
		ps.close();
		rs.close();
	}

	// método para listar rodadas.
	private ArrayList<MovimentacaoFinanceiraAdministrador> listar(String complemento) throws SQLException, MovimentacaoNaoCadastradaException, Exception {
		ArrayList<MovimentacaoFinanceiraAdministrador> movimentacaoFinanceiraAdministradores = new ArrayList<MovimentacaoFinanceiraAdministrador>();
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
				MovimentacaoFinanceiraAdministrador movimentacaoFinanceiraAdministrador = new MovimentacaoFinanceiraAdministrador
						(rs.getLong("id"), rs.getLong("id_administrador"), rs.getString("nome_administrador"), rs.getString("tipo_movimentacao"), 
						rs.getDouble("valor"), rs.getString("data_hora").substring(8, 10) + "/" + rs.getString("data_hora").substring(5, 7) + "/"
										   + rs.getString("data_hora").substring(0, 4) + " " + rs.getString("data_hora").substring(11, 16));
				movimentacaoFinanceiraAdministradores.add(movimentacaoFinanceiraAdministrador);
			}
		}else{
			throw new MovimentacaoNaoCadastradaException();
		}
		ps.close();
		rs.close();
		return movimentacaoFinanceiraAdministradores;
	}
	
	public ArrayList<MovimentacaoFinanceiraAdministrador> listar() throws SQLException, MovimentacaoNaoCadastradaException, Exception {
		return listar("");
	}

	public ArrayList<MovimentacaoFinanceiraAdministrador> procurar(int idAdministrador, String tipoMovimentacao) throws SQLException, MovimentacaoNaoCadastradaException, Exception {
		if (tipoMovimentacao.equals("")) {
			return listar(" and id_administrador=" + idAdministrador);
		}else{
			return listar(" and id_administrador=" + idAdministrador + " and tipo_movimentacao=" + tipoMovimentacao);
		}
	}

	public void atualizar(MovimentacaoFinanceiraAdministrador movimentacaoFinanceiraAdministrador) throws SQLException, MovimentacaoNaoCadastradaException, Exception {
		if(movimentacaoFinanceiraAdministrador != null){
			PreparedStatement ps = null;
			String sql = "";
			// instrução de update da rodada
			sql = "UPDATE " + NOME_TABELA + " SET id_administrador=?, tipo_movimentacao=?, valor=?, data_hora=NOW() WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setLong(1, movimentacaoFinanceiraAdministrador.getIdAdministrador());
			ps.setString(2, movimentacaoFinanceiraAdministrador.getTipoMovimentacao());
			ps.setDouble(3, movimentacaoFinanceiraAdministrador.getValor());
			ps.setLong(4, movimentacaoFinanceiraAdministrador.getId());
			Integer resultado = ps.executeUpdate();
			// se a atualização for efetuada com êxito o atributo resultado terá um valor diferente de 0, caso contrario levanta uma exception
			if (resultado == 0) throw new MovimentacaoNaoCadastradaException();
			// fecha a conexão
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
	
	public double caixa() throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_VIEW_CAIXA;
		double resposta = 0;		
		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next()){
			resposta = rs.getDouble("caixa");
		}
		ps.close();
		rs.close();
		return resposta;
	}
}
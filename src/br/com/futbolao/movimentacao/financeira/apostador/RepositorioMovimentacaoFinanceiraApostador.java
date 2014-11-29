package br.com.futbolao.movimentacao.financeira.apostador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import br.com.futbolao.conexao.Conexao;
import br.com.futbolao.conexao.DataBase;
import br.com.futbolao.exception.MovimentacaoJaCadastradaException;
import br.com.futbolao.exception.MovimentacaoNaoCadastradaException;

public class RepositorioMovimentacaoFinanceiraApostador implements IRepositorioMovimentacaoFinanceiraApostador {
	
	public static final String NOME_TABELA = "mov_fin_apostador";
	public static final String NOME_VIEW = "vw_mov_fin_apostador";
	private Connection connection;
	private int dataBase = 0;
	
	//construtor padrão, onde seleciona o banco mysql caso não seja executado o construtor com argumento
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
			throws SQLException, MovimentacaoJaCadastradaException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		if (existe(movimentacaoFinanceiraApostador.getIdCompeticao(), movimentacaoFinanceiraApostador.getNumeroRodada()) == false){
			sql = "INSERT INTO " + NOME_TABELA + " (id_competicao, numero_rodada, id_jogo, clube1, resultado_clube1, "
					+ "clube2, resultado_clube2) VALUES (?,?,?,?,?,?,?);";
			if (this.dataBase == DataBase.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			ps.setInt(1, movimentacaoFinanceiraApostador.getIdCompeticao());
			ps.setInt(2, movimentacaoFinanceiraApostador.getNumeroRodada());
			ps.setInt(3, movimentacaoFinanceiraApostador.getIdJogo());
			ps.setInt(4, movimentacaoFinanceiraApostador.getClube1());
			ps.setInt(5, movimentacaoFinanceiraApostador.getResultadoClube1());
			ps.setInt(6, movimentacaoFinanceiraApostador.getClube2());
			ps.setInt(7, movimentacaoFinanceiraApostador.getResultadoClube2());
			ps.execute();
			rs = ps.getGeneratedKeys();
			int id = 0;
			// Pegando o identificador gerado a partir do último insert
			while (rs.next()) {
				id = rs.getInt(1);
			}
			movimentacaoFinanceiraApostador.setId(id);
		} else {
			throw new MovimentacaoJaCadastradaException();
		}
		ps.close();
		rs.close();
	}

	// método para listar rodadas.
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
		// resultado de cada linha ao array de rodada, até que haja linhas.
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) {
			MovimentacaoFinanceiraApostador movimentacaoFinanceiraApostador = new MovimentacaoFinanceiraApostador(id, idApostador, nomeApostador, tipoMovimentacao, valor, dataHora)
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

	public ArrayList<MovimentacaoFinanceiraApostador> procurar(int idCompeticao, int numeroDaRodada) throws SQLException, MovimentacaoNaoCadastradaException, Exception {
		if (numeroDaRodada == 0) {
			return listar(" and id_competicao=" + idCompeticao);
		}else{
			return listar(" and id_competicao=" + idCompeticao + " and numero_rodada=" + numeroDaRodada);
		}
	}

	public void atualizar(MovimentacaoFinanceiraApostador movimentacaoFinanceiraApostador) throws SQLException, MovimentacaoNaoCadastradaException, Exception {
		if(movimentacaoFinanceiraApostador != null){
			PreparedStatement ps = null;
			String sql = "";
			// instrução de update da rodada
			sql = "UPDATE " + NOME_TABELA + " SET id_apostador=?, tipo_movimentacao=?, valor=?, data_hora=NOW() WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setLong(1, movimentacaoFinanceiraApostador.getIdApostador());
			ps.setString(2, movimentacaoFinanceiraApostador.getTipoMovimentacao());
			ps.setDouble(3, movimentacaoFinanceiraApostador.getValor());
			ps.setLong(4, movimentacaoFinanceiraApostador.getId());
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
}
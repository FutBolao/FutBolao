package br.com.futbolao.estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.futbolao.conexao.Conexao;
import br.com.futbolao.conexao.DataBase;
import br.com.futbolao.exception.EstadoJaCadastradoException;
import br.com.futbolao.exception.EstadoNaoCadastradoException;

public class RepositorioEstado implements IRepositorioEstado{
	
	public static final String NOME_TABELA = "pais";
	public static final String NOME_VIEW = "vw_pais";
	private Connection connection;
	private int dataBase = 0;
	
	//construtor padr�o, onde seleciona o banco mysql caso n�o seja executado o construtor com argumento
	public RepositorioEstado() throws Exception{
		this.connection = Conexao.getConexao(DataBase.MYSQL);
		this.dataBase = DataBase.MYSQL;
	}
	
	//construtor com argumento, que recebe como argumento o tipo de banco a ser executado.
	public RepositorioEstado(int dataBase) throws Exception{
		this.connection = Conexao.getConexao(dataBase);
		this.dataBase = dataBase;
	}

	public void cadastrar(Estado estado) throws SQLException, EstadoJaCadastradoException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		if (existe(estado.getNome(), estado.getIdPais()) == false){
			sql = "INSERT INTO " + NOME_TABELA + " (nome, uf, id_pais) VALUES (?,?,?);";
			if (this.dataBase == DataBase.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			ps.setString(1, estado.getNome());
			ps.setString(2, estado.getUf());
			ps.setInt(2, estado.getIdPais());
			ps.execute();
			rs = ps.getGeneratedKeys();
			int id = 0;
			// Pegando o identificador gerado a partir do �ltimo insert
			while (rs.next()) {
				id = rs.getInt(1);
			}
			estado.setId(id);
		} else {
			throw new EstadoJaCadastradoException();
		}
		ps.close();
		rs.close();
	}
	
	// m�todo para listar clubes.
	private ArrayList<Estado> listar(String complemento) throws SQLException, EstadoNaoCadastradoException, Exception {
		ArrayList<Estado> estados = new ArrayList<Estado>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + NOME_VIEW + " ";
		sql += "WHERE id IS NOT NULL";
		sql += complemento;
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		//se a consulta tiver algum resultado entro no loop e o executo adicionando o
		// resultado de cada linha ao array de clube, at� que haja linhas.
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) {
			Estado estado = new Estado(rs.getInt("id"), rs.getString("nome"), rs.getString("uf"), rs.getInt("id_pais"));
			estados.add(estado);
			}
		}else{
			throw new EstadoNaoCadastradoException();
		}
		ps.close();
		rs.close();
		return estados;
	}
	
	// m�todo para listar estado por nome.
	public ArrayList<Estado> procurarPorNome(String nome) throws SQLException, EstadoNaoCadastradoException, Exception{
		return listar(" and nome like '%" + nome + "%'");
	}

	// m�todo para listar estado por pais.
	public ArrayList<Estado> procurarPorPais(int idPais) throws SQLException, EstadoNaoCadastradoException, Exception{
		return listar(" and id_pais=" + idPais);
	}

	// m�todo para atualizar estado
	public void atualizar(Estado estado) throws SQLException, EstadoNaoCadastradoException, Exception {
		if(estado != null){
			PreparedStatement ps = null;
			String sql = "";
			// instru��o de update do estado
			sql = "UPDATE " + NOME_TABELA + " SET nome=?, uf=?, id_pais=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, estado.getNome());
			ps.setString(2, estado.getUf());
			ps.setInt(3, estado.getIdPais());
			ps.setInt(4, estado.getId());
			Integer resultado = ps.executeUpdate();
			// se a atualiza��o for efetuada com �xito o atributo resultado ter� um valor diferente de 0, caso contrario levanta uma exception
			if (resultado == 0) throw new EstadoNaoCadastradoException();
			// fecha a conex�o
			ps.close();
		}
	}
	
	// m�todo para deletar estado.
	public void deletar(int id) throws SQLException, EstadoNaoCadastradoException, Exception {
		PreparedStatement ps = null;
		String sql = "DELETE FROM " + NOME_TABELA + " WHERE id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		Integer resultado = ps.executeUpdate();
		ps.close();
		if(resultado == 0) throw new EstadoNaoCadastradoException();
	}

	// m�todo para verificar se existe estado pelo nome.
	public boolean existe(String nome, int idPais) throws SQLException, Exception {
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

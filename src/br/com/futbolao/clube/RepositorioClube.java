package br.com.futbolao.clube;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.futbolao.conexao.Conexao;
import br.com.futbolao.conexao.DataBase;
import br.com.futbolao.exception.ClubeJaCadastradoException;
import br.com.futbolao.exception.ClubeNaoCadastradoException;

public class RepositorioClube implements IRepositorioClube{
	
	public static final String NOME_TABELA = "clube";
	private Connection connection;
	private int dataBase = 0;
	
	//construtor padrão, onde seleciona o banco mysql caso não seja executado o construtor com argumento
	public RepositorioClube() throws Exception{
		this.connection = Conexao.getConexao(DataBase.MYSQL);
		this.dataBase = DataBase.MYSQL;
	}
	
	//construtor com argumento, que recebe como argumento o tipo de banco a ser executado.
	public RepositorioClube(int dataBase) throws Exception{
		this.connection = Conexao.getConexao(dataBase);
		this.dataBase = dataBase;
	}

	public void cadastrar(Clube clube) throws SQLException,	ClubeJaCadastradoException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		if (existe(clube.getNomeCompleto()) == false){
			sql = "INSERT INTO " + NOME_TABELA + " (nome, nome_completo, sigla, ativo, estado, pais) VALUES (?,?,?,?,?,?);";
			if (this.dataBase == DataBase.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			ps.setString(1, clube.getNome());
			ps.setString(2, clube.getNomeCompleto());
			ps.setString(3, clube.getSigla());
			ps.setString(4, String.valueOf(clube.getAtivo()));
			ps.setString(5, clube.getEstado());
			ps.setString(6, clube.getPais());
			ps.execute();
			rs = ps.getGeneratedKeys();
			int id = 0;
			// Pegando o identificador gerado a partir do último insert
			while (rs.next()) {
				id = rs.getInt(1);
			}
			clube.setId(id);
		} else {
			throw new ClubeJaCadastradoException();
		}
		ps.close();
		rs.close();
	}
	
	// método para listar clubes.
	private ArrayList<Clube> listar(String complemento) throws SQLException, ClubeNaoCadastradoException, Exception {
		ArrayList<Clube> clubes = new ArrayList<Clube>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + NOME_TABELA + " ";
		sql += "WHERE id IS NOT NULL";
		sql += complemento;
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		//se a consulta tiver algum resultado entro no loop e o executo adicionando o
		// resultado de cada linha ao array de clube, até que haja linhas.
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) {
			Clube clube = new Clube(rs.getInt("id"), rs.getString("nome"), rs.getString("nome_completo"), rs.getString("sigla"), 
					rs.getString("ativo").charAt(0), rs.getString("estado"), rs.getString("pais"));
			clubes.add(clube);
			}
		}else{
			throw new ClubeNaoCadastradoException();
		}
		ps.close();
		rs.close();
		return clubes;
	}
	
	// método para listar clubes.
	public ArrayList<Clube> procurarPorNome(String nome) throws SQLException, ClubeNaoCadastradoException, Exception{
		return listar(" and nome like '%" + nome + "%'");
	}
	
	// método para listar clubes.
	public ArrayList<Clube> listar() throws SQLException, ClubeNaoCadastradoException, Exception{
		return listar("");
	}

	// método para atualizar clube
	public void atualizar(Clube clube) throws SQLException,	ClubeNaoCadastradoException, Exception {
		if(clube != null){
			PreparedStatement ps = null;
			String sql = "";
			// instrução de update do clube
			sql = "UPDATE " + NOME_TABELA + " SET nome=?, nome_completo=?, sigla=?, ativo=?, estado=?, pais=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, clube.getNome());
			ps.setString(2, clube.getNomeCompleto());
			ps.setString(3, clube.getSigla());
			ps.setString(4, String.valueOf(clube.getAtivo()));
			ps.setString(5, clube.getEstado());
			ps.setString(6, clube.getPais());
			ps.setInt(7, clube.getId());
			Integer resultado = ps.executeUpdate();
			// se a atualizaçãp for efetuada com êxito o atributo resultado terá um valor diferente de 0, caso contrario levanta uma exception
			if (resultado == 0) throw new ClubeNaoCadastradoException();
			// fecha a conexão
			ps.close();
		}
	}
	
	// método para deletar clube.
	public void deletar(int id) throws SQLException, ClubeNaoCadastradoException, Exception {
		PreparedStatement ps = null;
		String sql = "DELETE FROM " + NOME_TABELA + " WHERE id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		Integer resultado = ps.executeUpdate();
		ps.close();
		if(resultado == 0) throw new ClubeNaoCadastradoException();
	}

	// método para verificar se existe clube pelo nome.
	public boolean existe(String nomeCompleto) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE nome_completo=?";
		boolean resposta = false;		
		ps = connection.prepareStatement(sql);
		ps.setString(1, nomeCompleto);
		rs = ps.executeQuery();
		if(rs.next()){
			resposta = true;
		}
		ps.close();
		rs.close();
		return resposta;
	}
}

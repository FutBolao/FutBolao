package br.com.futbolao.pais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.futbolao.conexao.Conexao;
import br.com.futbolao.conexao.DataBase;
import br.com.futbolao.exception.PaisJaCadastradoException;
import br.com.futbolao.exception.PaisNaoCadastradoException;

public class RepositorioPais implements IRepositorioPais{
	
	public static final String NOME_TABELA = "pais";
	private Connection connection;
	private int dataBase = 0;
	
	//construtor padrão, onde seleciona o banco mysql caso não seja executado o construtor com argumento
	public RepositorioPais() throws Exception{
		this.connection = Conexao.getConexao(DataBase.MYSQL);
		this.dataBase = DataBase.MYSQL;
	}
	
	//construtor com argumento, que recebe como argumento o tipo de banco a ser executado.
	public RepositorioPais(int dataBase) throws Exception{
		this.connection = Conexao.getConexao(dataBase);
		this.dataBase = dataBase;
	}

	public void cadastrar(Pais pais) throws SQLException, PaisJaCadastradoException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		if (existe(pais.getNome()) == false){
			sql = "INSERT INTO " + NOME_TABELA + " (nome, sigla) VALUES (?,?);";
			if (this.dataBase == DataBase.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			ps.setString(1, pais.getNome());
			ps.setString(2, pais.getSigla());
			ps.execute();
			rs = ps.getGeneratedKeys();
			int id = 0;
			// Pegando o identificador gerado a partir do último insert
			while (rs.next()) {
				id = rs.getInt(1);
			}
			pais.setId(id);
		} else {
			throw new PaisJaCadastradoException();
		}
		ps.close();
		rs.close();
	}
	
	// método para listar clubes.
	private ArrayList<Pais> listar(String complemento) throws SQLException, PaisNaoCadastradoException, Exception {
		ArrayList<Pais> paises = new ArrayList<Pais>();
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
			Pais pais = new Pais(rs.getInt("id"), rs.getString("nome"), rs.getString("sigla"));
			paises.add(pais);
			}
		}else{
			throw new PaisNaoCadastradoException();
		}
		ps.close();
		rs.close();
		return paises;
	}
	
	// método para listar clubes.
	public ArrayList<Pais> procurarPorNome(String nome) throws SQLException, PaisNaoCadastradoException, Exception{
		return listar(" and nome like '%" + nome + "%'");
	}
	
	// método para listar clubes.
	public ArrayList<Pais> listar() throws SQLException, PaisNaoCadastradoException, Exception{
		return listar("");
	}

	// método para atualizar clube
	public void atualizar(Pais pais) throws SQLException, PaisNaoCadastradoException, Exception {
		if(pais != null){
			PreparedStatement ps = null;
			String sql = "";
			// instrução de update do clube
			sql = "UPDATE " + NOME_TABELA + " SET nome=?, nome_completo=?, sigla=?, ativo=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, pais.getNome());
			ps.setString(2, pais.getSigla());
			ps.setInt(3, pais.getId());
			Integer resultado = ps.executeUpdate();
			// se a atualizaçãp for efetuada com êxito o atributo resultado terá um valor diferente de 0, caso contrario levanta uma exception
			if (resultado == 0) throw new PaisNaoCadastradoException();
			// fecha a conexão
			ps.close();
		}
	}
	
	// método para deletar clube.
	public void deletar(int id) throws SQLException, PaisNaoCadastradoException, Exception {
		PreparedStatement ps = null;
		String sql = "DELETE FROM " + NOME_TABELA + " WHERE id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		Integer resultado = ps.executeUpdate();
		ps.close();
		if(resultado == 0) throw new PaisNaoCadastradoException();
	}

	// método para verificar se existe clube pelo nome.
	public boolean existe(String nome) throws SQLException, Exception {
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

package br.com.futbolao.permissao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.futbolao.conexao.Conexao;
import br.com.futbolao.conexao.DataBase;
import br.com.futbolao.exception.PermissaoJaCadastradaException;
import br.com.futbolao.exception.PermissaoNaoCadastradaException;

public class RepositorioPermissao implements IRepositorioPermissao{
	
	public static final String NOME_TABELA = "permissao_admin";
	private Connection connection;
	private int dataBase = 0;
	
	//construtor padrão, onde seleciona o banco mysql caso não seja executado o construtor com argumento
	public RepositorioPermissao() throws Exception{
		this.connection = Conexao.getConexao(DataBase.MYSQL);
		this.dataBase = DataBase.MYSQL;
	}
	
	//construtor com argumento, que recebe como argumento o tipo de banco a ser executado.
	public RepositorioPermissao(int dataBase) throws Exception{
		this.connection = Conexao.getConexao(dataBase);
		this.dataBase = dataBase;
	}

	public void cadastrar(Permissao permissao) throws SQLException,	PermissaoJaCadastradaException, Exception {
		PreparedStatement ps = null;
		String sql = "";
		if (existe(permissao.getIdAdminstrador(), permissao.getModulo()) == false){
			sql = "INSERT INTO " + NOME_TABELA + " (id_administrador, permissao, modulo) VALUES (?,?,?);";
			ps = this.connection.prepareStatement(sql);
			ps.setInt(1, permissao.getIdAdminstrador());
			ps.setInt(2, permissao.getPermissao());
			ps.setInt(2, permissao.getModulo());
			ps.execute();
		} else {
			throw new PermissaoJaCadastradaException();
		}
		ps.close();
	}
	
	// método para listar permissao.
	public Permissao listar(int administrador, int modulo) throws SQLException, PermissaoNaoCadastradaException, Exception {
		Permissao permissao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + NOME_TABELA + " ";
		sql += "WHERE id_administrador=? and modulo=?";
		ps = this.connection.prepareStatement(sql);
		ps.setInt(1, administrador);
		ps.setInt(1, modulo);
		rs = ps.executeQuery();
		if (rs.next()) {
			permissao = new Permissao(rs.getInt("id_administrador"), rs.getInt("permissao"), rs.getInt("modulo"));
		}else{
			throw new PermissaoNaoCadastradaException();
		}
		ps.close();
		rs.close();
		return permissao;
	}

	// método para atualizar permissao
	public void atualizar(Permissao permissao) throws SQLException,	PermissaoNaoCadastradaException, Exception {
		if(permissao != null){
			PreparedStatement ps = null;
			String sql = "";
			// instrução de update da permissao
			sql = "UPDATE " + NOME_TABELA + " SET permissao=? WHERE id_administrador=? and modulo=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setInt(1, permissao.getPermissao());
			ps.setInt(2, permissao.getIdAdminstrador());
			ps.setInt(3, permissao.getModulo());
			Integer resultado = ps.executeUpdate();
			// se a atualização for efetuada com êxito o atributo resultado terá um valor diferente de 0, caso contrario levanta uma exception
			if (resultado == 0) throw new PermissaoNaoCadastradaException();
			// fecha a conexão
			ps.close();
		}
	}
	
	// método para deletar permissao.
	public void deletar(int administrador) throws SQLException, PermissaoNaoCadastradaException, Exception {
		PreparedStatement ps = null;
		String sql = "DELETE FROM " + NOME_TABELA + " WHERE id_administrador=?";
		ps = connection.prepareStatement(sql);
		ps.setLong(1, administrador);
		Integer resultado = ps.executeUpdate();
		ps.close();
		if(resultado == 0) throw new PermissaoNaoCadastradaException();
	}

	// método para verificar se existe permissao para o administrador no modulo.
	public boolean existe(int administrador, int modulo) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE id_administrador=? and modulo=?";
		boolean resposta = false;		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, administrador);
		ps.setInt(1, modulo);
		rs = ps.executeQuery();
		if(rs.next()){
			resposta = true;
		}
		ps.close();
		rs.close();
		return resposta;
	}
}

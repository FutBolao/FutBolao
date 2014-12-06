package br.com.futbolao.administrador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.futbolao.conexao.Conexao;
import br.com.futbolao.conexao.DataBase;
import br.com.futbolao.exception.AdministradorJaCadastradoException;
import br.com.futbolao.exception.AdministradorNaoCadastradoException;
import br.com.futbolao.util.Endereco;

public class RepositorioAdministrador implements IRepositorioAdministrador {
	
	private static final String NOME_TABELA = "administrador";
	private Connection connection;
	private int dataBase = 0;
	
	//construtor padrão, onde seleciona o banco mysql caso não seja executado o construtor com argumento
	public RepositorioAdministrador() throws Exception{
		this.connection = Conexao.getConexao(DataBase.MYSQL);
		this.dataBase = DataBase.MYSQL;
	}
	
	//construtor com argumento, que recebe como argumento o tipo de banco a ser executado.
	public RepositorioAdministrador(int dataBase) throws Exception{
		this.connection = Conexao.getConexao(dataBase);
		this.dataBase = dataBase;
	}
	
	//método cadastrar
	public void cadastrar(Administrador administrador) throws SQLException, AdministradorJaCadastradoException, Exception {
		// verifico se o objeto apostador está criado
		if (administrador != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "";
			// verifico se o apostador já existe na base, caso exista,
			// levanto a uma exception
			if (existe(administrador.getCpf()) == false) {
				sql = "INSERT INTO " + NOME_TABELA + " (cpf, nome, sexo, telefone, email, logradouro, numero, bairro, cidade, estado, "
						+ "pais ,data_de_nascimento, usuario, senha, ativo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				if (this.dataBase == DataBase.ORACLE) {
					ps = this.connection.prepareStatement(sql, new String[] { "id" });
				} else {
					ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				}
				ps.setString(1, administrador.getCpf());
				ps.setString(2, administrador.getNome());
				ps.setString(3, String.valueOf(administrador.getSexo()));
				ps.setString(4, administrador.getTelefone());
				ps.setString(5, administrador.getEmail());
				ps.setString(6, administrador.getEndereco().getLogradouro());
				ps.setString(7, administrador.getEndereco().getNumero());
				ps.setString(8, administrador.getEndereco().getBairro());
				ps.setString(9, administrador.getEndereco().getCidade());
				ps.setString(10, administrador.getEndereco().getEstado());
				ps.setString(11, administrador.getEndereco().getPais());
				ps.setString(12, administrador.getDataDeNascimento());
				ps.setString(13, administrador.getUsuario());
				ps.setString(14, administrador.getSenha());
				ps.setString(15, String.valueOf(administrador.getAtivo()));
				ps.execute();
				rs = ps.getGeneratedKeys();
				int id = 0;
				// Pegando o identificador gerado a partir do último insert
				while (rs.next()) {
					id = rs.getInt(1);
				}
				administrador.setId(id);
			} else {
				throw new AdministradorJaCadastradoException();
			}
			ps.close();
			rs.close();
		}
	}
	
	// método para listar apostadores.
	private ArrayList<Administrador> listar(String complemento) throws SQLException, AdministradorNaoCadastradoException, Exception {
		ArrayList<Administrador> administradores = new ArrayList<Administrador>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + NOME_TABELA + " ";
		sql += "WHERE id IS NOT NULL";
		sql += complemento;
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		//se a consulta tiver algum resultado entro no loop e o executo adicionando o
		// resultado de cada linha ao array de apostador, até que haja linhas.
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) {
				Endereco endereco = new Endereco(rs.getString("logradouro"),
						rs.getString("numero"), rs.getString("bairro"),
						rs.getString("id_cidade"), rs.getString("id_estado"),
						rs.getString("id_pais"));
				Administrador administrador = new Administrador(rs.getInt("id"),
						rs.getString("nome"), rs.getString("cpf"), rs.getString("sexo").charAt(0),
						rs.getString("telefone"), rs.getString("email"),
						endereco, rs.getString("data_de_nascimento"),
						rs.getString("usuario"), rs.getString("senha"), rs.getString("ativo").charAt(0));
				administradores.add(administrador);
			}
		} else {
			throw new AdministradorNaoCadastradoException();
		}
		ps.close();
		rs.close();
		return administradores;
	}
	
	// método para procurar apostador por cpf.
	public Administrador procurarPorCpf(String cpf) throws SQLException, AdministradorNaoCadastradoException, Exception{
		return listar(" and cpf='" + cpf + "'").get(0);
	}
	
	// método para procurar apostadores por nome.
	public ArrayList<Administrador> procurarPorNome(String nome) throws SQLException, AdministradorNaoCadastradoException, Exception{
		return listar(" and nome like '%" + nome + "%'");
	}
	
	// método para listar todos apostadores.
	public ArrayList<Administrador> listar() throws SQLException, AdministradorNaoCadastradoException, Exception{
		return listar("");
	}
	// método para atualizar apostador.
	public void atualizar(Administrador administrador) throws SQLException, AdministradorNaoCadastradoException, Exception {
		if(administrador != null){
			PreparedStatement ps = null;
			String sql = "";
			// instrução de update do apostador
			sql = "UPDATE " + NOME_TABELA + " SET cpf=?, nome=?, sexo=?, telefone=?, email=?, logradouro=?, numero=?, bairro=?,"
					+ " id_cidade=?, id_estado=?, id_pais=?, data_de_nascimento=?, usuario=?, senha=?, ativo=?"
					+ " WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, administrador.getCpf());
			ps.setString(2, administrador.getNome());
			ps.setString(3, String.valueOf(administrador.getSexo()));
			ps.setString(4, administrador.getTelefone());
			ps.setString(5, administrador.getEmail());
			ps.setString(6, administrador.getEndereco().getLogradouro());
			ps.setString(7, administrador.getEndereco().getNumero());
			ps.setString(8, administrador.getEndereco().getBairro());
			ps.setString(9, administrador.getEndereco().getCidade());
			ps.setString(10, administrador.getEndereco().getEstado());
			ps.setString(11, administrador.getEndereco().getPais());
			ps.setString(12, administrador.getDataDeNascimento());
			ps.setString(13, administrador.getUsuario());
			ps.setString(14, administrador.getSenha());
			ps.setString(15, String.valueOf(administrador.getAtivo()));
			ps.setLong(16, administrador.getId());
			Integer resultado = ps.executeUpdate();
			// se a atualizaçãp for efetuada com êxito o atributo resultado terá um valor diferente de 0, caso contrario levanta uma exception
			if (resultado == 0) throw new AdministradorNaoCadastradoException();
			// fecha a conexão
			ps.close();
		}
	}

	// método para deletar apostador.
	public void deletar(long id) throws SQLException, AdministradorNaoCadastradoException, Exception {
		PreparedStatement ps = null;
		String sql = "DELETE FROM " + NOME_TABELA + " WHERE id=?";
		ps = connection.prepareStatement(sql);
		ps.setLong(1, id);
		Integer resultado = ps.executeUpdate();
		ps.close();
		if(resultado == 0) throw new AdministradorNaoCadastradoException();
		
	}

	// método para verificar se existe apostador.
	public boolean existe(String cpf) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE cpf=?";
		boolean resposta = false;		
		ps = connection.prepareStatement(sql);
		ps.setString(1, cpf);
		rs = ps.executeQuery();
		if(rs.next()){
			resposta = true;
		}
		ps.close();
		rs.close();
		return resposta;
	}

}

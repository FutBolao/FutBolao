package br.com.futbolao.apostador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.futbolao.conexao.Conexao;
import br.com.futbolao.conexao.DataBase;
import br.com.futbolao.exception.ApostadorJaCadastradoException;
import br.com.futbolao.exception.ApostadorNaoCadastradoException;
import br.com.futbolao.util.Endereco;

public class RepositorioApostador implements IRepositorioApostador {
	
	private static final String NOME_TABELA = "apostador";
	private Connection connection;
	private int dataBase = 0;
	
	//construtor padrão, onde seleciona o banco mysql caso não seja executado o construtor com argumento
	public RepositorioApostador() throws Exception{
		this.connection = Conexao.getConexao(DataBase.MYSQL);
		this.dataBase = DataBase.MYSQL;
	}
	
	//construtor com argumento, que recebe como argumento o tipo de banco a ser executado.
	public RepositorioApostador(int dataBase) throws Exception{
		this.connection = Conexao.getConexao(dataBase);
		this.dataBase = dataBase;
	}
	
	//método cadastrar
	public void cadastrar(Apostador apostador) throws SQLException, ApostadorJaCadastradoException, Exception {
		// verifico se o objeto apostador está criado
		if (apostador != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "";
			// verifico se o apostador já existe na base, caso exista,
			// levanto a uma exception
			if (existe(apostador.getCpf()) == false) {
				sql = "INSERT INTO " + NOME_TABELA + " (cpf, nome, sexo, telefone, email, logradouro, numero, bairro, cidade, estado, "
						+ "pais ,data_de_nascimento, usuario, senha, clube) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				if (this.dataBase == DataBase.ORACLE) {
					ps = this.connection.prepareStatement(sql, new String[] { "id" });
				} else {
					ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				}
				ps.setString(1, apostador.getCpf());
				ps.setString(2, apostador.getNome());
				ps.setString(3, String.valueOf(apostador.getSexo()));
				ps.setString(4, apostador.getTelefone());
				ps.setString(5, apostador.getEmail());
				ps.setString(6, apostador.getEndereco().getLogradouro());
				ps.setString(7, apostador.getEndereco().getNumero());
				ps.setString(8, apostador.getEndereco().getBairro());
				ps.setString(9, apostador.getEndereco().getCidade());
				ps.setString(10, apostador.getEndereco().getEstado());
				ps.setString(11, apostador.getEndereco().getPais());
				ps.setString(12, apostador.getDataDeNascimento());
				ps.setString(13, apostador.getUsuario());
				ps.setString(14, apostador.getSenha());
				ps.setString(15, apostador.getClube());
				ps.execute();
				rs = ps.getGeneratedKeys();
				long id = 0;
				// Pegando o identificador gerado a partir do último insert
				while (rs.next()) {
					id = rs.getLong(1);
				}
				apostador.setId(id);
			} else {
				throw new ApostadorJaCadastradoException();
			}
			ps.close();
			rs.close();
		}
	}
	
	// método para listar apostadores.
	private ArrayList<Apostador> listar(String complemento) throws SQLException, ApostadorNaoCadastradoException, Exception {
		ArrayList<Apostador> apostadores = new ArrayList<Apostador>();
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
				Apostador apostador = new Apostador(rs.getInt("id"),
						rs.getString("nome"), rs.getString("cpf"), rs.getString("sexo").charAt(0),
						rs.getString("telefone"), rs.getString("email"),
						endereco, String.valueOf(rs.getDate("data_de_nascimento")),
						rs.getString("usuario"), rs.getString("senha"),
						rs.getString("clube"));
				apostadores.add(apostador);
			}
		} else {
			throw new ApostadorNaoCadastradoException();
		}
		ps.close();
		rs.close();
		return apostadores;
	}
	
	// método para procurar apostador por cpf.
	public Apostador procurarPorCpf(String cpf) throws SQLException, ApostadorNaoCadastradoException, Exception{
		return listar(" and cpf='" + cpf + "'").get(0);
	}
	
	// método para procurar apostadores por nome.
	public ArrayList<Apostador> procurarPorNome(String nome) throws SQLException, ApostadorNaoCadastradoException, Exception{
		return listar(" and cpf='%" + nome + "%'");
	}
	
	// método para listar apostadores.
	public ArrayList<Apostador> listar() throws SQLException, ApostadorNaoCadastradoException, Exception{
		return listar("");
	}
	
	// método para atualizar apostador.
	public void atualizar(Apostador apostador) throws SQLException, ApostadorNaoCadastradoException, Exception {
		if(apostador != null){
			PreparedStatement ps = null;
			String sql = "";
			// instrução de update do apostador
			sql = "UPDATE " + NOME_TABELA + " SET cpf=?, nome=?, sexo=?, telefone=?, email=?, logradouro=?, numero=?, bairro=?,"
					+ " id_cidade=?, id_estado=?, id_pais=?, data_de_nascimento=?, usuario=?, senha=?, clube=?"
					+ " WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, apostador.getCpf());
			ps.setString(2, apostador.getNome());
			ps.setString(3, String.valueOf(apostador.getSexo()));
			ps.setString(4, apostador.getTelefone());
			ps.setString(5, apostador.getEmail());
			ps.setString(6, apostador.getEndereco().getLogradouro());
			ps.setString(7, apostador.getEndereco().getNumero());
			ps.setString(8, apostador.getEndereco().getBairro());
			ps.setString(9, apostador.getEndereco().getCidade());
			ps.setString(10, apostador.getEndereco().getEstado());
			ps.setString(11, apostador.getEndereco().getPais());
			ps.setString(12, apostador.getDataDeNascimento());
			ps.setString(13, apostador.getUsuario());
			ps.setString(14, apostador.getSenha());
			ps.setString(15, apostador.getClube());
			ps.setLong(16, apostador.getId());
			Integer resultado = ps.executeUpdate();
			// se a atualizaçãp for efetuada com êxito o atributo resultado terá um valor diferente de 0, caso contrario levanta uma exception
			if (resultado == 0) throw new ApostadorNaoCadastradoException();
			// fecha a conexão
			ps.close();
		}
	}

	// método para deletar apostador.
	public void deletar(long id) throws SQLException, ApostadorNaoCadastradoException, Exception {
		PreparedStatement ps = null;
		String sql = "DELETE FROM " + NOME_TABELA + " WHERE id=?";
		ps = connection.prepareStatement(sql);
		ps.setLong(1, id);
		Integer resultado = ps.executeUpdate();
		ps.close();
		if(resultado == 0) throw new ApostadorNaoCadastradoException();
		
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

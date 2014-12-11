package br.com.futbolao.ganhadores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.futbolao.aposta.Aposta;
import br.com.futbolao.conexao.Conexao;
import br.com.futbolao.conexao.DataBase;
import br.com.futbolao.exception.ApostaNaoCadastradaException;
import br.com.futbolao.exception.ClubeJaCadastradoException;
import br.com.futbolao.exception.ClubeNaoCadastradoException;
import br.com.futbolao.exception.NaoHaGanhadoresNesseGrupoException;

public class RepositorioGanhador implements IRepositorioGanhador{
	
	private static final String NOME_TABELA = "ganhador";
	private static final String NOME_VIEW = "vw_ganhador";
	private static final String NOME_APOSTA = "aposta";
	private static final String NOME_RODADA = "rodada";
	private static final String NOME_VIEW_GRUPO = "vw_grupo";
	private Connection connection;
	private int dataBase = 0;
	
	//construtor padrão, onde seleciona o banco mysql caso não seja executado o construtor com argumento
	public RepositorioGanhador() throws Exception{
		this.connection = Conexao.getConexao(DataBase.MYSQL);
		this.dataBase = DataBase.MYSQL;
	}
	
	//construtor com argumento, que recebe como argumento o tipo de banco a ser executado.
	public RepositorioGanhador(int dataBase) throws Exception{
		this.connection = Conexao.getConexao(dataBase);
		this.dataBase = dataBase;
	}

	@Override
	public void verificar(long idGrupo) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Ganhador> listar(long idGrupo) throws SQLException, Exception {
		ArrayList<Ganhador> ganhadores = new ArrayList<Ganhador>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + NOME_VIEW + " ";
		if(idGrupo > 0){
			sql += "WHERE id_grupo=" + idGrupo;
		}
		sql += " ORDER BY nome_apostador";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		//se a consulta tiver algum resultado entro no loop e o executo adicionando o
		// resultado de cada linha ao array de clube, até que haja linhas.
		rs.first();
		if (rs.getRow() > 0) {
			rs.beforeFirst();
			while (rs.next()) {
			Ganhador ganhador = new Ganhador(rs.getLong("id_apostador"), rs.getString("nome_apostador"), rs.getLong("id_grupo"), 
					rs.getInt("pontos"), rs.getDouble("valor"));
			ganhadores.add(ganhador);
			}
		}else{
			throw new NaoHaGanhadoresNesseGrupoException();
		}
		ps.close();
		rs.close();
		return ganhadores;
	}
	
//	public void verificar(long idGrupo) throws SQLException, ApostaNaoCadastradaException, Exception {
//		ArrayList<Aposta> apostas = new ArrayList<Aposta>();
//		ArrayList<Ganhador> ganhador = new ArrayList<Ganhador>();
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		String sql = "";
//		sql = "SELECT * FROM " + NOME_APOSTA + " ";
//		sql += "WHERE id_aposta IS NOT NULL AND id_grupo";
//		sql += " ORDER BY id_aposta DESC";
//		ps = this.connection.prepareStatement(sql);
//		rs = ps.executeQuery();
//		rs.first();
//		if (rs.getRow() > 0) {
//			rs.beforeFirst();
//			while (rs.next()) {
//				pontos
//				Aposta aposta = new Aposta(rs.getLong("id_aposta"), rs.getLong("id_apostador"), rs.getString("nome_apostador"), rs.getLong("id_grupo"), 
//						rs.getDouble("valor"), 
//						rs.getString("data_aposta").substring(8, 10) + "/" + rs.getString("data_aposta").substring(5, 7) + "/"
//								   + rs.getString("data_aposta").substring(0, 4) + " " + rs.getString("data_aposta").substring(11, 16), 
//								   rs.getString("ativa").charAt(0));
//				apostas.add(aposta);
//				
//			}
//		}else{
//			throw new ApostaNaoCadastradaException();
//		}
//		ps.close();
//		rs.close();
//	}
//	
//	private ArrayList<Ganhador> listar(String complemento) throws SQLException, ClubeNaoCadastradoException, Exception {
//		ArrayList<Ganhador> ganhadors = new ArrayList<Ganhador>();
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		String sql = "";
//		sql = "SELECT * FROM " + NOME_TABELA + " ";
//		sql += "WHERE id IS NOT NULL";
//		sql += complemento;
//		sql += " ORDER BY nome";
//		ps = this.connection.prepareStatement(sql);
//		rs = ps.executeQuery();
//		//se a consulta tiver algum resultado entro no loop e o executo adicionando o
//		// resultado de cada linha ao array de clube, até que haja linhas.
//		rs.first();
//		if (rs.getRow() > 0) {
//			rs.beforeFirst();
//			while (rs.next()) {
//			Ganhador ganhador = new Ganhador(rs.getInt("id"), rs.getString("nome"), rs.getString("nome_completo"), rs.getString("sigla"), 
//					rs.getString("ativo").charAt(0), rs.getString("estado"), rs.getString("pais"));
//			ganhadors.add(ganhador);
//			}
//		}else{
//			throw new ClubeNaoCadastradoException();
//		}
//		ps.close();
//		rs.close();
//		return ganhadors;
//	}
//	
//	// método para listar clubes.
//	public ArrayList<Ganhador> procurarPorNome(String nome) throws SQLException, ClubeNaoCadastradoException, Exception{
//		return listar(" and nome like '%" + nome + "%'");
//	}
//	
//	// método para listar clubes.
//	public Ganhador procurarPorId(int id) throws SQLException, ClubeNaoCadastradoException, Exception{
//		return listar(" and id=" + id).get(0);
//	}
//	
//	// método para listar clubes.
//	public ArrayList<Ganhador> listar(char ativo) throws SQLException, ClubeNaoCadastradoException, Exception{
//		if (ativo == 'S' || ativo == 'N') {
//			return listar(" and ativo='" + ativo + "'");
//		} else {
//			return listar("");
//		}
//	}
//
//	// método para atualizar clube
//	public void atualizar(Ganhador ganhador) throws SQLException,	ClubeNaoCadastradoException, Exception {
//		if(ganhador != null){
//			PreparedStatement ps = null;
//			String sql = "";
//			// instrução de update do clube
//			sql = "UPDATE " + NOME_TABELA + " SET nome=?, nome_completo=?, sigla=?, ativo=?, estado=?, pais=? WHERE id=?;";
//			ps = this.connection.prepareStatement(sql);
//			ps.setString(1, ganhador.getNome());
//			ps.setString(2, ganhador.getNomeCompleto());
//			ps.setString(3, ganhador.getSigla());
//			ps.setString(4, String.valueOf(ganhador.getAtivo()));
//			ps.setString(5, ganhador.getEstado());
//			ps.setString(6, ganhador.getPais());
//			ps.setInt(7, ganhador.getId());
//			Integer resultado = ps.executeUpdate();
//			// se a atualizaçãp for efetuada com êxito o atributo resultado terá um valor diferente de 0, caso contrario levanta uma exception
//			if (resultado == 0) throw new ClubeNaoCadastradoException();
//			// fecha a conexão
//			ps.close();
//		}
//	}
//	
//	// método para deletar clube.
//	public void deletar(int id) throws SQLException, ClubeNaoCadastradoException, Exception {
//		PreparedStatement ps = null;
//		String sql = "DELETE FROM " + NOME_TABELA + " WHERE id=?";
//		ps = connection.prepareStatement(sql);
//		ps.setInt(1, id);
//		Integer resultado = ps.executeUpdate();
//		ps.close();
//		if(resultado == 0) throw new ClubeNaoCadastradoException();
//	}
//
//	// método para verificar se existe clube pelo nome.
//	public boolean existe(String nomeCompleto) throws SQLException, Exception {
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE nome_completo=?";
//		boolean resposta = false;		
//		ps = connection.prepareStatement(sql);
//		ps.setString(1, nomeCompleto);
//		rs = ps.executeQuery();
//		if(rs.next()){
//			resposta = true;
//		}
//		ps.close();
//		rs.close();
//		return resposta;
//	}
}

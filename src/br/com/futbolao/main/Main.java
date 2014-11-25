//teste
package br.com.futbolao.main;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.futbolao.apostador.Apostador;
import br.com.futbolao.clube.Clube;
import br.com.futbolao.competicao.Competicao;
import br.com.futbolao.exception.ApostadorJaCadastradoException;
import br.com.futbolao.exception.ApostadorNaoCadastradoException;
import br.com.futbolao.exception.ClubeJaCadastradoException;
import br.com.futbolao.exception.ClubeNaoCadastradoException;
import br.com.futbolao.exception.CompeticaoJaCadastradaException;
import br.com.futbolao.exception.CompeticaoNaoCadastradaException;
import br.com.futbolao.exception.CpfInvalidoException;
import br.com.futbolao.exception.NomeVazioException;
import br.com.futbolao.fachada.Fachada;
import br.com.futbolao.util.Endereco;

public class Main {
	
	private static Apostador apostador;
	private static Endereco endereco;
	private static Clube clube;
	private static Competicao competicao;

	public static void main(String[] args) {
		//teste ok google.
		Fachada fachada = null;
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Endereco endereco = new Endereco("Rua tal", "222", "Salgado", 1, 1, 1);
		Apostador apostador = new Apostador(0, "Anderson", "07597102445", 'M', "(81)9198-3432", "kaio@weblink.com.br", endereco, "1989-09-30", "usuario", "senha", "Palmeiras");
		
		try {
			fachada.cadastrarApostador(apostador);
		} catch (NomeVazioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApostadorJaCadastradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CpfInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

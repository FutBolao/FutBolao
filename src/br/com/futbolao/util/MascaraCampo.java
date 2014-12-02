package br.com.futbolao.util;

import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class MascaraCampo {
	
	private MaskFormatter cpf;
	private MaskFormatter cep;
	private MaskFormatter data;
	private MaskFormatter telefone;
	
	public MascaraCampo() {
		try {
			cpf = new MaskFormatter("###.###.###-##");
			cep = new MaskFormatter("#####-###");
			data = new MaskFormatter("##/##/####");
			telefone = new MaskFormatter("(##)#########");
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado ao tentar formatar o campo");
		}
	}

	public MaskFormatter getCpf() {
		return cpf;
	}
	
	public MaskFormatter getCep() {
		return cep;
	}
	
	public MaskFormatter getData() {
		return data;
	}
	
	public MaskFormatter getTelefone() {
		return telefone;
	}
}

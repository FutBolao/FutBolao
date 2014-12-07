package br.com.futbolao.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class FormataCampoPermiteApenasLetrasNumeros extends PlainDocument {
	
	private int quantidadeMaxima;
	
	public FormataCampoPermiteApenasLetrasNumeros(int quantidadeCaracteres) {
		super();
		if(quantidadeCaracteres<=0){
			throw new IllegalArgumentException("Especifique a quantidade de caracteres");
		}
		quantidadeMaxima = quantidadeCaracteres;
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if(str == null || getLength() == quantidadeMaxima){
			return;
		}
		int totalQuantia = (getLength()+str.length());
		if(totalQuantia <= quantidadeMaxima){
			super.insertString(offset, str, attr);
			return;
		}
		String nova = str.substring(0,getLength()-quantidadeMaxima);
		super.insertString(offset, nova, attr);
	}
}

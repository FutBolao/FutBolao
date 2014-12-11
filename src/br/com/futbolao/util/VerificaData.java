package br.com.futbolao.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VerificaData {
	
	public static boolean verifica(String vencimento, String atual) throws ParseException {  
	    
		boolean retorno;
	    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");  
	    Date dataVencimento = new Date(format.parse(vencimento).getTime());  
	    Date dataAtual = new Date(format.parse(atual).getTime());  
	      
	    if(dataVencimento.after(dataAtual)){  
	        retorno = true;
	    }else if(dataVencimento.before(dataAtual)){  
	    	retorno = false;
	    }else{  
	    	retorno = true;
	    }  
	    return retorno;
	}

}

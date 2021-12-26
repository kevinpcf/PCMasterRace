package model;

import java.io.Serializable;

public class PaymentBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	int codice;
	String utente;
	String numero;
	int CVV;
	String scadenza;
	
	public PaymentBean()
	{
		codice=0;
		utente="";
		numero="";
		CVV=0;
		scadenza = "";
	}

	public int getCodice() 
	{
		return codice;
	}

	public void setCodice(int code) 
	{
		codice = code;
	}

	public String getUtente() 
	{
		return utente;
	}

	public void setUtente(String utent) 
	{
		utente = utent;
	}

	public String getNumero() 
	{
		return numero;
	}

	public void setNumero(String numer) 
	{
		numero = numer;
	}

	public int getCVV() 
	{
		return CVV;
	}

	public void setCVV(int cVV) 
	{
		CVV = cVV;
	}

	public String getScadenza() 
	{
		return scadenza;
	}

	public void setScadenza(String scadenz) 
	{
		scadenza = scadenz;
	}
}

package model;

import java.io.Serializable;

public class AddressBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	int codice;
	String utente;
	String via;
	String civico;
	String CAP;
	String citta;
	String provincia;
	
	public AddressBean()
	{
		codice=0;
		utente="";
		via="";
		civico="";
		CAP="";
		citta="";
		provincia="";
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
	
	public String getVia() 
	{
		return via;
	}
	
	public void setVia(String vij)
	{
		via = vij;
	}
	
	public String getCivico() 
	{
		return civico;
	}
	
	public void setCivico(String civic)
	{
		civico = civic;
	}
	
	public String getCAP()
	{
		return CAP;
	}
	
	public void setCAP(String cAP) 
	{
		CAP = cAP;
	}
	
	public String getCitta() 
	{
		return citta;
	}
	
	public void setCitta(String citt) 
	{
		citta = citt;
	}
	
	public String getProvincia() 
	{
		return provincia;
	}
	
	public void setProvincia(String provinci) 
	{
		provincia = provinci;
	}
	
	public String toString()
	{
		return via+" "+civico+", "+citta+", "+provincia+", "+CAP;
	}
	
}


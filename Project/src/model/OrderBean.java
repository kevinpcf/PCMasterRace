package model;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class OrderBean implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	public OrderBean()
	{
		id = 0;
		data = new GregorianCalendar();
		username = "";
		codice = 0;
		quantita = 0;
		prezzo = 0;
		codiceIndirizzo=0;
		codicePagamento=0;
	}
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}

	public GregorianCalendar getData() 
	{
		return data;
	}

	public void setData(GregorianCalendar data) 
	{
		this.data = data;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public int getCodice() 
	{
		return codice;
	}

	public void setCodice(int codice) 
	{
		this.codice = codice;
	}
	
	public int getQuantita() 
	{
		return quantita;
	}
	
	public void setQuantita(int quantita) 
	{
		this.quantita = quantita;
	}

	public double getPrezzo() 
	{
		return prezzo;
	}

	public void setPrezzo(double prezzo) 
	{
		this.prezzo = prezzo;
	}
	
	public int getCodiceIndirizzo() 
	{
		return codiceIndirizzo;
	}

	public void setCodiceIndirizzo(int codiceIndirizzo) 
	{
		this.codiceIndirizzo = codiceIndirizzo;
	}

	public int getCodicePagamento() 
	{
		return codicePagamento;
	}

	public void setCodicePagamento(int codicePagamento) 
	{
		this.codicePagamento = codicePagamento;
	}
	
	int id;
	GregorianCalendar data;
	String username;
	int codice;
	int quantita;
	double prezzo;
	int codiceIndirizzo;
	int codicePagamento;
}

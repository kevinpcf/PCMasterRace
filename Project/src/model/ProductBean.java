package model;

import java.io.Serializable;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int codice;
	String nome;
	String descrizione;
	String categoria;
	double prezzo;
	int quantita;
	int IVA;
	String immagine;
	

	public ProductBean() 
	{
		codice = -1;
		nome = "";
		categoria ="";
		descrizione = "";
		quantita = 0;
		prezzo = 0;
		IVA = 0;
		immagine="";
	}

	public int getCodice() 
	{
		return codice;
	}

	public void setCodice(int code) 
	{
		codice = code;
	}

	public String getNome() 
	{
		return nome;
	}

	public void setNome(String name) 
	{
		nome = name;
	}
	
	public String getCategoria() 
	{
		return categoria;
	}

	public void setCategoria(String category) 
	{
		categoria = category;
	}

	public String getDescrizione() 
	{
		return descrizione;
	}

	public void setDescrizione(String description) 
	{
		descrizione = description;
	}

	public double getPrezzoBase() 
	{
		return prezzo;
	}

	public void setPrezzoBase(double price) 
	{
		prezzo = price;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantity) 
	{
		quantita = quantity;
	}

	public int getIVA() {
		return IVA;
	}

	public void setIVA(int iva) 
	{
		IVA = iva;
	}
	
	public double getPrezzo()
	{
		return prezzo+((prezzo/100)*IVA);
	}
	
	public String getImmagine() 
	{
		return immagine;
	}

	public void setImmagine(String img) 
	{
		immagine = img;
	}
	
	

	@Override
	public String toString() 
	{
		return nome + " (" + codice + "), " + prezzo + " " + quantita + ". " + descrizione;
	}

}

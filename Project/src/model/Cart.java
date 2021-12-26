package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<ProductBean> prodotti;
	
	public Cart() {
		prodotti = new ArrayList<ProductBean>();
	}
	
	public void aggiungiProdotto(ProductBean product) {
		product.setQuantita(1);
		boolean b = false;
		for(int i=0;i<prodotti.size();i++)
		{
			if(product.getCodice()==prodotti.get(i).getCodice())
			{
				prodotti.get(i).setQuantita(prodotti.get(i).getQuantita()+1);
				b=true;
			}
		}
		if(!b) prodotti.add(product);
	}
	
	public void cancellaProdotto(ProductBean product) 
	{
		for(int i=0; i<prodotti.size(); i++) 
		{
			if(prodotti.get(i).getCodice() == product.getCodice()) 
			{
				prodotti.remove(prodotti.get(i));
				break;
			}
		}
 	}
	
	public List<ProductBean> getProdotti() {
		return  prodotti;
	}
	
	public boolean isEmpty()
	{
		return prodotti.isEmpty();
	}
}

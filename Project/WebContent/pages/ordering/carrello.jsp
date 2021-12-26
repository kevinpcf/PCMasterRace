<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,model.ProductBean,model.Cart,java.text.DecimalFormat"%>
    
<%  Cart carrello = (Cart) session.getAttribute("cart");
	double tot = 0;
	String prezzo = null;
	String totale = null;
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="/ProgettoTSW/assets/style/carrello.css">
<title>Carrello</title>
<body style="background-color:white">
</head>

<body>
<%@include file="../components/header.jsp"%>
<%@include file="../components/menu.jsp"%>

<div id="totale">
<div id ="titolo">
	Carrello
</div>
<% if(carrello == null || carrello.isEmpty())
	{
	%> Nessun prodotto inserito nel carrello
<%  }
	else 
	{ %>
<form action="/ProgettoTSW/carthandling" method="get"> 
<input type="hidden" name="action" value="datiOrdine">

	
<%
	List<ProductBean>prodotti = carrello.getProdotti();
			for(int i=0;i<prodotti.size();i++)
			{
				tot +=prodotti.get(i).getPrezzo()*prodotti.get(i).getQuantita();
				totale = String.format("%.2f", tot);
				prezzo = String.format("%.2f", prodotti.get(i).getPrezzo());
%>
  <div id="prodotto_singolo">
  
  
    <div id="immagine">
    <a href="/ProgettoTSW/product?action=dettagli&codice=<%=prodotti.get(i).getCodice()%>"><img src="<%=prodotti.get(i).getImmagine()%>"></a>
    </div>
    
    <div id="dettagli">   
    
	    <div id="codice">
	    Codice:
		<%=prodotti.get(i).getCodice() %>
		</div>
		
		<div id="categoria">
		Categoria:
		<%=prodotti.get(i).getCategoria() %>
		</div>
		
		<div id="nome">
		Nome:
	    <%=prodotti.get(i).getNome() %>
	    </div>
	   
	    <div id="descrizione">
	    <%=prodotti.get(i).getDescrizione() %>
	    </div>
	   
	    <div id="prezzo">
	    Prezzo:
	    <%=prezzo+ "€"%>
	    </div>
	    
	    <div id="quantita">
	    Quantità:
		<input type="number" name="<%=prodotti.get(i).getCodice()%>" value="<%=prodotti.get(i).getQuantita()%>" min="1" required>
		
		<a href="/ProgettoTSW/carthandling?action=elimina&codice=<%=prodotti.get(i).getCodice()%>">Elimina</a>
	    </div>
	
	</div> 
	
	</div>  
	
<% 			}%>

      <div id="total">
      <h4>Totale: <%= totale+"€" %></h4>
      </div>
       
      <div id="ordina">
      <input type="submit" value="Ordina">
      </div>
      
      <br><br>
      <div id="svuota">
      <a href="/ProgettoTSW/carthandling?action=svuota" id="svuota">Svuota carrello</a><br>
      <% } %>
      </div>
      
</form>
</div>


   <%@include file="../components/footer.jsp" %>
</body>
</html>
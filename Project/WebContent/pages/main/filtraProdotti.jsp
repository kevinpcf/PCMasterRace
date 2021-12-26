<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,model.Cart,model.ProductBean" %>

<%
	ArrayList<ProductBean> prodotti = (ArrayList<ProductBean>) session.getAttribute("products");
    String prezzo = null;
    boolean admin = false;
    
	if(prodotti == null || (session.getAttribute("admin")!=null && (Boolean)session.getAttribute("admin"))) 
	{
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product");
		dispatcher.forward(request, response);
		return;
	}
	
	Cart carrello = (Cart) session.getAttribute("cart");
	
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>PC MasterRace</title>
<body style="background-color:white">

</head>


<body>
<%@include file="../components/header.jsp" %>
<%@include file="../components/menu.jsp" %>
<% 	
	String username = (String)session.getAttribute("utenteAttivo");
	
	if(username == null || username.isEmpty()){ 
%>
	
	<h3 align=right> <a href="/ProgettoTSW/pages/auth/login.html">Login</a>/<a href="/ProgettoTSW/pages/auth/registrazione.html">Registrazione</a></h3>
<% 	}
	else
  	{ 
%>
	   <h3 align=right>Ciao <%=username%> </h3>
	   <h3 align=right><a href=/ProgettoTSW/orderhandling>I miei ordini/</a><a href="/ProgettoTSW/loginhandling?action=logout">Logout</a></h3>
<% 	}%>


<center>
<h2> Prodotti</h2>
<table border=1>
	<tr>
		<th align=center>Codice</th>
		<th align=center>Categoria</th>
		<th align=center>Nome</th>
		<th align=center>Descrizione</th>
		<th align=center>Quantità</th>
		<th align=center>Prezzo</th>
		<th align=center></th>
	</tr>
	
<%
	if(prodotti!=null && prodotti.size()!=0)
	{
		ProductBean prodotto = null;
		for(int i=0;i<prodotti.size();i++)
		{
			prodotto = prodotti.get(i);
			prezzo = String.format(" %.2f", prodotto.getPrezzo());
%>
	<tr>
		<td align=center><%= prodotto.getCodice()%></td>
		<td align=center><%= prodotto.getCategoria()%></td>
		<td align=center><%= prodotto.getNome()%></td>
		<td align=center><%= prodotto.getDescrizione()%></td>
		<td align=center><%= prodotto.getQuantita() %></td>
		<td align=center><%= prezzo + "€" %></td>
		<td align=center><a href="/ProgettoTSW/product?action=dettagli&codice=<%=prodotto.getCodice()%>">Dettagli</a><br>
		<a href="/ProgettoTSW/product?action=carrello&codice=<%=prodotto.getCodice()%>">Aggiungi al carrello</a></td>
	</tr>
<%	}}
	else { %>
	<tr><td>Nessun prodotto trovato</td></tr>
	<% } %>
</table>
<br><br>

<%	int numProdotti = 0;
	if (carrello != null) 
	{ 
		numProdotti = carrello.getProdotti().size(); 
	}
%>

<a href="/ProgettoTSW/pages/ordering/carrello.jsp"> <h1>Carrello (<%=numProdotti%>) </h1> </a>

</center>
<%@include file="../components/footer.jsp" %>
</body>
</html>
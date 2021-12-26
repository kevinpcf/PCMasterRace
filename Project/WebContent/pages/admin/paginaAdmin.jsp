<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, model.ProductBean,model.UserBean"%>

<%
ArrayList<ProductBean> prodotti = (ArrayList<ProductBean>) session.getAttribute("products");
String prezzo = "";
if(prodotti == null) 
{
	response.sendRedirect("./product");	
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<body style="background-color:white">
<title>Pagina amministratore</title>
</head>

<body>
<h2 align=right>Ciao <%=((UserBean)session.getAttribute("utenteAttivo")).getUsername()%> </h2>
<h4 align=right><a href="/ProgettoTSW/adminhandling?action=ordini"> Visualizza ordini</a></h4>

<table border=1 align=center>
	<tr>
		<th align=center>Codice</th>
		<th align=center>Categoria</th>
		<th align=center>Nome</th>
		<th align=center>Descrizione</th>
		<th align=center>Quantità</th>
		<th align=center>Prezzo totale</th>
		<th align=center></th>
	</tr>
	
<%  for(int i=0;i<prodotti.size();i++)
	{
		ProductBean prodotto = prodotti.get(i);
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
		<a href="/ProgettoTSW/adminhandling?action=redirectModifica&codice=<%=prodotto.getCodice()%>">Modifica</a><br>
		<a href="/ProgettoTSW/adminhandling?action=elimina&codice=<%=prodotto.getCodice()%>">Elimina</a></td>
	</tr>
<% 
	}
%>
</table>
<br><br>
<center>
<a href="/ProgettoTSW/adminhandling?action=redirectInserisci">Inserisci un nuovo prodotto</a><br>
<a href="/ProgettoTSW/loginhandling?action=logout">Effettua il logout</a>
</center>

</body>
</html>
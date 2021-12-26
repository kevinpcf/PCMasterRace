<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,model.ProductBean,model.OrderBean"%>

<%
	ProductBean prodotto = (ProductBean)session.getAttribute("dettagliProdotto");
	OrderBean ordine = (OrderBean)session.getAttribute("dettagliOrdine");
	
	String data = ordine.getData().get(Calendar.DAY_OF_MONTH) +"/"+ordine.getData().get(Calendar.MONTH)+"/"+ordine.getData().get(Calendar.YEAR);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dettagli ordine</title>
<body style="background-color:white">
</head>

<body>
<%@include file="../components/header.jsp" %>
<link type="text/css" rel="stylesheet" href="/ProgettoTSW/assets/style/dettagliOrdine.css">
<center>

<% String prezzo = String.format("%.2f", ordine.getPrezzo()); %>

<h2>Dettagli ordine</h2><br>

<div id="corpo">
<h3>ID ordine: <%=ordine.getId() %></h3>
<h3>Data ordine: <%=data %></h3>
<h3>Nome prodotto: <%=prodotto.getNome()%></h3>
<h3>Descrizione prodotto: <%=prodotto.getDescrizione()%></h3>
<h3>Codice prodotto: <%=prodotto.getCodice()%></h3>
<h3>Quantità: <%=ordine.getQuantita()%></h3>
<h3>Costo totale: <%=prezzo+"€"%></h3>
</div>
<%if(session.getAttribute("admin") != null && (Boolean) (session.getAttribute("admin")))
	{%>
	<h3>Utente: <%=ordine.getUsername() %></h3>
	<%} %>

<br>
<%if(session.getAttribute("admin") != null && (Boolean) (session.getAttribute("admin"))) 
  { 
%>	<h4><a href="adminhandling?action=ordini">Torna alla pagina degli ordini effettuati</a></h4>
 <%}

else
  {%>
	<h4><a href="orderhandling">Torna alla pagina degli ordini effettuati</a></h4>
<%} %>

<a href="/ProgettoTSW/product"> Torna all'homepage</a>
</center>

<%@include file="../components/footer.jsp" %>
</body>
</html>
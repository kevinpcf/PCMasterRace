<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.ProductBean"%>
    
<% ProductBean prodotto = (ProductBean) request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="/ProgettoTSW/assets/style/dettagliProdotto.css">
<title>Dettagli prodotto</title>
</head>

<body>
<%@include file="../components/header.jsp" %>
<div id="dettagliProdotto">
	<div id="immagine">
	<img src="<%= prodotto.getImmagine()%>">
	</div>
	<% String prezzoTot = String.format("%.2f", prodotto.getPrezzo());
	   String prezzoBase = String.format("%.2f", prodotto.getPrezzoBase());
	%>
	
	<div id="info"> 
	<p id="nome"><%=prodotto.getNome()%></p> 
	<p id="prezzoTot">Prezzo totale: <%=prezzoTot + "€" %></p>
	<p id="prezzoBase">Prezzo base: <%=prezzoBase + "€" %><br>
		IVA: <%=prodotto.getIVA()%>%</p>
	
	<p id="dettagli">Dettagli:<br>
	Codice: <%=prodotto.getCodice() %><br>
	Tipologia: <%=prodotto.getCategoria() %><br>
	Descrizione: <%=prodotto.getDescrizione() %><br>
	Quantità: <%=prodotto.getQuantita() %><br>
	</p>
	<%	if(session.getAttribute("admin")==null || !(Boolean)session.getAttribute("admin"))
		{
	%><p id="aggiungiCarrello"><a href="/ProgettoTSW/product?action=carrello&codice=<%=prodotto.getCodice()%>" ><img src="/ProgettoTSW/assets/images/addToCart.png"></a></p><br>
		<%} %>
	</div>
</div>

	

<%@include file="../components/footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, model.OrderBean,model.UserBean" %>

<%
	List<OrderBean> ordini = (List<OrderBean>)session.getAttribute("ordini");
    String prezzo = null;
	
    if(ordini==null)
	{
		response.sendRedirect("./orderhandling");
		return;
	}
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<body style="background-color:white">
<title>Dettagli ordine</title>
<link type="text/css" rel="stylesheet" href="/ProgettoTSW/assets/style/ordini.css">
</head>

<body>
<%@include file="../components/header.jsp" %>
<h1 align=right><%=((UserBean)session.getAttribute("utenteAttivo")).getUsername()%></h1>

<div id="totale">
	<h2> Storico ordini</h2><br>

	<% if(ordini.size()!=0)
  		{		
	%>
	<%	
		for(int i=0; i<ordini.size(); i++)
		{
			String data = ordini.get(i).getData().get(Calendar.DAY_OF_MONTH) +"/"+(ordini.get(i).getData().get(Calendar.MONTH)+1)+"/"+ordini.get(i).getData().get(Calendar.YEAR);
			prezzo = String.format("%.2f" ,ordini.get(i).getPrezzo());
	%>		
	
	<div id="prodottosingolo">
		<div id="id">
			ID:
			<%= ordini.get(i).getId() %>
			</div>
			<div id="data">
			Data:
			<%= data %>
			</div>
			<div id="prezzo">
			Prezzo totale:
			<%= prezzo+"€" %>
			</div>
			<div id="quantita">
			Quantità:
			<%= ordini.get(i).getQuantita() %>
			</div>

		<div id="dettagli">
		<a href="/ProgettoTSW/orderhandling?action=dettagli&id=<%=ordini.get(i).getId()%>">Dettagli</a><br>
		</div>
		
		</div>
<%	}  %>	
<br><br>
<%}
	else if(ordini.isEmpty())
	{
%>		<h3 align=center>Nessun ordine effettuato</h3>
<%} %>

</div>
	<br><br>
	<div id="homepage">
		<a href="/ProgettoTSW/product"> Torna all'homepage</a>
	</div>
	
	<%@include file="../components/footer.jsp" %>
</body>
</html>
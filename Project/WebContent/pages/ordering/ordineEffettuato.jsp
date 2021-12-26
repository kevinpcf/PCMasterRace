<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,model.Cart,model.ProductBean"%>

<!DOCTYPE html>
<% Cart carrello = (Cart)request.getSession().getAttribute("cart"); 
   List<ProductBean> prodotti = carrello.getProdotti();
   int n=0;
   for(int i=0;i<prodotti.size();i++)
   {
	   n+=prodotti.get(i).getQuantita();
   }
   request.getSession().setAttribute("cart", new Cart()); 
   double totale = ((Double)request.getSession().getAttribute("totale"));
   String tot = String.format("%.2f",totale);
%>

<html>
<head>
<meta charset="UTF-8">
<body style="background-color:white">
<link type="text/css" rel="stylesheet" href="/ProgettoTSW/assets/style/stilePagine.css">
<title>Ordine effettuato</title>
</head>

<body>
<%@include file="../components/header.jsp" %>
<div id ="titolo">
<h1>Ordine effettuato correttamente</h1>
</div>
<br>

<div id="corpo">
<% if(n == 1)
	{%><h3>Hai ordinato 1 prodotto</h3>
	<%}else{%>
<h3>Hai ordinato <%=n%> prodotti</h3>
<%} %>
<br>
<h5>Totale ordini: <%=tot+"€"%></h5>
</div>

<div id="bottoni">
<a href=/ProgettoTSW/orderhandling>Visualizza tutti i tuoi ordini</a>

<a href="/ProgettoTSW/product"> Torna all'homepage </a>

</div>
<%@include file="../components/footer.jsp" %>
</body>
</html>
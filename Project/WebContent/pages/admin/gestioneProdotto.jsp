<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.ProductBean"%>

<%
ProductBean prodotto = (ProductBean)session.getAttribute("prodotto");
int codice=0;
String nome="";
String descrizione="";
String categoria="";
int quantita=0;
double prezzoTot=0;
double prezzoBase=0;
int IVA=0;

boolean b=false;
if(prodotto!=null)
{
	codice=prodotto.getCodice();
	nome=prodotto.getNome();
	descrizione=prodotto.getDescrizione();
	categoria=prodotto.getCategoria();
	prezzoTot=prodotto.getPrezzo();
	prezzoBase=prodotto.getPrezzoBase();
	IVA=prodotto.getIVA();
	quantita=prodotto.getQuantita();
	b=true;
}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestione prodotto</title>
<body style="background-color:white">
</head>

<body>
<center>
<br><br><br><br><br>

<%	if(b)
	{
%>
	<h1>Modifica prodotto</h1>
<%	}
	else
	{
%>
	<h1>Inserimento prodotto</h1>
<%	} %>

<form method="get" action="/ProgettoTSW/adminhandling">
<%	String azione = "";
	if(b) azione="modifica";
	else azione = "inserisci";
%>

<input type=hidden name=action value=<%=azione%>>
<input type=hidden name=codice value=<%=codice %>>
<h3>Nome: <input type=text name=nome value="<%=nome%>"> </h3>
<h3>Descrizione: <input type=text name=descrizione value="<%=descrizione%>"> </h3>
<h3>Categoria: <input type=text name=categoria value="<%=categoria%>"> </h3>
<h3>Prezzo base: <input type=text name=prezzo value=<%=prezzoBase%> placeholder=100,00 > </h3>
<h3>Quantità: <input type=number name=quantita value=<%=quantita%> placeholder=10 min=0> </h3>
<h3>IVA: <input type=number name=IVA value=<%=IVA%> placeholder=22 max=22> </h3>
<% if(b){%>
<h3>Prezzo totale: <%=prezzoTot%></h3>
<% }%>
<br>
<input type=submit value=Conferma><input type=reset value=Reset>
</form>
<br><br><h3><a href="/ProgettoTSW/product">Torna alla pagina di gestione</a></h3>
</center>
</body>
</html>
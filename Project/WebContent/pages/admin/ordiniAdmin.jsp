<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, model.OrderBean, model.OrderDS,model.UserBean"%>

<%
	List<OrderBean> ordini = (List<OrderBean>)session.getAttribute("ordini");
    String prezzo = null;
	
    if(ordini==null)
	{
		response.sendRedirect("./adminhandling?action=ordini");
		return;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<body style="background-color:white">
<title>Ordini Admin</title>
</head>

<body>

<h1 align=right><%=((UserBean)session.getAttribute("utenteAttivo")).getUsername()%></h1>
<h2 align=center>Storico ordini</h2>

<% if(ordini.size()!=0)
   {		
%>
<center>
<form action="/ProgettoTSW/adminhandling" method=get>
<input type=hidden name=action value=filtraOrdini>

<h3>Cerca per username:</h3>
<input type=text name=username><br><br>

<%  GregorianCalendar g = new GregorianCalendar();
	int i_mese = g.get(Calendar.MONTH) + 1;
	String s_mese =  String.valueOf(i_mese);
	String mese = "0"+s_mese;
	
   	String oggi = g.get(Calendar.YEAR)+"-"+ mese+"-"+g.get(Calendar.DAY_OF_MONTH); 
%>

<label> Cerca dalla data:
<input type=date name=start max= <%= oggi %> value="2021-01-01">
</label>

<label> alla data:
<input type=date name=end max= <%= oggi %> value=<%= oggi %>>
</label>

<br><br>
<input type=submit value=Cerca>
<input type=reset value=Reset>

</form><br><br>
</center>

<table border=1 align=center>
	<tr>
		<th>ID</th>
		<th>Utente</th>
		<th>Data</th>
		<th>Prezzo totale</th>
		<th>Quantità</th>
		<th></th>
	</tr>
<%	String data= "";
	for(int i=0; i<ordini.size(); i++)
	{
		data = ordini.get(i).getData().get(Calendar.DAY_OF_MONTH) +"/"+(ordini.get(i).getData().get(Calendar.MONTH)+1)+"/"+ordini.get(i).getData().get(Calendar.YEAR);
		prezzo = String.format("%.2f" ,ordini.get(i).getPrezzo());
%>		
	<tr>
		<td align=center><%= ordini.get(i).getId() %></td>
		<td align=center> <%= ordini.get(i).getUsername() %> </td>
		<td align=center><%= data %></td>
		<td align=center><%= prezzo+"€" %></td>
		<td align=center><%= ordini.get(i).getQuantita() %></td>
		<td align=center><a href="/ProgettoTSW/orderhandling?action=dettagli&id=<%=ordini.get(i).getId()%>">Dettagli</a><br></td>
	</tr>

<%	
	}  %>	
</table><br><br>
<%}
	else
	{
%>		<h3 align=center>Nessun ordine effettuato</h3>
<%} %>
<center>
<a href="/ProgettoTSW/product"> Torna all'homepage</a><br>
<a href="/ProgettoTSW/adminhandling?action=ordini">Ricarica pagina</a>
</center>
</body>
</html>
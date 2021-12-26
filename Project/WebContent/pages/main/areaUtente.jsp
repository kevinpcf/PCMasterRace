<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.UserBean,model.PaymentDS,model.PaymentBean,model.AddressDS,model.AddressBean, java.util.*"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="/ProgettoTSW/assets/style/areaUtente.css">
<title>Area utente</title>
</head>

<% UserBean user = (UserBean)session.getAttribute("utenteAttivo"); 
   PaymentDS modelPayment = new PaymentDS();
   ArrayList<PaymentBean> payments = (ArrayList<PaymentBean>)modelPayment.doRetrieveByUser(user.getUsername());
   AddressDS modelAddress = new AddressDS();
   ArrayList<AddressBean> addresses = (ArrayList<AddressBean>)modelAddress.doRetrieveByUser(user.getUsername());
%>

<body>
<%@include file="../components/header.jsp" %>
<div id=titolo>
Benvenuto <%=user.getUsername()%>
</div>
<br>

<div id ="corpo">
	<div id=datiUtente>
		<%=user.getNome() %>
		<%=user.getCognome() %>
	</div>

	<div id ="botton">
       <a href=/ProgettoTSW/orderhandling>I miei ordini</a>
       <a href="/ProgettoTSW/loginhandling?action=logout">Logout</a>
    </div>
</div>

<div id=indirizzi>
I tuoi indirizzi di spedizione:<br><br>
<%  for(int i=0;i<addresses.size();i++)
	{
%>
	Indirizzo <%=i+1%>: <%=addresses.get(i).toString() %> 
	<% if(addresses.size()>1){ %><a href="/ProgettoTSW/addrpayhandling?action=cancellaIndirizzo&codice=<%=addresses.get(i).getCodice()%>">Elimina</a><br>
	<%} %>
<%} %>
<br><a href="/ProgettoTSW/pages/auth/formIndirizzo.jsp">Inserisci un nuovo indirizzo di spedizione</a>
</div>
<br>

<div id=pagamenti>
I tuoi dati di pagamento:<br><br>
<%  for(int i=0;i<payments.size();i++)
	{
%>
	Metodo di pagamento <%=i+1%>: <%=payments.get(i).getNumero()%> 
	<%if(payments.size()>1){ %><a href="/ProgettoTSW/addrpayhandling?action=cancellaPagamento&codice=<%=payments.get(i).getCodice()%>">Elimina</a><br>
	<%} %>
<%} %>
<br><a href="/ProgettoTSW/pages/auth/formPagamento.jsp">Inserisci un nuovo metodo di pagamento</a>
</div><br><br>

<%@include file="../components/footer.jsp" %>
</body>
</html>
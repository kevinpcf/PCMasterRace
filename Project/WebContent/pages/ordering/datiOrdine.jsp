<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.UserBean,model.PaymentDS,model.PaymentBean,model.AddressDS,model.AddressBean, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="/ProgettoTSW/assets/style/datiOrdine.css">
<title>Ordine in corso</title>
</head>

<% 
	ArrayList<PaymentBean> payments = (ArrayList<PaymentBean>)session.getAttribute("pagamenti");
    ArrayList<AddressBean> addresses = (ArrayList<AddressBean>)session.getAttribute("indirizzi");
 %>

<body>
<%@include file="../components/header.jsp" %>

<center>
<form name="form" action="/ProgettoTSW/carthandling" method="get">
<input type="hidden" name="action" value="ordina">

<div id="titolo">Inserisci dati di spedizione e pagamento</div><br><br>

<div id="corpo">
	<div id=indirizzi>
	Scegli l'indirizzo di spedizione:<br>
	<%for(int i=0;i<addresses.size();i++) {%>
	<input type="radio" id="<%=addresses.get(i).getCodice()%>" value="<%=addresses.get(i).getCodice()%>" name="indirizzi" required><%=addresses.get(i).toString()%><br>
	<%} %>
	</div>
	<br>
	<div id=pagamenti>
	Scegli il metodo di pagamento:<br>
	<%for(int i=0;i<payments.size();i++) {%>
	<input type="radio" id="<%=payments.get(i).getCodice()%>" value="<%=payments.get(i).getCodice()%>" name="pagamenti" required><%=payments.get(i).getNumero()%><br>
	<%} %>
	</div>
<br>
<div id="modifica">	
	<a href="/ProgettoTSW/pages/main/areaUtente.jsp">Modifica dati</a>
</div>

</div>
<br>
<div id="ordina"><input type="submit" value="Effettua ordine"></div>
</form>
</center>

<div id="footer"><%@include file="../components/footer.jsp" %> </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="/ProgettoTSW/assets/style/header.css">
<title>Header</title>
</head>

<body>
<div id="header_container">

    <div id="bottonaus">
	<div id="scritta_logo">
	<a href="/ProgettoTSW/product"><img src="/ProgettoTSW/assets/images/scrittaLogo.png"></a>
	</div>
	
	<div id="immagine_logo">
	<a href="/ProgettoTSW/product"><img src="/ProgettoTSW/assets/images/immagineLogo.png"></a>
	</div>
	</div>
	
    <form action="/ProgettoTSW/product" method=get>
    <input type=hidden name="action"value="filtraRicerca">
	<div id="barra_ricerca">
	<input type=text name="ricerca" id="ricerca" placeholder="Cerca un prodotto..." width="500px">
	</div>
	</form>
	

	<div id="bottoniera">  
	    
		<div id="bottone_utente">
		<%if(session.getAttribute("utenteAttivo")!=null) {%>
		<a href="/ProgettoTSW/pages/main/areaUtente.jsp"><img src="/ProgettoTSW/assets/images/immagineUtente.png"></a> 
		<%} else{ %>
		<a href="/ProgettoTSW/pages/auth/login.html"><img src="/ProgettoTSW/assets/images/immagineUtente.png"></a>
		<%} %>
		</div>
		
		
		<div id="bottone_carrello">
		<a href="/ProgettoTSW/pages/ordering/carrello.jsp"><img src="/ProgettoTSW/assets/images/immagineCarrello.png"></a>
		</div>
		
		<div id="utente">
		<%if(session.getAttribute("utenteAttivo")==null) {%>
		<a href="/ProgettoTSW/pages/auth/login.html">Login</a>/<a href="/ProgettoTSW/pages/auth/registrazione.html">Registrati</a>
		<% }
		else{%>
		<a href="/ProgettoTSW/pages/main/areaUtente.jsp"><%=((UserBean)session.getAttribute("utenteAttivo")).getUsername() %></a> 
		<%} %>
		
		</div>
	</div>
	
</div>
</body>
</html>
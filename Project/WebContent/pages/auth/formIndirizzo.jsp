<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="/ProgettoTSW/assets/style/pagamentoIndirizzo.css">
<title>Nuovo indirizzo di spedizione</title>
</head>
<body>


<div id="immagine">
<br><br>
    <a href="/ProgettoTSW/product"><img src="/ProgettoTSW/assets/images/scrittaLogo.png"></a>
<br><br><br><br>
</div>


<script src="/ProgettoTSW/assets/script/formIndirizzo.js" type="text/javascript"></script>
<form name="frm1" action="/ProgettoTSW/addrpayhandling" method=get onsubmit="event.preventDefault(); validate(this)">

<div id="corpo">
Inserisci un nuovo indirizzo di spedizione:<br><br>
    <input type=hidden name=action value=nuovoIndirizzo>
    
    <div id="inputs">
    <input type=text name="via" id="via" placeholder=Via><br>
    <input type=text name="civico" id="civico" placeholder=Civico><br>
    <input type=text name="CAP" id="CAP" placeholder=CAP><br>
    <input type=text name="citta" id="citta" placeholder=Città><br>
    <input type=text name="provincia" id="provincia" placeholder=Provincia><br><br>
    </div>
    
    <input type=submit value="Inserisci" style="width:25%; height:40px; border-radius: 10px; font-family: Garamond; font-weight: bold; font-size: 0.8em;" onclick="verifica()">
</div>
</form>
</body>
</html>
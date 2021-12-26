<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="/ProgettoTSW/assets/style/pagamentoIndirizzo.css">
<title>Nuovo metodo di pagamento</title>
</head>

<body>


<div id="immagine">
<br><br>
    <a href="/ProgettoTSW/product"><img src="/ProgettoTSW/assets/images/scrittaLogo.png"></a>
<br><br><br><br>
</div>


<script src="/ProgettoTSW/assets/script/formPagamento.js" type="text/javascript"></script>
<form name="frm1" action="/ProgettoTSW/addrpayhandling" method=get onsubmit="event.preventDefault(); validate(this)">

<div id="corpo">
Inserisci un nuovo metodo di pagamento:<br><br>

    <input type=hidden name=action value=nuovoPagamento>
    
    <div id="inputs">
    <input type=text name="numeroCarta" id="numeroCarta" placeholder="Numero carta"><br>
    <input type=text name="CVV" id="CVV" placeholder=CVV ><br>
    <input type=text name="scadenza" id="scadenza" placeholder="Scadenza carta"><br><br>
    </div>
    
    <input type=submit value="Inserisci" style="width:25%; height:40px; border-radius: 10px; font-family: Garamond; font-weight: bold; font-size: 0.8em;" onclick="verifica()">
</form>

</div>
</body>
</html>
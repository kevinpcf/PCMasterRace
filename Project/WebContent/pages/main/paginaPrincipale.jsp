<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,model.Cart,model.ProductBean,model.UserBean" %>

<%
    ArrayList<ProductBean> prodotti = (ArrayList<ProductBean>) session.getAttribute("products");
    String prezzo = null;
    boolean admin = false;
    
    if(prodotti == null || (session.getAttribute("admin")!=null && (Boolean)session.getAttribute("admin"))) 
    {
        response.sendRedirect("/ProgettoTSW/product");
        return;
    }
    
    Cart carrello = (Cart) session.getAttribute("cart");
    
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>PC MasterRace</title>
<body style="background-color:white">
<script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>
<link type="text/css" rel="stylesheet" href="/ProgettoTSW/assets/style/paginaPrincipale.css">
</head>


<body>
<%@include file="../components/header.jsp" %>
<%@include file="../components/menu.jsp" %>

<section class="cover">
  <div class= "cover_caption_filter">
  </div>
   <div class="cover_caption reveal">
  <div class="cover_caption_copy">
    <h1> Scorri verso il basso</h1>
   <h2>per scoprire i nostri prodotti</h2>

</div>
</div>
</section>

<%     
    if(session.getAttribute("utenteAttivo")==null){ 
%>
    
<%     }
    else
      { 
        String username = ((UserBean)session.getAttribute("utenteAttivo")).getUsername();
%>
 	   
<%     }%>


<%     if(prodotti!=null && prodotti.size()!=0)
    {%>


<%
        ProductBean prodotto = null;
        for(int i=0;i<prodotti.size();i++)
        {
            prodotto = prodotti.get(i);
            prezzo = String.format(" %.2f", prodotto.getPrezzo());
%>   
    <div id ="corpo">
    
    <div id="immagine">
    <a href="/ProgettoTSW/product?action=dettagli&codice=<%=prodotto.getCodice()%>"><img src="<%= prodotto.getImmagine()%>"></a>
    </div>    

           
    <div id="prodotto">
        <%= prodotto.getNome()%><br>
        Prezzo: <%= prezzo + "€" %>
    </div>    
        
    <div id= "bottoni">
        <a href="/ProgettoTSW/product?action=carrello&codice=<%=prodotto.getCodice()%>"><img src="/ProgettoTSW/assets/images/addToCart.png"></a><br>    
    </div>        
    </div>
<%        }%>
<%} 
    else { %>
    Nessun prodotto trovato
    <% } %>


<%    int numProdotti = 0;
    if (carrello != null) 
    { 
        numProdotti = carrello.getProdotti().size(); 
    }
%>

<script> 
    ScrollReveal().reveal('.reveal', { distance: '50px', duration: 1500});
</script>


<%@include file="../components/footer.jsp" %>
</body>
</html>

function verifica()
{
    var fail = false;
    
    var username = $("#username").val();
    
    if ($("#nome").val() === "") {
        alert("Inserire un nome valido");
        $("#nome").focus();
        fail = true;
    } else if ($("#cognome").val() === "") {
        alert("Inserire un cognome valido");
        $("#cognome").focus();
        fail = true;
    } else if (username === "") {
        alert("Inserire un username valido");
        $("#username").focus();
        fail = true;
    } else if ($("#password").val() === "") {
        alert("Inserire una password valida");
        $("#password").focus();
        fail = true;
    }
    
    if (fail) return false;
        
        var call = $.ajax({
            url: '/ProgettoTSW/SigninInputHandling',
            type: 'POST',
            data: jQuery.param({"username": username}),
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            success: function (response) {
                console.log(response.esito);
                if (response.esito === "true") $("#frm1").submit();
                else {
                    $("#username").val("");
                    alert("L'username: '" + response.esito + "' non è valido.");
                }
            },
            error: function () {
                alert("Errore di sistema, impossibile completare l'operazione");
            },
            async: true
        });
}

function checkUsername(username)
{
	var usernameRE = /^[a-zA-Z0-9]{6,14}$/;
	if(username.value.match(usernameRE))
		return true;
	else{
		alert("Lo username deve essere lungo min 6 caratteri e max 14 e non può contenere caratteri speciali");
		username.focus();
		return false;
	}
}

function checkPassword(password)
{
	var passwordRE = /^[a-zA-Z0-9_*-+!?,:;.\xE0\xE8\xE9\xF9\xF2\xEC\x27]{6,12}/;
	if(password.value.match(passwordRE))
		return true;
	else{
		alert("La password deve essere di min 6 caratteri, max 12, formata da un numero ed un carattere speciale");
		password.focus();
		return false;
	}
}

function checkCAP(cap)
{
	var capRE = /^\d{5}$/;
	if(cap.value.match(capRE))
		return true;
	else{
		alert("Il CAP deve essere composto da 5 numeri e non sono ammesse lettere");
		cap.focus();
		return false;
	}
}

function checkProvincia(provincia)
{
	var provinciaRE = /^[A-Z0]{2}$/;
	if(provincia.value.match(provinciaRE))
		return true;
	else{
		alert("La provincia deve essere costituita da 2 lettere maiuscole");
		provincia.focus();
		return false;
	}
}

function checkCardNumber(cardNumber)
{
	var cc = /^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|(222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\d{3})\d{11}|62[0-9]{14})$/;
	if(cardNumber.value.match(cc))
		return true;
	else{
		alert("Numero di carta non valido");
		cardNumber.focus();
		return false;
	}
}

function checkCVV(cvv)
{
	var cvvRE = /^[0-9]{3}$/;
	if(cvv.value.match(cvvRE))
		return true;
	else{
		alert("Il CVV deve essere composto da tre cifre");
		cvv.focus();
		return false;
	}
}

function checkData(data)
{
	var dataRE = "^(0[1-9]|1[0-2]|[1-9])/(1[4-9]|[2-9][0-9]|20[1-9][1-9])$";
	if(data.value.match(dataRE))
		return true;
	else{
		alert("La data non è valida, es. 04/20");
		data.focus();
		return false;
	}
}

function validate(obj)
{
	var valid = true;
	
	//Username
	var username = document.getElementsByName("username")[0];
	if(!checkUsername(username) || username===""){
		valid = false;
		username.classList.add("error");
	}
	else
		username.classList.remove("error");
	
	//Password
	var password = document.getElementsByName("password")[0];
	if(!checkPassword(password)){
		valid = false;
		password.classList.add("error");
	}
	else
		password.classList.remove("error");
	
	//CAP
	var cap = document.getElementsByName("CAP")[0];
	if(!checkCAP(cap)){
		valid = false;
		cap.classList.add("error");
	}
	else
		cap.classList.remove("error");
	
	//Provincia
	var provincia = document.getElementsByName("provincia")[0];
	if(!checkProvincia(provincia)){
		valid = false;
		provincia.classList.add("error");
	}
	else
		provincia.classList.remove("error");
		
	//Numero carta
	var cardNumber = document.getElementsByName("numeroCarta")[0];
	if(!checkCardNumber(cardNumber)){
		valid = false;
		cardNumber.classList.add("error");
	}
	else
		cardNumber.classList.remove("error");
		
	//CVV
	var cvv = document.getElementsByName("CVV")[0];
	if(!checkCVV(cvv)){
		valid = false;
		cvv.classList.add("error");
	}
	else
		cvv.classList.remove("error");	
		
	//Data di scadenza carta
	var data = document.getElementsByName("scadenza")[0];
	if(!checkData(data)){
		valid = false;
		data.classList.add("error");
	}
	else
		data.classList.remove("error");	
		
	if(valid) obj.submit();	
}






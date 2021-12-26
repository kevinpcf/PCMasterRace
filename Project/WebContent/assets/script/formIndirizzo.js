function checkVia(via)
{
	var viaRE = /^.*?\s[N]{0,1}([-a-zA-Z0-9]+)\s*\w*$/;
	if(via.value.match(viaRE))
		return true;
	
	else{
		alert("Inserire una via valida");
		via.focus();
		return false;
	}
}

function checkCivico(civico)
{
	var civicoRE = /^\d{2,3}$/;
	if(civico.value.match(civicoRE))
		return true;
	
	else{
		alert("Il civico deve essere formato da 2 o 3 cifre");
		civico.focus();
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
	var provinciaRE = /[A-Z].*[A-Z]/;
	if(provincia.value.match(provinciaRE))
		return true;
	else{
		alert("La provincia deve essere costituita da 2 lettere maiuscole");
		provincia.focus();
		return false;
	}
}

function validate(obj)
{
	var valid = true;
	
	//Via
	var via = document.getElementsByName("via")[0];
	if(!checkVia(via)){
		valid = false;
		via.classList.add("error");
	}
	else
		via.classList.remove("error");
	
	//Civico
	var civico = document.getElementsByName("civico")[0];
	if(!checkCivico(civico)){
		valid = false;
		civico.classList.add("error");
	}
	else
		civico.classList.remove("error");
	
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
		
	
	if(valid) obj.submit();	
}
function checkCardNumber(cardNumber)
{
	var ccRE = /^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|(222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\d{3})\d{11}|62[0-9]{14})$/;
	
	if(cardNumber.value.match(ccRE))
		return true;
	
	else{
		alert("Il numero di carta deve essere di 16 cifre e di formato valido");
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
		alert("Il CVV deve essere composto da 3 cifre");
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
		alert("La data di scadenza deve essere nel formato: MM/YY es: 05/21");
		data.focus();
		return false;
	}
}

function validate(obj)
{
	var valid = true;
	
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
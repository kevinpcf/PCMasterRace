function verifica()
{
	if (document.getElementById("username").value=="")
	{
		alert("Inserire uno username valido");
		document.getElementById("username").focus();
		return(false);
	}

	if (document.getElementById("password").value=="")
	{
		alert("Inserire una password valida");
		document.getElementById("password").focus();
		return(false);
	}
	
	document.getElementById("frm1").submit();
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

function validate(obj)
{
	var valid = true;
	
	//Username
	var username = document.getElementsByName("username")[0];
	if(!checkUsername(username)){
		valid = false;
		username.classList.add("error");
	}
	else
		username.classList.remove("error");
	
	//PASSWORD
	var password = document.getElementsByName("password")[0];
	if(! checkPassword(password)){
		valid = false;
		password.classList.add("error");
	}
	else
		password.classList.remove("error");

	if(valid) obj.submit();	
}
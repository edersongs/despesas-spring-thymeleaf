/**
 * Funcao que converte os caracteres digitados para Maiusculo. Evento(s) onde
 * deve ser chamado simultaneamente. onkeypress="return toUpperCase(this);"
 * onkeyup="return toUpperCase(this);"
 */
function toUpperCase(obj) {
	obj.value = obj.value.toUpperCase();
	obj.style.textTransform = "uppercase";
	return true;
};

/**
 * Funcao que aceita somente numeros. Evento(s) onde deve ser chamado
 * onkeypress="return onlyNumber(event);"
 */
function onlyNumber(event) {
    var tecla = (window.event) ? event.keyCode : event.which;
    var ctrl = event.ctrlKey;
    if ((tecla > 47 && tecla < 58)) {
    	return true;
    } else {
    	if (tecla == 45) {
    		return true;
    	} else if (ctrl && tecla == 118) {
    		return true;
    	} else if (ctrl && tecla == 99) {
    		return true;
    	} else if (tecla != 8 && tecla != 9 && tecla != 11 && tecla != 46 && tecla != 13 &&  
    			   tecla != 37 && tecla != 38 && tecla != 39 && tecla != 40 && tecla != 0) {
    		return false;
    	} else {
    		return true;
    	}	
    }
};

function convertCurrency(campo, tecla) {
	var separador_milhar = '.';
	var separador_decimal = ',';
	var sep = 0;
	var key = '';
	var i = j = 0;
	var len = len2 = 0;
	var strCheck = '0123456789';
	var aux = aux2 = '';
	var whichCode = (window.Event) ? tecla.which : tecla.keyCode;
	
	if (whichCode == 13) return false; // Tecla Enter
	if (whichCode == 8) return true; // Tecla Delete
	key = String.fromCharCode(whichCode); // Pegando o valor digitado
	if (strCheck.indexOf(key) == -1) return false; // Valor inválido (não
													// inteiro)
	len = campo.value.length;
	for(i = 0; i < len; i++)
	if ((campo.value.charAt(i) != '0') && (campo.value.charAt(i) != separador_decimal)) break;
	aux = '';
	for(; i < len; i++)
	if (strCheck.indexOf(campo.value.charAt(i))!=-1) aux += campo.value.charAt(i);
	aux += key;
	len = aux.length;
	if (len == 0) campo.value = '';
	if (len == 1) campo.value = '0'+ separador_decimal + '0' + aux;
	if (len == 2) campo.value = '0'+ separador_decimal + aux;

	if (len > 2) {
		aux2 = '';

		for (j = 0, i = len - 3; i >= 0; i--) {
			if (j == 3) {
				aux2 += separador_milhar;
				j = 0;
			}
			aux2 += aux.charAt(i);
			j++;
		}

		campo.value = '';
		len2 = aux2.length;
		for (i = len2 - 1; i >= 0; i--)
		campo.value += aux2.charAt(i);
		campo.value += separador_decimal + aux.substr(len - 2, len);
	}
	return false;
};

/**
 * Verifica se a tecla pressionada foi o ENTER
 * 
 * @param evt
 *            evento disparado
 * @returns {Boolean} 'true' para SIM e 'false' para NAO
 */
function isEnter(evt) {
	var keyCode = getKeyCode(evt);
	
	if (keyCode == 13) {
		return true;
	}
	return false;
};

/**
 * Verifica se a tecla pressionada foi o TAB
 * 
 * @param evt
 *            evento disparado
 * @returns {Boolean} 'true' para SIM e 'false' para NAO
 */
function isTab(evt) {
	var keyCode = getKeyCode(evt);
	
	if (keyCode == 9) {
		return true;
	}
	return false;
};

/**
 * Verifica se a tecla pressionada foi o ENTER ou TAB
 * 
 * @param evt
 *            evento disparado
 * @returns {Boolean} 'true' para SIM e 'false' para NAO
 */
function isEnterOrTab(evt) {
	var keyCode = getKeyCode(evt);
	
	if (keyCode == 13 || keyCode == 9) {
		return true;
	}
	return false;
};

/**************************************************************************
Fun��o para simular um Tab quando for pressionado a tecla Enter
Exemplo: onKeyDown="TABEnter(event)"
**************************************************************************/
function TABEnter(evt) {
	var keyCode = getKeyCode(evt);
	if (keyCode == 13) {
		evt.keyCode = 9;
	}
}

/**
 * Obt�m o codigo da tecla pressionada
 * 
 * @param event
 *            evento disparado
 * @returns o codigo da tecla pressionada
 */
function getKeyCode(event) {
	var keyCode = (event.which) ? event.which : event.keyCode;
	return keyCode;
};

/**
 * Retorna apenas os d�gitos do valor enviado.
 * 
 */
function onlyDigite(value){
	return value.replace(/\D/g,"");
};

function formatReal(numero) {
	   
	var tmp = numero + '';
    var neg = false;

    if (tmp - (Math.round(numero)) == 0) {
        tmp = tmp + '00';        
    }

    if (tmp.indexOf(".")) {
        tmp = tmp.replace(".", "");
    }

    if (tmp.indexOf("-") == 0) {
        neg = true;
        tmp = tmp.replace("-", "");
    }

    if (tmp.length == 1) tmp = "0" + tmp

    tmp = tmp.replace(/([0-9]{2})$/g, ",$1");

    if (tmp.length > 6)
        tmp = tmp.replace(/([0-9]{3}),([0-9]{2}$)/g, ".$1,$2");

    if (tmp.length > 9)
        tmp = tmp.replace(/([0-9]{3}).([0-9]{3}),([0-9]{2}$)/g, ".$1.$2,$3");

    if (tmp.length = 12)
        tmp = tmp.replace(/([0-9]{3}).([0-9]{3}).([0-9]{3}),([0-9]{2}$)/g, ".$1.$2.$3,$4");

    if (tmp.length > 12)
        tmp = tmp.replace(/([0-9]{3}).([0-9]{3}).([0-9]{3}).([0-9]{3}),([0-9]{2}$)/g, ".$1.$2.$3.$4,$5");

    if (tmp.indexOf(".") == 0) tmp = tmp.replace(".", "");
    if (tmp.indexOf(",") == 0) tmp = tmp.replace(",", "0,");

    return (neg ? '-' + tmp : tmp);
}

function noAccent(e,args) {   
	if (document.all){var evt=event.keyCode;} // caso seja IE
    else{var evt = e.charCode;} 	// do contrário deve ser Mozilla
    var valid_chars = '0123456789abcdefghijlmnopqrstuvxzwykABCDEFGHIJLMNOPQRSTUVXZWYK-_/@.'+args;      // criando a lista de teclas permitidas
    var chr= String.fromCharCode(evt);      // pegando a tecla digitada
    if (valid_chars.indexOf(chr)>-1 ){return true;} // se a tecla estiver na lista de permissão permite-a
    // para permitir teclas como <BACKSPACE> adicionamos uma permissão para 
    // códigos de tecla menores que 09 por exemplo (geralmente uso menores que 20)
    if (valid_chars.indexOf(chr)>-1 || evt < 9){return true;} 
	if (valid_chars.indexOf(chr)>30 || evt <35){return true;} //permite a tecla espaço
    return false;   // do contrário nega
};

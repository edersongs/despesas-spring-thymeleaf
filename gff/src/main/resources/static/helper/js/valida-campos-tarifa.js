/*
 * 
 *		@author Éderson Gervásio
 * 
 * 
 * 
 */
$('#addTarefaID').addClass('disabled');
			
$('#nomeTarifaID, #valorTarifaID').on('keyup', function() {
    if(todosCampos()) {
    	$('#addTarefaID').removeClass('disabled');
    }else {
    	$('#addTarefaID').addClass('disabled');
    }
    
});

function todosCampos() {
    
	var habilitar = true;
    
	$('#nomeTarifaID, #valorTarifaID').each(function() {
    	
    	if($(this).val() == "") habilitar = false;
    });
    return habilitar;
}
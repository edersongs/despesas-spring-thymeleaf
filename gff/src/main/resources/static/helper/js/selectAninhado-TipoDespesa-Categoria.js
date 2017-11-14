/*
 * 
 *		@author Éderson Gervásio
 * 
 * 
 * 
 */

function onSelectClick() {
	var tipoDespesaSelecionada = $('#tipoDespesaID').val();
       if(tipoDespesaSelecionada != null && tipoDespesaSelecionada != "") {
    	   $.ajax({
    		   url: '/despesas/pesquisarCategoriaDespesa?codigoTipoDespesa='+tipoDespesaSelecionada,
    		   method: 'GET',
    		   dataType: 'json',
    		   success: pesquisaRealizadaComSucesso
    	   });
       } else {
    	   $('#categoriaDespesaID').find('option').remove();
       }
}

function pesquisaRealizadaComSucesso(dados) {
	if($.isArray(dados)) {
		$('#categoriaDespesaID').find('option').remove();
        $.each(dados, function(index, value) {
            $('#categoriaDespesaID').append('<option value=' + value.codigoCategoria + '>' + value.nome + '</option>');
        });
    }
}

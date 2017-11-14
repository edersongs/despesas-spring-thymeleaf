/*
 * 
 *		@author Éderson Gervásio
 * 
 * 
 * 
 */

document.getElementById("pesquisaNewCatID").addEventListener("click", onClickBotao);

function onClickBotao() {
    $.ajax({
        url: '/despesas/listaTiposDespesas',
        method: "GET",
        dataType: "json",
        success: pesquisaRealizadaComSucess
    });
}

function pesquisaRealizadaComSucess(dados) {
	if ($.isArray(dados)) {
		$('#tipoDespesaModalID').find('option').remove();
        $.each(dados, function(index, value) {
            $('#tipoDespesaModalID').append('<option value=' + value.codigoTipoDespesa + '>' + value.nome + '</option>');
        });
    }
}

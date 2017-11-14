// Formatação default para todas as telas que usa componente de Calendário
$('.datepicker').datepicker({
	format: 'dd/mm/yyyy',
	language: 'pt-BR',
	//weekStart: 0,  // Deixa escolher somente data e mês >= a data atual.
	//startDate:'0d', // Deixa escolher somente data e mês >= a data atual.
	todayHighlight: true, // Marca no componente o dia atual
	autoclose: true
});

//Formatação para o componente data vencimento do cartão
$('.datepickerVencimentoCartao').datepicker({
	format: 'dd/mm/yyyy',
	language: 'pt-BR',
	todayHighlight: true, // Marca no componente o dia atual
	autoclose: true
});

// Formatação para o compoente de data de validade do cartão
$('#datepickerMesAno').datepicker({
	minViewMode: 'months',
    format: 'MM/yyyy',
    language: 'pt-BR',
    autoclose: true
});

//Formatação para o compoente de data de vencimento da despesa
$('.datepickerDataVencimentoDespesa').datepicker({
	format: 'dd/mm/yyyy',
	language: 'pt-BR',
	weekStart: 0,  // Deixa escolher somente data e mês >= a data atual.
	startDate:'0d', // Deixa escolher somente data e mês >= a data atual.
	todayHighlight: true, // Marca no componente o dia atual
	autoclose: true
});

//Formatação para o compoente de data pesquisa Despesa
$('#datepickerMes').datepicker({
	minViewMode: 'months',
    format: 'dd/mm/yyyy',
    language: 'pt-BR',
    autoclose: true
});

$('#datepickerMes').datepicker().on('change',function(e){
	$('#formFilterDespesaID').submit();
})




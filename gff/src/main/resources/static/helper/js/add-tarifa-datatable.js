/*
 * 
 *		@author Éderson Gervásio
 * 
 * 
 * 
 */
var Gff = Gff || {};

Gff.TabelaTarifa = (function() {
	
	function TabelaTarifa() {
		
		this.inputTipoTarifa = $('#tipoTarifaID');
		this.inputNomeTarifa = $('#nomeTarifaID');
		this.inputValorTarifa = $('#valorTarifaID');
		this.tabelaTarifa = $('#tabelaTarifas');
		this.botaoAdicionar = $('#addTarefaID');
		
	}
	
	TabelaTarifa.prototype.iniciar = function() { 
		
		this.botaoAdicionar.on("click", onClickBotaoAdicionar.bind(this));
		
	}
	
	function onClickBotaoAdicionar() {
		
		var tipoTarifa = this.inputTipoTarifa.val();
		var nomeTarifa = this.inputNomeTarifa.val().trim();
		var valorTarifa = this.inputValorTarifa.val().replace('R$', '').trim();
		
		var resposta = $.ajax({
		        url: 'addTarifa',
		        method: "POST",
		        contentType: 'application/json',
		        data: JSON.stringify({nome: nomeTarifa, valor: valorTarifa.replace(",", "."), tipoTarifa: tipoTarifa}),
		        success: console.log('ok')
		    });
		
		
		resposta.done(onPopulaTabela.bind(this));
	}
	
	function onPopulaTabela(html) {
		
		console.log(html);
		this.tabelaTarifa.html(html);
		
	}
	
	return TabelaTarifa;
	
}());


$(function() {
	var tabelaTarifa = new Gff.TabelaTarifa();
	tabelaTarifa.iniciar();
});

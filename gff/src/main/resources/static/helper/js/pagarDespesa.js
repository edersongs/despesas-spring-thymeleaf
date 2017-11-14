var Gff = Gff || {};

Gff.PagamentoDespesaRapido = (function() {
	
	function PagamentoDespesaRapido() {
		this.modal = $('.modalPagamentoDespesa');
		this.botaoSalvar = this.modal.find('.js-modal-pagamento-despesa-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputValorPagamento = $('#valor');
		this.containerMensagemErro = $('.js-mensagem-pagamento-despesa');
		this.codigoDespesa = this.modal.attr('id');
	}
	
	PagamentoDespesaRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.botaoSalvar.on('click', onBotaoFazerPagamentoClick.bind(this));
	}
	
	function onModalShow() {
		this.inputValorPagamento.focus();
	}
	
	function onModalClose() {
		this.inputValorPagamento.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoFazerPagamentoClick() {
		var valorPagamento = this.inputValorPagamento.val().trim();
		var valorConta = $('input[name=conta]:checked', '#form').val();
		
		console.log(valorPagamento);
		
		/*$.ajax({
			//url: '/despesas/fazerPagamento',
			url: '/despesas/fazerPagamento?valorPagamento='+valorPagamento+"?codigoDespesa="+this.codigoDespesa+"?codigoConta="+valorConta,
			method: 'POST',
			dataType: 'json',
			error: onErroRealizarPagamento.bind(this),
			success: onPagamentoRealizado.bind(this)
		});*/
	}
	
	function onErroRealizarPagamento(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onPagamentoRealizado(valorDespesa) {
		this.modal.modal('hide');
	}
	
	return PagamentoDespesaRapido;
	
}());

$(function() {
	var pagamentoDespesaRapido = new Gff.PagamentoDespesaRapido();
	pagamentoDespesaRapido.iniciar();
});
var Gff = Gff || {};

Gff.TipoDespesaCadastroRapido = (function() {
	
	function TipoDespesaCadastroRapido() {
		this.modal = $('#modalDespesaID');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-tipo-despesa-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputTipoDespesa = $('#nome');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-tipo-despesa');
	}
	
	TipoDespesaCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputTipoDespesa.focus();
	}
	
	function onModalClose() {
		this.inputTipoDespesa.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nomeTipoDespesa = this.inputTipoDespesa.val().trim();
		$.ajax({
			url: '/despesas/salvarTipoDespesa',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeTipoDespesa}),
			error: onErroSalvandoTipoDespesa.bind(this),
			success: onTipoDespesaSalvo.bind(this)
		});
	}
	
	function onErroSalvandoTipoDespesa(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onTipoDespesaSalvo(tipoDespesa) {
		var comboTipoDespesa = $('#tipoDespesaID');
		comboTipoDespesa.append('<option value=' + tipoDespesa.codigoTipoDespesa + '>' + tipoDespesa.nome + '</option>');
		comboTipoDespesa.val(tipoDespesa.codigoTipoDespesa);
		this.modal.modal('hide');
	}
	
	return TipoDespesaCadastroRapido;
	
}());

$(function() {
	var tipoDespesaCadastroRapido = new Gff.TipoDespesaCadastroRapido();
	tipoDespesaCadastroRapido.iniciar();
});
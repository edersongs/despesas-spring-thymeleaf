var Gff = Gff || {};

Gff.CategoriaDespesaCadastroRapido = (function() {
	
	function CategoriaDespesaCadastroRapido() {
		this.modal = $('#modalCategoriaDespesaID');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-tipo-despesa-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputCategoriaDespesa = $('#nomeModalID');
		this.inputTipoDespesa = $('#tipoDespesaModalID');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-tipo-despesa');
	}
	
	CategoriaDespesaCadastroRapido.prototype.iniciar = function() {
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
		var nomeCategoriaDespesa = this.inputCategoriaDespesa.val().trim();
		var tipoDespesa = this.inputTipoDespesa.val().trim();
		$.ajax({
			url: '/despesas/salvarCategoriaDespesa',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nomeCategoria: nomeCategoriaDespesa, codigoTipoDespesa: tipoDespesa}),
			error: onErroSalvandoCategoriaDespesa.bind(this),
			success: onCategoriaDespesaSalvo.bind(this)
		});
	}
	
	function onErroSalvandoCategoriaDespesa(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onCategoriaDespesaSalvo(categoriaDespesa) {
		var comboCategoriaDespesa = $('#categoriaDespesaID');
		comboCategoriaDespesa.append('<option value=' + categoriaDespesa.codigoCategoria + '>' + categoriaDespesa.nome + '</option>');
		comboCategoriaDespesa.val(categoriaDespesa.codigoCategoria);
		this.modal.modal('hide');
	}
	
	return CategoriaDespesaCadastroRapido;
	
}());

$(function() {
	var categoriaDespesaCadastroRapido = new Gff.CategoriaDespesaCadastroRapido();
	categoriaDespesaCadastroRapido.iniciar();
});
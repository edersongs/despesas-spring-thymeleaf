/*
 * 
 *		@author Éderson Gervásio
 * 
 * 
 * 
 */
	
	
	
	
	$('.selectCard').change(function() {
		var selectCartao = $('#selectCartaoID').val().trim();
		var inputDataVencimento = $('#datVencimentoID').val().trim();
		
		if (selectCartao != '') {
			
			$('#datVencimentoID').attr('disabled','disabled');
			
		}else {
			
			$('#datVencimentoID').removeAttr('disabled'); 
		}
		
	});
	
	/*$('#salveButtonID').click(function() {
		var selectCartao = $('#selectCartaoID').val().trim();
		var inputDataVencimento = $('#datVencimentoID').val().trim();
		var inputQuantidadeParcelas = $('#qdeParcelasID').val().trim();
		
		if (selectCartao == '' && inputDataVencimento == '' && inputQuantidadeParcelas != '') {
			var mensagemErro = 'Informe a data de vencimento para essa despesa parcelada!';
			alert(mensagemErro);
		}
	});*/
	
	
	
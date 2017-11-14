/**
 * 
 */
package gervasio.system.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gervasio.system.model.Despesa;
import gervasio.system.model.TipoDespesa;
import gervasio.system.model.enuns.StatusPagamento;
import gervasio.system.repository.DespesaRepository;
import gervasio.system.repository.TipoDespesaRepository;
import gervasio.system.service.exception.ContaServiceException;
import gervasio.system.util.DateHelper;

/**
 * @author Éderson Gervásio
 *
 */
@Service
public class DespesaService {

	@Autowired private DespesaRepository despesaRepository;
	@Autowired private TipoDespesaRepository tipoDespesaRepository;
	@Autowired private ContaService contaService;
	
	private void salvarDespesa(Despesa despesa) {
		
		despesaRepository.save(despesa);
	}
	
	public void lancarDespesa(Despesa despesa) {
		
		int atualOuSeguinte = iniciarLancamentoMesAtualOuSeguinte(despesa);

		if (despesa.isParcelado() || despesa.isFixa()) {
			
			while (despesa.getParcelasPagas() < despesa.getQuantidadeParcelasOrFixas()) {
				despesa.calcularParcelasRestantes();
				criarDespesaFutura(despesa, despesa.getValor(), DateHelper.mesSeguinte(new Date(), atualOuSeguinte), StatusPagamento.A_PAGAR);
				atualOuSeguinte++;
			}
			
		}else {
			despesa.setDataDespesa(DateHelper.mesSeguinte(new Date(), atualOuSeguinte));
			despesa.setDataVencimento(calcularDataVencimento(despesa, despesa.getDataVencimento()));
			salvarDespesa(despesa);
		}
	}

	public TipoDespesa salvarTipoDespesa(TipoDespesa tipoDespesa) {
		
		return tipoDespesaRepository.save(tipoDespesa);
	}
	
	public List<BigDecimal> valoresDespesas(List<Despesa> despesas) {
		
		List<BigDecimal> valores = new ArrayList<BigDecimal>();
		despesas.forEach(d -> valores.add(d.getValor()));
		
		return valores; 
	}
	
	public void realizarPagamentoDespesa(BigDecimal valorPagamento, Long codigoDespesa, int conta) throws ContaServiceException {
		
		Despesa despesaSelecionada = despesaRepository.findOne(codigoDespesa);
		verificarPagamentoEAlterarStatusSeNecessario(valorPagamento, despesaSelecionada, conta);
		
	}
	
	public void realizarPagamentoCartaoCredito(BigDecimal valorPagamento, Long codigoCartao, int conta) throws ContaServiceException {
		
		//Despesa despesaSelecionada = despesaRepository.findDespesasCartaoCredito(codigoCartao);
		//verificarPagamentoEAlterarStatusSeNecessario(valorPagamento, despesaSelecionada, conta);
		
	}

	private void verificarPagamentoEAlterarStatusSeNecessario(BigDecimal valorPagamento, Despesa despesaSelecionada, int conta) throws ContaServiceException {
		
		if (!valorPagamento.equals(BigDecimal.ZERO) && valorPagamento.compareTo(despesaSelecionada.getValor()) < 0) {
			
			BigDecimal valorRestante = despesaSelecionada.getValor().subtract(valorPagamento);
			despesaSelecionada.setValor(valorPagamento);
			despesaSelecionada.setStatus(StatusPagamento.PARCIAL);
			despesaSelecionada.setQuantidadeParcelas(0);
			despesaSelecionada.setParcelasPagas(0);
			despesaSelecionada.setFixa(false);
			despesaSelecionada.setDataVencimento(null);
			despesaSelecionada.getObservacao().concat(Despesa.MENSAGEM_INF_PAGAMENTO_PARCIAL);
			
			criarDespesaFutura(despesaSelecionada, 
					valorRestante, 
					DateHelper.mesSeguinte(despesaSelecionada.getDataDespesa(), 1), 
					StatusPagamento.A_PAGAR);
			
		}else {
			
			despesaSelecionada.setStatus(StatusPagamento.PAGO);
			salvarDespesa(despesaSelecionada);
		}
		
		contaService.debitar(new Long(conta), despesaSelecionada.getValor()); // falta definir a conta
		
	}

	private void criarDespesaFutura(Despesa despesa, BigDecimal valorDaDespesa, Date dataParaDespesa, StatusPagamento statusPagamento) {
		
		Despesa despesaFutura = new Despesa(
				dataParaDespesa,
				valorDaDespesa, 
				despesa.getFormaPagamento(), 
				despesa.getCartao(), 
				despesa.getCategoriaDespesa(), 
				calcularDataVencimento(despesa, dataParaDespesa),
				despesa.getQuantidadeParcelas(), 
				despesa.getParcelasPagas(),
				despesa.getObservacao(),
				despesa.isFixa(), 
				statusPagamento);
		
		if (despesa.isFixa() && despesa.isNova()) {
			despesaFutura.setDataFimFixa(DateHelper.mesSeguinte(dataParaDespesa, despesa.getQuantidadeParcelasOrFixas()));
		}
		
		salvarDespesa(despesaFutura);
		
	}

	private Date calcularDataVencimento(Despesa despesa, Date dataParaDespesa) {
		
		Date dataParaVencimento = null;
		
		if (despesa.getCartao() != null) {
			dataParaVencimento = despesa.getCartao().getDataVencimento();
		
		}else if (despesa.getDataVencimento() != null) {
			dataParaVencimento = DateHelper.mesSeguinte(despesa.getDataVencimento(), DateHelper.diferencaDeMesEntre(dataParaDespesa, despesa.getDataVencimento()));
			
		}else {
			dataParaVencimento = dataParaDespesa;
		}
		
		return dataParaVencimento;
	}
	
	private int iniciarLancamentoMesAtualOuSeguinte(Despesa despesa) {
		
		int resultadoParaLancamento;
		
		if (despesa.getCartao() != null) {
			
			resultadoParaLancamento = despesa.getDataDespesa().compareTo(despesa.getCartao().getDataFechamento()) < 0 ? 0 : 1;
			
		}else {
			
			resultadoParaLancamento = DateHelper.
					diferencaDeMesEntre(despesa.getDataDespesa(), despesa.getDataVencimento() == null ? new Date() : despesa.getDataVencimento()) == 0 ? 0 : 1;
		}
		
		return resultadoParaLancamento;
	}
}

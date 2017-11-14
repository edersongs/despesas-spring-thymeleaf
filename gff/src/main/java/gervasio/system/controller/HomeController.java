/**
 * 
 */
package gervasio.system.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import gervasio.system.model.Conta;
import gervasio.system.model.Despesa;
import gervasio.system.model.TotalDespesaMensal;
import gervasio.system.model.dto.TotalDespesaPorCategoriaDTO;
import gervasio.system.repository.CartaoRepository;
import gervasio.system.repository.ContaRepository;
import gervasio.system.repository.DespesaRepository;
import gervasio.system.repository.TotalDespesaMensalRepository;
import gervasio.system.util.DateHelper;

/**
 * @author Éderson Gervásio
 *
 */
@Controller
public class HomeController {

	@Autowired private DespesaRepository despesaRepository;
	@Autowired private CartaoRepository cartaoRepository;
	@Autowired private ContaRepository contaRepository;
	@Autowired private TotalDespesaMensalRepository totalDespesaMensalRepository;
	
	@GetMapping("/")
	public ModelAndView root() {
		
		ModelAndView model = new ModelAndView("page/home");
		
		model.addObject("limiteDisponivel", cartaoRepository.findLimiteDisponivelNosCartoes());
		
		model.addObject("totalDespesa", despesaRepository.findTotalDeDespesasNoMesAtual()
				.stream().map(Despesa::getValor).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
		
		model.addObject("totalContas", contaRepository.findSaldoDisponivelEmContasCorrente()
				.stream().map(Conta::getSaldo).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
		
		model.addObject("totalDespesaProxMes", despesaRepository.findTotalDeDespesasNoProximoMes()
				.stream().map(Despesa::getValor).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
		
		return model;
	}
	
	@GetMapping(path = "/graficoDespesaPorCategoria")
	public @ResponseBody Collection<TotalDespesaPorCategoriaDTO> popularGraficoDespesaPorCategoria() {
		
		return despesaRepository.findTotalDespesaPorCategoria();
	}
	
	@GetMapping(path = "/graficoDespesaPorTipoDespesa")
	public @ResponseBody Collection<TotalDespesaPorCategoriaDTO> popularGraficoDespesaPorTipoDespesa() {
		
		return despesaRepository.findTotalDespesaPorTipoDespesa();
	}
	
	@GetMapping(path = "/graficoTotalDespesaPorMes")
	public @ResponseBody Collection<TotalDespesaMensal> popularGraficoTotalDespesasPorMes() {
		
		return totalDespesaMensalRepository.findAll();
	}
}

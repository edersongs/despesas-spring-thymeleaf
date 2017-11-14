/**
 * 
 */
package gervasio.system.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gervasio.system.model.CategoriaDespesa;
import gervasio.system.model.Despesa;
import gervasio.system.model.TipoDespesa;
import gervasio.system.model.dto.CategoriaDespesaDTO;
import gervasio.system.model.enuns.FormaPagamento;
import gervasio.system.model.enuns.StatusPagamento;
import gervasio.system.repository.CartaoRepository;
import gervasio.system.repository.CategoriaDespesaRepository;
import gervasio.system.repository.DespesaRepository;
import gervasio.system.repository.TipoDespesaRepository;
import gervasio.system.service.CartaoService;
import gervasio.system.service.CategoriaDespesaService;
import gervasio.system.service.DespesaService;
import gervasio.system.service.exception.ContaServiceException;
import gervasio.system.util.DateHelper;

/**
 * @author Éderson Gervásio
 *
 */
@Controller
@RequestMapping("/despesas")
public class DespesaController {
	
	@Autowired private DespesaRepository despesaRepository;
	@Autowired private CartaoRepository cartaoRepository;
	@Autowired private TipoDespesaRepository tipoDespesaRepository;
	@Autowired private CategoriaDespesaRepository categoriaDespesaRepository;
	
	@Autowired private DespesaService despesaService;
	@Autowired private CategoriaDespesaService categoriaDespesaService;
	@Autowired CartaoService cartaoService;
	
	private List<StatusPagamento> listaStatusPagamento = new ArrayList<>(Arrays.asList(StatusPagamento.PAGO, StatusPagamento.A_PAGAR));
	
	@GetMapping
	public ModelAndView principal() {
		
		return pesquisar(null);
	}
	
	@GetMapping("/add")
	public ModelAndView novo(Despesa despesa) {
		
		cartaoService.verificarVencimentoFechamentoCartao();
		
		ModelAndView model = new ModelAndView("page/despesa/cadDespesa");
		model.addObject("tiposDespesa", tipoDespesaRepository.findAll());
		model.addObject("categoriasDeDespesas", categoriaDespesaRepository.findAll());
		model.addObject("formasPagamento", FormaPagamento.values());
		model.addObject("cartoes", cartaoRepository.findAll());
		model.addObject("status", listaStatusPagamento);
		model.addObject(despesa);
		
		return model;
	}
	
	@GetMapping("/pesquisar")
	public ModelAndView pesquisar(@RequestParam("dataFiltro") String dataFiltro) {
		
		LocalDate dataInicial;
		LocalDate dataFinal;
		
		if (StringUtils.isEmpty(dataFiltro)) {
			
			dataInicial = DateHelper.primeiroDiaDoMesAtual();
			dataFinal = DateHelper.ultimoDiaDoMesAtual();
			
		} else {
			
			dataInicial = DateHelper.primeiroDiaDoMesPara(LocalDate.parse(dataFiltro, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			dataFinal = DateHelper.ultimoDiaDoMesPara(LocalDate.parse(dataFiltro, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			
		}
		
		List<Despesa> listaDespesas = despesaRepository.findByDataDespesaBetween(DateHelper.convertLocalDateParaDate(dataInicial), DateHelper.convertLocalDateParaDate(dataFinal)); 
				
		ModelAndView model = new ModelAndView("page/despesa/listDespesa");
		model.addObject("despesas", listaDespesas);
		model.addObject("totalDespesas", despesaService.valoresDespesas(listaDespesas));
		
		return model;
	}
	
	@GetMapping("/edit{id}")
	public ModelAndView editar(@PathVariable("id") Despesa despesa) {
		
		return novo(despesa);
	}
	
	@GetMapping("/del{id}")
	public ModelAndView deletar(@PathVariable("id") Long codigo) {
		
		despesaRepository.delete(codigo);
		
		return pesquisar(null);
	}
	
	@GetMapping(path="/pesquisarCategoriaDespesa", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<CategoriaDespesa> pesquisarCategoriaDespesa(@RequestParam("codigoTipoDespesa") Long codigoTipoDespesa) {
		
		return categoriaDespesaRepository.findByTipoDespesa(tipoDespesaRepository.findOne(codigoTipoDespesa));
	}
	
	@GetMapping(path="/listaTiposDespesas")
	public @ResponseBody List<TipoDespesa> pesquisarTipoDespesa() {
		
		return tipoDespesaRepository.findAll();
	}
	
	@PostMapping("/save")
	public ModelAndView salvar(@Valid Despesa despesa, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return novo(despesa);
		}
		
		if (despesa.getDataDespesa() == null) {
			
			despesa.setDataDespesa(new Date());
			
		}
		despesaService.lancarDespesa(despesa);
		attributes.addFlashAttribute("mensagem", "Despesa Salva com Sucesso!");
		
		return new ModelAndView("redirect:/despesas/add");
	}
	
	@PostMapping(path="/salvarTipoDespesa", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<?> salvarTipoDespesa(@RequestBody @Valid TipoDespesa tipoDespesa, BindingResult result) {
		
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		tipoDespesa = despesaService.salvarTipoDespesa(tipoDespesa);
		
		return ResponseEntity.ok(tipoDespesa);
	}
	
	@PostMapping(path="/salvarCategoriaDespesa", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<?> salvarCategoriaDespesa(@RequestBody @Valid CategoriaDespesaDTO categoriaDespesaDTO, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nomeModalID").getDefaultMessage());
		}
		
		CategoriaDespesa categoriDespesa = new CategoriaDespesa(
				categoriaDespesaDTO.getNomeCategoria(), 
				null, 
				tipoDespesaRepository.findOne(categoriaDespesaDTO.getCodigoTipoDespesa()));
		
		return ResponseEntity.ok(categoriaDespesaService.salvarCategoriaDespesa(categoriDespesa));
	}
	
	@PostMapping(path="/fazerPagamento/{codigoDespesa}")
	public ModelAndView realizarPagamento(@RequestParam("valorPagamento") String valorPagamento, 
			@RequestParam("conta") int conta, @PathVariable("codigoDespesa") Long codigoDespesa, RedirectAttributes attributes) {
		
		BigDecimal valorConvertido = BigDecimal.ZERO;
		
		if (!StringUtils.isEmpty(valorPagamento)) {
			valorConvertido = new BigDecimal(StringUtils.replace(valorPagamento, ",", "."));
		}
		
		try {
			
			despesaService.realizarPagamentoDespesa(valorConvertido, codigoDespesa, conta);
			
		} catch (ContaServiceException e) {
			attributes.addFlashAttribute("mensagem", "Não foi possível realizar o pagamento. ERRO: " + e.getMessage());
		}
		
		return pesquisar(null);
	}
	
	@PostMapping(path="/fazerPagamentoCartaoCredito/{codigoDespesa}")
	public ModelAndView realizarPagamentoCartaoCredito(@RequestParam("valorPagamento") String valorPagamento, 
			@RequestParam("conta") int conta, @PathVariable("codigoCartao") Long codigoDespesa, RedirectAttributes attributes) {
		
		BigDecimal valorConvertido = BigDecimal.ZERO;
		
		if (!StringUtils.isEmpty(valorPagamento)) {
			valorConvertido = new BigDecimal(StringUtils.replace(valorPagamento, ",", "."));
		}
		
		try {
			
			despesaService.realizarPagamentoDespesa(valorConvertido, codigoDespesa, conta);
			
		} catch (ContaServiceException e) {
			attributes.addFlashAttribute("mensagem", "Não foi possível realizar o pagamento. ERRO: " + e.getMessage());
		}
		
		return pesquisar(null);
	}
}

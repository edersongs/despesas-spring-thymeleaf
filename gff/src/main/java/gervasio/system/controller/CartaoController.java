/**
 * 
 */
package gervasio.system.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gervasio.system.model.Cartao;
import gervasio.system.model.Despesa;
import gervasio.system.model.Tarifa;
import gervasio.system.model.dto.TarifaDTO;
import gervasio.system.model.enuns.TipoCartao;
import gervasio.system.repository.CartaoRepository;
import gervasio.system.repository.ContaRepository;
import gervasio.system.repository.DespesaRepository;
import gervasio.system.repository.TipoTariaRepository;
import gervasio.system.service.CartaoService;

/**
 * @author edersongs
 *
 */
@Controller
@RequestMapping("/cartoes")
public class CartaoController {
	
	@Autowired private TipoTariaRepository tipoTariaRepository; 
	@Autowired private ContaRepository contaRepository;
	@Autowired private CartaoRepository cartaoRepository;
	@Autowired private DespesaRepository despesaRepository;
	
	@Autowired private CartaoService cartaoService;
	
	private List<Tarifa> tarifas = new ArrayList<>();
	
	@GetMapping
	public ModelAndView init() {
		return novo(new Cartao());
	}
	
	@GetMapping("/add")
	public ModelAndView novo(Cartao cartao) {
		ModelAndView model = new ModelAndView("page/cartao/cadCartao");
		model.addObject("tipoDeCartoes", TipoCartao.values());
		model.addObject("tiposDeTarifas", tipoTariaRepository.findAll());
		model.addObject("contas", contaRepository.findAll());
		model.addObject(cartao);
		return model;
	}
	
	@GetMapping("/pagamentos")
	public ModelAndView pagamento() {
		ModelAndView model = new ModelAndView("page/cartao/pagamentoCartao");
		model.addObject("cartoes", cartaoRepository.findAll());
		return model;
	}
	
	@PostMapping("/save")
	public ModelAndView salvar(@Valid Cartao cartao, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cartao);
		}
		cartaoService.associarTarifas(cartao, this.tarifas);
		attributes.addFlashAttribute("mensagem", "Cartão Salvo com Sucesso!");
		return new ModelAndView("redirect:/cartoes/add");
	}
	
	@PostMapping(path="/addTarifa", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ModelAndView addTarifa(@RequestBody TarifaDTO tarifaDTO) {
		
		Tarifa tarifaPreenchida = new Tarifa(
				tarifaDTO.getNome(), 
				new BigDecimal(tarifaDTO.getValor()), 
				tipoTariaRepository.findOne(new Long(tarifaDTO.getTipoTarifa())));
		
		this.tarifas.add(tarifaPreenchida);
		
		ModelAndView mv = new ModelAndView("page/tarifa/tabelaDeTarifas");
		
		mv.addObject("tarifas", this.tarifas);
		
		return mv;
	}
}

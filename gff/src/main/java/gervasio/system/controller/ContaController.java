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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gervasio.system.model.Conta;
import gervasio.system.model.Tarifa;
import gervasio.system.model.dto.TarifaDTO;
import gervasio.system.model.enuns.TipoConta;
import gervasio.system.repository.ContaRepository;
import gervasio.system.repository.TipoTariaRepository;
import gervasio.system.service.ContaService;

/**
 * @author Éderson Gervásio
 *
 */
@Controller
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired ContaRepository contaRepository;
	@Autowired TipoTariaRepository tipoTariaRepository; 
	
	@Autowired ContaService contaService;
	
	private List<Tarifa> tarifas;
	
	@GetMapping
	public ModelAndView novo(Conta conta) {
		
		tarifas = new ArrayList<>();
		ModelAndView model = new ModelAndView("page/conta/cadConta");
		model.addObject("tiposContas", TipoConta.values());
		model.addObject("tarifa", new Tarifa());
		model.addObject("tiposDeTarifas", tipoTariaRepository.findAll());
		model.addObject(conta);
		
		return model;
	}

	@PostMapping("/save")
	public ModelAndView salvar(@Valid Conta conta, BindingResult result, RedirectAttributes attributes) throws Exception {
		
		if (result.hasErrors()) {
			return novo(conta);
		}
		contaService.associarTarifas(conta, this.tarifas);
		attributes.addFlashAttribute("mensagem", "Conta salva com sucesso!");
		
		return new ModelAndView("redirect:/contas");
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

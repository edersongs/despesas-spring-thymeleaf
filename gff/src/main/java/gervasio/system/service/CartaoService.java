/**
 * 
 */
package gervasio.system.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gervasio.system.model.Cartao;
import gervasio.system.model.Tarifa;
import gervasio.system.repository.CartaoRepository;
import gervasio.system.service.api.TarifaService;
import gervasio.system.util.DateHelper;

/**
 * @author Éderson Gervásio
 *
 */
@Service
public class CartaoService implements TarifaService {
	
	@Autowired CartaoRepository cartaoRepository;
	
	public void salvarCartao(Cartao cartao) {
		
		cartaoRepository.save(cartao);
	}
	
	public void verificarVencimentoFechamentoCartao() {
		
		cartaoRepository.findAll().forEach(f -> lancarVencimentoFechamentoCartao(f));
	}
	
	private void lancarVencimentoFechamentoCartao(Cartao cartao) {
		
		if(DateHelper.diferencaDeMesEntre(cartao.getDataVencimento(), new Date()) != 0) {
			cartao.setDataVencimento(DateHelper.mesSeguinte(cartao.getDataVencimento(), 1));
		}
		if (DateHelper.diferencaDeMesEntre(cartao.getDataFechamento(), new Date()) != 0) {
			cartao.setDataFechamento(DateHelper.mesSeguinte(cartao.getDataFechamento(), 1));
		}
		
		salvarCartao(cartao);
	}

	public void atualizarDatasCartoesCredito() {
		
		cartaoRepository.findAll().forEach(c -> {
			if(DateHelper.diferencaDeMesEntre(DateHelper.truncaData(c.getDataVencimento()), DateHelper.truncaData(new Date())) < 0) {
				c.setDataVencimento(DateHelper.mesSeguinte(c.getDataVencimento(), 0));
				c.setDataFechamento(DateHelper.mesSeguinte(c.getDataFechamento(), 0));
				cartaoRepository.save(c);
			}
		});
		
	}
	
	@Override
	public void associarTarifas(Object object, List<Tarifa> tarifas) {
		
		if (object instanceof Cartao) {
			
			Cartao cartao = (Cartao) object;
			
			tarifas.forEach(t -> t.setCartao(cartao));
			cartao.setTarifas(tarifas);
			
			salvarCartao(cartao);
		}
		
	}
}

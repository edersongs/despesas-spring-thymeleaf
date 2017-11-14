/**
 * 
 */
package gervasio.system.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gervasio.system.model.Cartao;
import gervasio.system.model.Conta;
import gervasio.system.model.Tarifa;
import gervasio.system.repository.ContaRepository;
import gervasio.system.service.api.TarifaService;
import gervasio.system.service.exception.ContaServiceException;

/**
 * @author Éderson Gervásio
 *
 */
@Service
public class ContaService implements TarifaService {
	
	@Autowired ContaRepository contaRepository;
	
	public void salvarConta(Conta conta) {
		
		contaRepository.save(conta);
	}
	
	public void debitar(Long codigoConta, BigDecimal valor) throws ContaServiceException {
		
		if (codigoConta == null || (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0)) {
			throw new ContaServiceException("Código da conta deve ser diferente de nulo e o valor deve ser diferente de nulo e maior que zero!");
		}
		
		Conta contaBuscada = contaRepository.findOne(codigoConta);
		
		contaBuscada.setSaldo(contaBuscada.getSaldo().subtract(valor));
		
		salvarConta(contaBuscada);
	}
	
	@Override
	public void associarTarifas(Object object, List<Tarifa> tarifas) {
		
		if (object instanceof Conta) {
			
			Conta conta = (Conta) object;
			
			tarifas.forEach(t -> t.setConta(conta));
			conta.setTarifas(tarifas);
			
			salvarConta(conta);
		}
	}
}

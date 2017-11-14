/**
 * 
 */
package gervasio.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gervasio.system.model.CategoriaDespesa;
import gervasio.system.repository.CategoriaDespesaRepository;

/**
 * @author Éderson Gervásio
 *
 */
@Service
public class CategoriaDespesaService {

	@Autowired private CategoriaDespesaRepository categoriaDespesaRepository;
	
	public CategoriaDespesa salvarCategoriaDespesa(CategoriaDespesa categoriaDespesa) {
		return categoriaDespesaRepository.save(categoriaDespesa);
	}
}

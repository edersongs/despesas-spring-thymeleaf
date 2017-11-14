/**
 * 
 */
package gervasio.system.service.api;

import java.util.List;

import gervasio.system.model.Tarifa;

/**
 * @author Éderson Gervásio
 *
 */
public interface TarifaService {
	
	public void associarTarifas(Object object, List<Tarifa> tarifas);

}

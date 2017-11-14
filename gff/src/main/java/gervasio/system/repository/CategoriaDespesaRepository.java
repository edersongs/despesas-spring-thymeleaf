/**
 * 
 */
package gervasio.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gervasio.system.model.CategoriaDespesa;
import gervasio.system.model.TipoDespesa;

/**
 * @author Éderson Gervásio
 *
 */
@Repository
public interface CategoriaDespesaRepository extends JpaRepository<CategoriaDespesa, Long> {

	List<CategoriaDespesa> findByTipoDespesa(TipoDespesa tipoDespesa);
}

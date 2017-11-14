/**
 * 
 */
package gervasio.system.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gervasio.system.model.Cartao;
import gervasio.system.model.Despesa;

/**
 * @author Éderson Gervásio
 *
 */
@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

	BigDecimal findLimiteDisponivelNosCartoes();
}

/**
 * 
 */
package gervasio.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gervasio.system.model.Conta;

/**
 * @author Éderson Gervásio
 *
 */
public interface ContaRepository extends JpaRepository<Conta, Long> {

	@Query("select c from Conta c where c.tipoConta = 'CONTA_CORRENTE'")
	List<Conta> findSaldoDisponivelEmContasCorrente();
}

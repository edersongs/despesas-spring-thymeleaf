/**
 * 
 */
package gervasio.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gervasio.system.model.TotalDespesaMensal;

/**
 * @author Éderson Gervásio
 *
 */
@Repository
public interface TotalDespesaMensalRepository extends JpaRepository<TotalDespesaMensal, Long> {

}

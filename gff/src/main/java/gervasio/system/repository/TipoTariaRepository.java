/**
 * 
 */
package gervasio.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gervasio.system.model.TipoTarifa;

/**
 * @author Éderson Gervásio
 *
 */
@Repository
public interface TipoTariaRepository extends JpaRepository<TipoTarifa, Long> {

}

/**
 * 
 */
package gervasio.system.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gervasio.system.model.Despesa;
import gervasio.system.model.dto.TotalDespesaPorCategoriaDTO;

/**
 * @author Éderson Gervásio
 *
 */
@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

	List<Despesa> findByDataDespesaBetween(Date dataInicial, Date dataFinal);
	
	@Query("select d from Despesa d where MONTH(d.dataDespesa) = MONTH(CURRENT_DATE()) and d.status = 'A_PAGAR'")
	List<Despesa> findTotalDeDespesasNoMesAtual();
	
	@Query("select d from Despesa d where MONTH(d.dataDespesa) = MONTH(CURRENT_DATE()) + 1 and d.status = 'A_PAGAR'")
	List<Despesa> findTotalDeDespesasNoProximoMes();
	
	@Query("SELECT new gervasio.system.model.dto.TotalDespesaPorCategoriaDTO(c.nome, sum(d.valor)) "
			+ "FROM Despesa d, CategoriaDespesa c "
			+ "WHERE c.codigoCategoria = d.categoriaDespesa.codigoCategoria "
			+ "and MONTH(d.dataDespesa) = MONTH(CURRENT_DATE()) "
			+ "GROUP BY c.nome")
	Collection<TotalDespesaPorCategoriaDTO> findTotalDespesaPorCategoria();
	
	@Query("SELECT new gervasio.system.model.dto.TotalDespesaPorCategoriaDTO(t.nome, sum(d.valor)) "
			+ "FROM Despesa d, CategoriaDespesa c, TipoDespesa t "
			+ "WHERE c.codigoCategoria = d.categoriaDespesa.codigoCategoria "
			+ "and t.codigoTipoDespesa = c.tipoDespesa.codigoTipoDespesa "
			+ "and MONTH(d.dataDespesa) = MONTH(CURRENT_DATE()) "
			+ "GROUP BY t.nome")
	Collection<TotalDespesaPorCategoriaDTO> findTotalDespesaPorTipoDespesa();
	
	@Query("select d "
			+ "from Despesa d, Cartao c "
			+ "where c.codigoCartao = d.cartao.codigoCartao "
			+ "and d.formaPagamento = 'CARTAO_CREDITO' "
			+ "and d.status = 'A_PAGAR' "
			+ "and month(d.dataDespesa) = month(current_date())"
			+ "and c.codigoCartao = :codigoCartao")
	List<Despesa> findDespesasCartaoCredito(@Param("codigoCartao") Long codigoCartao);
	
}

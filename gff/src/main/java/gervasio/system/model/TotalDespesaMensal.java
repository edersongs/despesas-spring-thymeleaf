/**
 * 
 */
package gervasio.system.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/**
 * @author Éderson Gervásio
 *
 */
@Entity
@Table(name = "VTOTALMENSALDESPESA")
public class TotalDespesaMensal {
	
	@Id
	private String mes;
	
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal total;
	
	public String getMes() {
		return mes;
	}
	public BigDecimal getTotal() {
		return total;
	}
}

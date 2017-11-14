/**
 * 
 */
package gervasio.system.model.dto;

import java.math.BigDecimal;

/**
 * @author Éderson Gervásio
 *
 */
public class TotalDespesaPorCategoriaDTO {

	private String label;
	private BigDecimal value;
	
	public TotalDespesaPorCategoriaDTO(String label, BigDecimal value) {

		this.label = label;
		this.value = value;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
}

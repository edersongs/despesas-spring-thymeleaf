/**
 * 
 */
package gervasio.system.model.dto;

/**
 * @author Éderson Gervásio
 *
 */
public class CategoriaDespesaDTO {

	private String nomeCategoria;
	
	private Long codigoTipoDespesa;
	
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public Long getCodigoTipoDespesa() {
		return codigoTipoDespesa;
	}
	public void setCodigoTipoDespesa(Long codigoTipoDespesa) {
		this.codigoTipoDespesa = codigoTipoDespesa;
	}
}

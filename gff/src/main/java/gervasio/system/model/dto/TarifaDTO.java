/**
 * 
 */
package gervasio.system.model.dto;

/**
 * @author Éderson Gervásio
 *
 */
public class TarifaDTO {

	private String nome;
	private String valor;
	private String tipoTarifa;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getTipoTarifa() {
		return tipoTarifa;
	}
	public void setTipoTarifa(String tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}
}

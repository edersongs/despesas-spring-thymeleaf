/**
 * 
 */
package gervasio.system.model.enuns;

/**
 * @author Éderson Gervásio
 *
 */
public enum TipoPagamento {

	VISTA(1,"A Vista"),
	FINANCIADO(2,"Financiado"),
	PARCELADO(3,"Parcelado");
	
	private int valor;
	private String descricao;
	
	TipoPagamento(int valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public int getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}
}

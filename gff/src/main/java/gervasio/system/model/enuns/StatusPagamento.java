/**
 * 
 */
package gervasio.system.model.enuns;

/**
 * @author Éderson Gervásio
 *
 */
public enum StatusPagamento {

	PAGO(1,"Pago"),
	A_PAGAR(2,"A Pagar"),
	PARCIAL(3,"Pago Parcialmente");
	
	private int codigo;
	private String descricao;
	
	private StatusPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
}

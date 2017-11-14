/**
 * 
 */
package gervasio.system.model.enuns;

/**
 * @author Éderson Gervásio
 *
 */
public enum TipoCartao {

	CREDITO(1, "Crédito"),
	DEBITO(2, "Débito");
	
	private int codigo;
	private String nome;
	
	private TipoCartao(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}
}

/**
 * 
 */
package gervasio.system.model.enuns;

/**
 * @author Éderson Gervásio
 *
 */
public enum TipoConta {

	CONTA_CORRENTE(1, "Conta Corrente"),
	CONTA_POUPANCA(2, "Conta Poupança"),
	CONTA_SALARIO(3, "Conta Salário");
	
	private int codigo;
	private String descricao;
	
	private TipoConta(int codigo, String descricao) {
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
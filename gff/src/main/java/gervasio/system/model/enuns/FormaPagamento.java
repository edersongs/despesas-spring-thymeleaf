/**
 * 
 */
package gervasio.system.model.enuns;

/**
 * @author Éderson Gervásio
 *
 */
public enum FormaPagamento {

	CARTAO_CREDITO(1,"Cartão de Crédito"),
	DINHEIRO(2,"Dinheiro"),
	CARTAO_DEBITO(3,"Cartão de Débito"),
	DEBITO_AUTO(4,"Débito Automático"),
	CARNE(5,"Transferência"),
	BOLETO(6, "Cartão da Loja");
	
	private int valor;
	private String descricao;
	
	FormaPagamento(int valor, String descricao) {
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

/**
 * 
 */
package gervasio.system.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/**
 *  @author Éderson Gervásio
 *
 */
@Entity
@Table(name="TTARIFA")
public class Tarifa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoTarifa;
	
	@NotBlank(message="Nome da tarifa não informada!")
	private String nome;
	
	@NotNull(message="Valor da tarifa não informada!")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valor;
	
	@ManyToOne(targetEntity=Conta.class)
	@JoinColumn(name="CODIGOCONTA", foreignKey= @ForeignKey(name = "fk_tarifa_conta"))
	private Conta conta;
	
	@ManyToOne(targetEntity=Cartao.class)
	@JoinColumn(name="CODIGOCARTAO", foreignKey= @ForeignKey(name = "fk_tarifa_cartao"))
	private Cartao cartao;
	
	@ManyToOne(targetEntity=Despesa.class)
	@JoinColumn(name="CODIGODESPESA", foreignKey= @ForeignKey(name = "fk_tarifa_despesa"))
	private Despesa despesa;
	
	@NotNull(message="Tipo de tarifa não informada!")
	@ManyToOne(targetEntity=TipoTarifa.class)
	@JoinColumn(name="CODIGOTIPOTARIFA", foreignKey = @ForeignKey(name = "fk_tarifaTipo"))
	private TipoTarifa tipoTarifa;

	public Tarifa() {}	
	
	public Tarifa(String nome, BigDecimal valor, TipoTarifa tipoTarifa) {
		this.nome = nome;
		this.valor = valor;
		this.tipoTarifa = tipoTarifa;
	}

	public Long getCodigoTarifa() {
		return codigoTarifa;
	}

	public void setCodigoTarifa(Long codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public TipoTarifa getTipoTarifa() {
		return tipoTarifa;
	}

	public void setTipoTarifa(TipoTarifa tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoTarifa == null) ? 0 : codigoTarifa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarifa other = (Tarifa) obj;
		if (codigoTarifa == null) {
			if (other.codigoTarifa != null)
				return false;
		} else if (!codigoTarifa.equals(other.codigoTarifa))
			return false;
		return true;
	}
}

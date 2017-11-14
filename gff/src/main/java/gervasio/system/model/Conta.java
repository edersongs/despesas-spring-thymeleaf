/**
 * 
 */
package gervasio.system.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import gervasio.system.model.enuns.TipoConta;

/**
 * @author Éderson Gervásio
 *
 */
@Entity
@Table(name="TCONTA")
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoConta;
	
	@NotBlank(message="Nome da conta não informado!")
	private String nome;
	
	@NotBlank(message="Titular da conta não informado!")
	private String titular;
	
	@NotNull(message="Agência da conta não informada!")
	private Integer agencia;
	
	@NotNull(message="Digito da agência não informado!")
	private Integer digitoAgencia;
	
	@NotNull(message="Número da conta não informado!")
	private Integer conta;
	
	@NotNull(message="Digito da conta não informado!")
	private Integer digitoConta;
	
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal saldo;
	
	@OneToMany(targetEntity=Tarifa.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy="conta")
	private List<Tarifa> tarifas;
	
	@Enumerated(EnumType.STRING)
	private TipoConta tipoConta;

	public Long getCodigoConta() {
		return codigoConta;
	}

	public void setCodigoConta(Long codigoConta) {
		this.codigoConta = codigoConta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Integer getDigitoAgencia() {
		return digitoAgencia;
	}

	public void setDigitoAgencia(Integer digitoAgencia) {
		this.digitoAgencia = digitoAgencia;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

	public Integer getDigitoConta() {
		return digitoConta;
	}

	public void setDigitoConta(Integer digitoConta) {
		this.digitoConta = digitoConta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public List<Tarifa> getTarifas() {
		return tarifas;
	}

	public void setTarifas(List<Tarifa> tarifas) {
		this.tarifas = tarifas;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	@PrePersist
	private void antesDePersistir() {
		if (saldo == null) {
			saldo = BigDecimal.ZERO;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoConta == null) ? 0 : codigoConta.hashCode());
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
		Conta other = (Conta) obj;
		if (codigoConta == null) {
			if (other.codigoConta != null)
				return false;
		} else if (!codigoConta.equals(other.codigoConta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome + " - " + tipoConta;
	}
	
	
}

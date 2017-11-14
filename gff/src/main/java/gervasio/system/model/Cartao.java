/**
 * 
 */
package gervasio.system.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import gervasio.system.model.enuns.StatusPagamento;
import gervasio.system.model.enuns.TipoCartao;
import gervasio.system.util.DateHelper;

/**
 * @author Éderson Gervásio
 *
 */
@Entity
@Table(name = "TCARTAO")
@NamedNativeQuery(name = "Cartao.findLimiteDisponivelNosCartoes", 
				  query = "select sum(limite) - ( "
						  + "	SELECT "
						  + "     IFNULL(sum(d.valor),0) "
						  + "FROM "
						  + "    tdespesa d "
						  + "WHERE "
						  + "    MONTH(d.dataDespesa) = MONTH(CURRENT_DATE()) "
						  + "And d.formaPagamento = 'CARTAO_CREDITO'"
						  + ") desp from tcartao ")
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoCartao;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Tipo de Cartao não foi informado!")
	private TipoCartao tipoCartao;
	
	@NotBlank(message = "Bandeira não foi informada!")
	private String bandeira;
	
	@NotBlank(message = "Número do cartão não foi informado!")
	private String numeroCartao;
	
	@NotBlank(message = "Nome do cartão não foi informado!")
	private String nomeCartao;
	
	@NotNull(message = "Código de segurança não informado!")
	private Integer codigoSeguranca;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull(message = "Data de vencimento não informado!")
	private Date dataVencimento;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull(message = "Data de fechamento não informado!")
	private Date dataFechamento;
	
	@NumberFormat(style=Style.CURRENCY)
	@NotNull(message = "Limte não informado!")
	private BigDecimal limite;
	
	@NotBlank(message = "Data de validade não informado!")
	private String dataValidade;
	
	@OneToMany(targetEntity=Tarifa.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy="cartao")
	private List<Tarifa> tarifas;
	
	@ManyToOne(targetEntity=Conta.class)
	@JoinColumn(name="CODIGOCONTA", foreignKey = @ForeignKey(name = "fk_cartao_conta"))
	private Conta conta;
	
	public List<Tarifa> getTarifas() {
		return tarifas;
	}
	public void setTarifas(List<Tarifa> tarifas) {
		this.tarifas = tarifas;
	}
	public Long getCodigoCartao() {
		return codigoCartao;
	}
	public void setCodigoCartao(Long codigoCartao) {
		this.codigoCartao = codigoCartao;
	}
	public TipoCartao getTipoCartao() {
		return tipoCartao;
	}
	public void setTipoCartao(TipoCartao tipoCartao) {
		this.tipoCartao = tipoCartao;
	}
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public String getNomeCartao() {
		return nomeCartao;
	}
	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}
	public Integer getCodigoSeguranca() {
		return codigoSeguranca;
	}
	public void setCodigoSeguranca(Integer codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public BigDecimal getLimite() {
		return limite;
	}
	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}
	public String getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}
	public Date getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public boolean isAtrasado() {
		
		boolean estaAtrasado = false;
		
		if(dataVencimento != null 
				&& (DateHelper.diferencaDeMesEntre(new Date(), dataVencimento) > 0 
				|| DateHelper.diaPrimeiraDataMaiorDiaSegundaData(new Date(), dataVencimento))) {
			
			estaAtrasado = true;
		}
		
		return estaAtrasado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoCartao == null) ? 0 : codigoCartao.hashCode());
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
		Cartao other = (Cartao) obj;
		if (codigoCartao == null) {
			if (other.codigoCartao != null)
				return false;
		} else if (!codigoCartao.equals(other.codigoCartao))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return nomeCartao +" - " + bandeira;
	}
	
	
}

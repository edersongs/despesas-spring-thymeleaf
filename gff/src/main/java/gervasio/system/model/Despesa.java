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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import gervasio.system.model.enuns.FormaPagamento;
import gervasio.system.model.enuns.StatusPagamento;
import gervasio.system.util.DateHelper;

/**
 * @author Éderson Gervásio
 *
 */
@Entity
@Table(name = "TDESPESA")
public class Despesa {
	
	public static final String MENSAGEM_INF_PAGAMENTO_PARCIAL = " (Restante do pagameto parcial realizado no mês passado)!";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoDespesa;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataDespesa;
	
	@NumberFormat(style=Style.CURRENCY)
	@NotNull(message="Valor da Despesa não informado!")
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message="Forma de Pagamento não informado!")
	private FormaPagamento formaPagamento;
	
	@ManyToOne(targetEntity=Cartao.class)
	@JoinColumn(name="codigoCartao", foreignKey = @ForeignKey(name = "fk_despesaCartao"))
	private Cartao cartao;
	
	@ManyToOne(targetEntity=CategoriaDespesa.class)
	@JoinColumn(name="codigoCategoriaDespesa", referencedColumnName="codigoCategoria", foreignKey = @ForeignKey(name = "fk_despesaCategoria"))
	@NotNull(message="Categoria não informado!")
	private CategoriaDespesa categoriaDespesa;
	
	@OneToMany(targetEntity=Tarifa.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy="despesa")
	private List<Tarifa> tarifas;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataFimFixa;
	
	@Enumerated(EnumType.STRING)
	private StatusPagamento status;

	private Integer quantidadeParcelas;
	
	private int parcelasPagas;
	
	private String observacao;
	
	private boolean fixa;
	
	public Despesa() {}

	public Despesa(Date dataDespesa, BigDecimal valor, FormaPagamento formaPagamento, Cartao cartao,
			CategoriaDespesa categoriaDespesa, Date dataVencimento, Integer quantidadeParcelas, int parcelasPagas,
			String observacao, boolean fixa, StatusPagamento status) {

		this.dataDespesa = dataDespesa;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
		this.cartao = cartao;
		this.categoriaDespesa = categoriaDespesa;
		this.dataVencimento = dataVencimento;
		this.quantidadeParcelas = quantidadeParcelas;
		this.parcelasPagas = parcelasPagas;
		this.observacao = observacao;
		this.fixa = fixa;
		this.status = status;
	}
	
	public Long getCodigoDespesa() {
		return codigoDespesa;
	}
	public void setCodigoDespesa(Long codigoDespesa) {
		this.codigoDespesa = codigoDespesa;
	}
	public Date getDataDespesa() {
		return dataDespesa;
	}
	public void setDataDespesa(Date dataDespesa) {
		this.dataDespesa = dataDespesa;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}
	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
	public int getParcelasPagas() {
		return parcelasPagas;
	}
	public void setParcelasPagas(int parcelasPagas) {
		this.parcelasPagas = parcelasPagas;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Cartao getCartao() {
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	public boolean isFixa() {
		return fixa;
	}
	public void setFixa(boolean fixa) {
		this.fixa = fixa;
	}
	public StatusPagamento getStatus() {
		return status;
	}
	public void setStatus(StatusPagamento status) {
		this.status = status;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Date getDataFimFixa() {
		return dataFimFixa;
	}
	public void setDataFimFixa(Date dataFimFixa) {
		this.dataFimFixa = dataFimFixa;
	}
	public CategoriaDespesa getCategoriaDespesa() {
		return categoriaDespesa;
	}
	public void setCategoriaDespesa(CategoriaDespesa categoriaDespesa) {
		this.categoriaDespesa = categoriaDespesa;
	}
	public List<Tarifa> getTarifas() {
		return tarifas;
	}

	public void setTarifas(List<Tarifa> tarifas) {
		this.tarifas = tarifas;
	}
	
	public boolean isNova() {
		return codigoDespesa == null;
	}
	
	public boolean isParcelado() {
		return quantidadeParcelas != null && quantidadeParcelas > 0;
	}
	
	public void calcularParcelasRestantes() {
		if(parcelasPagas < quantidadeParcelas && parcelasPagas > 0) {
			parcelasPagas++;
			
		}else if(parcelasPagas == 0) {
			this.setParcelasPagas(1);
		}
	}
	
	public Integer getQuantidadeParcelasOrFixas() {
		
		if (fixa && (quantidadeParcelas == null || quantidadeParcelas <= 0)) {
			quantidadeParcelas = 12;
		}
		
		return quantidadeParcelas;
	}
	
	public boolean isAtrasado() {
		
		boolean estaAtrasado = false;
		
		if(dataVencimento != null 
				&& cartao == null 
				&& !status.equals(StatusPagamento.PAGO)
				&& (DateHelper.diferencaDeMesEntre(new Date(), dataVencimento) > 0 
				|| DateHelper.diaPrimeiraDataMaiorDiaSegundaData(new Date(), dataVencimento))) {
			
			estaAtrasado = true;
		}
		
		return estaAtrasado;
	}
	
	@PrePersist
	private void antesDePersistirThis() {
		if (quantidadeParcelas == null) {
			quantidadeParcelas = 0;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoDespesa == null) ? 0 : codigoDespesa.hashCode());
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
		Despesa other = (Despesa) obj;
		if (codigoDespesa == null) {
			if (other.codigoDespesa != null)
				return false;
		} else if (!codigoDespesa.equals(other.codigoDespesa))
			return false;
		return true;
	}
}

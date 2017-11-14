/**
 * 
 */
package gervasio.system.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Éderson Gervásio
 *
 */
@Entity
@Table(name = "TCATEGORIADESPESA")
public class CategoriaDespesa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigoCategoria;
	
	@NotBlank(message = "Nome da categoria não informado!")
	private String nome;
	
	private String descricao;
	
	@NotNull(message = "Tipo de despesa não informado!")
	@ManyToOne(targetEntity=TipoDespesa.class)
	@JoinColumn(name = "codigoTipoDespesa", nullable = false, foreignKey = @ForeignKey(name = "fk_categoriaTipo"))
	private TipoDespesa tipoDespesa;
	
	public CategoriaDespesa() {}
	
	public CategoriaDespesa(String nome, String descricao, TipoDespesa tipoDespesa) {
		this.nome = nome;
		this.descricao = descricao;
		this.tipoDespesa = tipoDespesa;
	}
	
	public Long getCodigoCategoria() {
		return codigoCategoria;
	}
	public void setCodigoCategoria(Long codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}
	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoCategoria == null) ? 0 : codigoCategoria.hashCode());
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
		CategoriaDespesa other = (CategoriaDespesa) obj;
		if (codigoCategoria == null) {
			if (other.codigoCategoria != null)
				return false;
		} else if (!codigoCategoria.equals(other.codigoCategoria))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return nome;
	}
}

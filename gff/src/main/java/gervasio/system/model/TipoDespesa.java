/**
 * 
 */
package gervasio.system.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Éderson Gervásio
 *
 */
@Entity
@Table(name = "TTIPODESPESA")
public class TipoDespesa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoTipoDespesa;
	
	@NotBlank(message="Nome do tipo da despesa é obrigatório!")
	private String nome;
	
	public Long getCodigoTipoDespesa() {
		return codigoTipoDespesa;
	}
	public void setCodigoTipoDespesa(Long codigoTipoDespesa) {
		this.codigoTipoDespesa = codigoTipoDespesa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoDespesa() {}
	
	public TipoDespesa(String nome) {
		this.nome = nome;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoTipoDespesa == null) ? 0 : codigoTipoDespesa.hashCode());
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
		TipoDespesa other = (TipoDespesa) obj;
		if (codigoTipoDespesa == null) {
			if (other.codigoTipoDespesa != null)
				return false;
		} else if (!codigoTipoDespesa.equals(other.codigoTipoDespesa))
			return false;
		return true;
	}
}

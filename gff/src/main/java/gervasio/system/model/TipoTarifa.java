/**
 * 
 */
package gervasio.system.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Éderson Gervásio
 *
 */
@Entity
@Table(name="TTIPOTARIFA")
public class TipoTarifa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoTipoTarifa;
	private String nome;
	private String descricao;
	
	public Long getCodigoTipoTarifa() {
		return codigoTipoTarifa;
	}
	public void setCodigoTipoTarifa(Long codigoTipoTarifa) {
		this.codigoTipoTarifa = codigoTipoTarifa;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoTipoTarifa == null) ? 0 : codigoTipoTarifa.hashCode());
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
		TipoTarifa other = (TipoTarifa) obj;
		if (codigoTipoTarifa == null) {
			if (other.codigoTipoTarifa != null)
				return false;
		} else if (!codigoTipoTarifa.equals(other.codigoTipoTarifa))
			return false;
		return true;
	}
}

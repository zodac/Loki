package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mcc_mnc database table.
 * 
 */
@Entity
@NamedQuery(name="MccMnc.findAll", query="SELECT m FROM MccMnc m")
public class MccMnc implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MccMncPK id;

	@Column(length=255)
	private String country;

	@Column(length=255)
	private String operator;

	//bi-directional many-to-one association to Callfailure
	@OneToMany(mappedBy="mccMnc", fetch=FetchType.EAGER)
	private List<CallFailure> callFailures;

	public MccMnc() {
	}

	public MccMncPK getId() {
		return this.id;
	}

	public void setId(MccMncPK id) {
		this.id = id;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}
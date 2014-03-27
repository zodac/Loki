package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the uetype database table.
 * 
 */
@Entity
@NamedQuery(name="UEType.findAll", query="SELECT u FROM UEType u")
public class UEType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int tac;

	@Column(length=255)
	private String accessCapability;

	@Column(length=255)
	private String inputMode;

	@Column(length=255)
	private String manufacturer;

	@Column(length=255)
	private String marketingName;

	@Column(length=255)
	private String model;

	@Column(length=255)
	private String os;

	@Column(length=255)
	private String ueType;

	@Column(length=255)
	private String vendorName;

	//bi-directional many-to-one association to Callfailure
	@OneToMany(mappedBy="ueType")
	private List<CallFailure> callFailures;

	public UEType() {
	}

	public int getTac() {
		return this.tac;
	}

	public void setTac(int tac) {
		this.tac = tac;
	}

	public String getAccessCapability() {
		return this.accessCapability;
	}

	public void setAccessCapability(String accessCapability) {
		this.accessCapability = accessCapability;
	}

	public String getInputMode() {
		return this.inputMode;
	}

	public void setInputMode(String inputMode) {
		this.inputMode = inputMode;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getMarketingName() {
		return this.marketingName;
	}

	public void setMarketingName(String marketingName) {
		this.marketingName = marketingName;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOs() {
		return this.os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getUEType() {
		return this.ueType;
	}

	public void setUEType(String ueType) {
		this.ueType = ueType;
	}

	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
}
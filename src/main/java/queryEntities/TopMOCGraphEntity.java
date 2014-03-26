package queryEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;

/**
 * The persistent class for the user database table.
 * 
 */
public class TopMOCGraphEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=255)
	private int cellId;

	@Column(length=255)
	private String country;
	
	@Column(length=255)
	private String operator;
	
	private BigInteger numberOfFailures;
	
	private BigDecimal ofAllFailures;

	public TopMOCGraphEntity() {
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public BigInteger getNumberOfFailures() {
		return numberOfFailures;
	}

	public void setNumberOfFailures(BigInteger numberOfFailures) {
		this.numberOfFailures = numberOfFailures;
	}

	public BigDecimal getOfAllFailures() {
		return ofAllFailures;
	}

	public void setOfAllFailures(BigDecimal ofAllFailures) {
		this.ofAllFailures = ofAllFailures;
	}
}
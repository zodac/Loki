package queryEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class TopMOCEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private int cellId;
	private String country;
	private String operator;
	private BigInteger numberOfFailures;
	private BigDecimal ofAllFailures;

	public TopMOCEntity() {
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
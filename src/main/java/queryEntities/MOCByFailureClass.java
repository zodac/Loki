package queryEntities;

import java.io.Serializable;
import java.math.BigInteger;

public class MOCByFailureClass implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int cellId;
	private String country;
	private int failureClass;
	private BigInteger occurences;
	private String operator;
	
	public MOCByFailureClass() {
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

	public int getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(int failureClass) {
		this.failureClass = failureClass;
	}

	public BigInteger getOccurences() {
		return occurences;
	}

	public void setOccurences(BigInteger occurences) {
		this.occurences = occurences;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	
	
}

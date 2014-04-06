package queryEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class TopIMSIByFailureClass implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private BigInteger count;
	private int failureClass;
	private BigDecimal IMSI;
	
	public BigInteger getCount() {
		return count;
	}
	public void setCount(BigInteger count) {
		this.count = count;
	}
	public int getFailureClass() {
		return failureClass;
	}
	public void setFailureClass(int failureClass) {
		this.failureClass = failureClass;
	}
	public BigDecimal getIMSI() {
		return IMSI;
	}
	public void setIMSI(BigDecimal iMSI) {
		IMSI = iMSI;
	}



}

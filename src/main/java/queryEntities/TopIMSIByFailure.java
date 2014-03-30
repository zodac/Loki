package queryEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class TopIMSIByFailure implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private BigDecimal IMSI;
	
	private BigInteger NumofFailures;

	public TopIMSIByFailure() {
	}

	public BigDecimal getIMSI() {
		return IMSI;
	}

	public void setIMSI(BigDecimal iMSI) {
		IMSI = iMSI;
	}

	public BigInteger getNumofFailures() {
		return NumofFailures;
	}

	public void setNumofFailures(BigInteger numofFailures) {
		NumofFailures = numofFailures;
	}
	
	
	

}

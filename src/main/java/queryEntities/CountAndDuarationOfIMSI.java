package queryEntities;

import java.io.Serializable;
import java.math.BigInteger;

public class CountAndDuarationOfIMSI implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BigInteger IMSI;
	private Long count;
	private Long NumofFailures;
	
	public CountAndDuarationOfIMSI(){
	}

	public BigInteger getIMSI() {
		return IMSI;
	}

	public void setIMSI(BigInteger obj) {
		IMSI = obj;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long obj) {
		this.count = obj;
	}

	public Long getNumofFailures() {
		return NumofFailures;
	}

	public void setNumofFailures(Long obj) {
		NumofFailures = obj;
	}
	
	

}

package queryEntities;

import java.io.Serializable;
import java.math.BigInteger;

public class EventIdCauseCodeCombo implements Serializable {
	private static final long serialVersionUID = 1L;
		
	private int event_ID;

	private int cause_Code;
	
	private BigInteger occurrences;

	public EventIdCauseCodeCombo() {
	}

	public int getEvent_ID() {
		return event_ID;
	}

	public void setEvent_ID(int event_ID) {
		this.event_ID = event_ID;
	}

	public int getCause_Code() {
		return cause_Code;
	}

	public void setCause_Code(int cause_Code) {
		this.cause_Code = cause_Code;
	}

	public BigInteger getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(BigInteger obj) {
		this.occurrences = obj;
	}
	
	

}

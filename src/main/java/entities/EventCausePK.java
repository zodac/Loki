package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the eventcause database table.
 * 
 */
@Embeddable
public class EventCausePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private int a_Event_ID;

	@Column
	private int b_Cause_Code;

	public EventCausePK() {
	}
	public int getA_Event_ID() {
		return this.a_Event_ID;
	}
	public void setA_Event_ID(int a_Event_ID) {
		this.a_Event_ID = a_Event_ID;
	}
	public int getB_Cause_Code() {
		return this.b_Cause_Code;
	}
	public void setB_Cause_Code(int b_Cause_Code) {
		this.b_Cause_Code = b_Cause_Code;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.a_Event_ID;
		hash = hash * prime + this.b_Cause_Code;
		
		return hash;
	}
}
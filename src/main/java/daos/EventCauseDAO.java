package daos;

import entities.EventCause;
import entities.EventCausePK;

public interface EventCauseDAO {
	void addEventCause(EventCause ec);
	EventCause getEventCause(EventCausePK epk);
	long getNumberOfEventCauses();
	boolean doesEventCauseExist(EventCausePK ecpk);
}

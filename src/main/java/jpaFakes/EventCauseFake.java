package jpaFakes;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Alternative;

import daos.EventCauseDAO;
import entities.EventCause;
import entities.EventCausePK;

@Alternative
public class EventCauseFake implements EventCauseDAO{
	private List<EventCause> eventCauses = new ArrayList<>();

	@Override
	public boolean addEventCause(EventCause ec) {
		return eventCauses.add(ec);
	}

	@Override
	public EventCause getEventCause(EventCausePK epk) {
		for(EventCause eventCause : eventCauses){
			if(eventCause.getId().equals(epk)){
				return eventCause;
			}
		}
		return null;
	}

	@Override
	public long getNumberOfEventCauses() {
		return eventCauses.size();
	}

	@Override
	public boolean doesEventCauseExist(EventCausePK ecpk) {
		for(EventCause eventCause : eventCauses){
			if(eventCause.getId().equals(ecpk)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void removeEventCause(EventCausePK epk) {
		int index = -1;
		for(int i = 0; i < eventCauses.size(); i++){
			if(eventCauses.get(i).getId().equals(epk)){
				index = i;
			}
		}
		
		if(index != -1){
			eventCauses.remove(index);
		}
	}
}
package jpas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import daos.EventCauseDAO;
import entities.EventCause;
import entities.EventCausePK;

@JPA
@SuppressWarnings("unchecked")
public class EventCauseJPA implements EventCauseDAO {

	@PersistenceContext
	private EntityManager em;

	public void addEventCause(EventCause ec) {
		if(em.find(EventCause.class, ec.getId()) == null){
			em.persist(ec);
		}
	}

	public long getNumberOfEventCauses() {
		Query query = em.createQuery("from EventCause");
		List<EventCause> eventcauses = (List<EventCause>) query.getResultList(); 
		return eventcauses.size();
	}

	public boolean doesEventCauseExist(EventCausePK ecpk) {
		return (em.find(EventCause.class, ecpk) != null);		
	}

	public EventCause getEventCause(EventCausePK epk) {
		return em.find(EventCause.class, epk);	
	}
}

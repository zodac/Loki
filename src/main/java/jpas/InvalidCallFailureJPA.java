package jpas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import daos.InvalidCallFailureDAO;
import entities.InvalidCallFailure;

@JPA
@SuppressWarnings("unchecked")
public class InvalidCallFailureJPA implements InvalidCallFailureDAO {

	@PersistenceContext
	private EntityManager em;

	public void addInvalidCallFailure(InvalidCallFailure icf) {
		Query query = em.createQuery("from InvalidCallFailure");
		List<InvalidCallFailure> invalidcallfailures = (List<InvalidCallFailure>) query.getResultList(); 
		if (!invalidcallfailures.contains(icf)){
			em.persist(icf);
		}
	}

	public long getNumberOfInvalidCallFailures() {
		Query query = em.createQuery("from InvalidCallFailure");
		List<InvalidCallFailure> invalidcallfailures = (List<InvalidCallFailure>) query.getResultList(); 
		return invalidcallfailures.size();
	}
}
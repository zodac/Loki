package jpas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import daos.InvalidCallFailureDAO;
import entities.InvalidCallFailure;

@JPA
@SuppressWarnings("unchecked")
public class InvalidCallFailureJPA implements InvalidCallFailureDAO {

	@PersistenceContext
	private EntityManager em;

	public void addInvalidCallFailure(InvalidCallFailure icf) {
		em.persist(icf);
	}
	
	public void addManyInvalidCallFailures(List<InvalidCallFailure> invalidCallFailures){
		for(InvalidCallFailure invalidCallFailure : invalidCallFailures){
			em.persist(invalidCallFailure);
		}
	}

	public long getNumberOfInvalidCallFailures() {
		return em.createNativeQuery("from InvalidCallFailure").getResultList().size();
	}
	
	public List<InvalidCallFailure> getAllInvalidCallFailures(){
		return (List<InvalidCallFailure>) em.createNamedQuery("InvalidCallFailure.findAll").getResultList();
	}
}
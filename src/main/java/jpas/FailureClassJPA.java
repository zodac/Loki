package jpas;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import daos.FailureClassDAO;
import entities.FailureClass;

@JPA
@SuppressWarnings("unchecked")
public class FailureClassJPA implements FailureClassDAO {

	@PersistenceContext
	private EntityManager em;
	
	public boolean addFailureClass(FailureClass fc) {
		if(em.find(FailureClass.class, fc.getFailureClass()) == null){
			em.persist(fc);
			return true;
		}
		return false;
	}

	public long getNumberOfFailureClasses() {
		Query query = em.createQuery("from FailureClass");
		List<FailureClass> failureClasses = (List<FailureClass>) query.getResultList(); 
		return failureClasses.size();
	}

	public boolean doesFailureClassExist(int failureClassId) {
		return (em.find(FailureClass.class, failureClassId) != null);
	}

	public FailureClass getFailureClass(int failureClassId) {
		return em.find(FailureClass.class, failureClassId);
	}

	public long getFailureClassCount(int failureClassId) {
		return ((BigInteger) em.createNativeQuery("SELECT COUNT(*) FROM FailureClass WHERE failureClass= ?")
				.setParameter(1, failureClassId).getSingleResult()).longValue();
	}

	@Override
	public String[] getAllFailureClassesAndDescriptions() {
		List<Object[]> failureClasses = (List<Object[]>) em.createNativeQuery("SELECT * FROM FailureClass").getResultList();
		int size = failureClasses.size();
		String[] results = new String[size];
		
		for(int i = 0; i < size; i++){
			Object[] fc = failureClasses.get(i);
			results[i] = fc[0] + " - " + fc[1];
		}
		return results;
	}
}
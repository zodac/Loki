package jpas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import daos.UETypeDAO;
import entities.UEType;

@JPA
@SuppressWarnings("unchecked")
public class UETypeJPA implements UETypeDAO {

	@PersistenceContext
	private EntityManager em;

	public void addUEType(UEType uet) {
		Query query = em.createQuery("from UEType");
		List<UEType> ueTypes = (List<UEType>) query.getResultList(); 
		if (!ueTypes.contains(uet)){
			em.persist(uet);
		}
	}

	public long getNumberOfUETypes() {
		Query query = em.createQuery("from UEType");
		List<UEType> uetypes = (List<UEType>) query.getResultList(); 
		return uetypes.size();
	}

	public boolean doesUETypeExist(int tac) {
		return (em.find(UEType.class, tac) != null);		
	}

	public UEType getUEType(int tac) {
		return em.find(UEType.class, tac);
	}

	public UEType getUETypeByModel(String model) {
		Query query = em.createNativeQuery("SELECT * FROM UEType WHERE model = ?").setParameter(1, model);
		List<Object[]> uetypes = (List<Object[]>) query.getResultList();
		
		if(uetypes.isEmpty()){
			return null;
		}
			
		return getUEType((Integer) uetypes.get(0)[0]);
	}
}

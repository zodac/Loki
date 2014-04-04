package jpas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import daos.MccMncDAO;
import entities.MccMnc;
import entities.MccMncPK;

@JPA
@SuppressWarnings("unchecked")
public class MccMncJPA implements MccMncDAO {

	@PersistenceContext
	private EntityManager em;

	public boolean addMccMnc(MccMnc mccmnc) {
		if(em.find(MccMnc.class, mccmnc.getId()) == null){
			em.persist(mccmnc);
			return true;
		}
		return false;
	}

	public MccMnc getMCC_MNCByMCCAndMNC(MccMnc mccmnc) {
		MccMncPK mpk = new MccMncPK();
		mpk.setMcc(mccmnc.getId().getMcc());
		mpk.setMnc(mccmnc.getId().getMnc());
		List<MccMnc> mcc_mncs = (List<MccMnc>) em
				.createNamedQuery("MCC_MNC.findByMCCANDMNC")
				.setParameter("id", mpk).getResultList();

		return mcc_mncs.get(0);
	}

	public long getNumberOfMccMnc() {
		Query query = em.createQuery("MccMnc.findAll");
		List<MccMnc> mccmncs = (List<MccMnc>) query.getResultList();
		return mccmncs.size();
	}

	public boolean doesMccMncExist(MccMncPK mpk) {
		return (em.find(MccMnc.class, mpk) != null);
	}

	public MccMnc getMCC_MNC(MccMncPK mpk) {
		return em.find(MccMnc.class, mpk);
	}

	public void removeMccMnc(MccMncPK mpk) {
		em.remove(em.find(MccMnc.class, mpk));
	}
}

package jpas;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import queryEntities.TopMOCGraphEntity;
import daos.CallFailureDAO;
import entities.CallFailure;
import entities.EventCause;

@JPA
@SuppressWarnings("unchecked")
public class CallFailureJPA implements CallFailureDAO {

	@PersistenceContext
	private EntityManager em;

	public void addCallFailure(CallFailure cf) {
		Query query = em.createQuery("from CallFailure");
		List<CallFailure> callfailures = (List<CallFailure>) query.getResultList(); 
		if (!callfailures.contains(cf)){
			em.persist(cf);
		}
	}

	public long getNumberOfCallFailures() {
		Query query = em.createQuery("from CallFailure");
		List<CallFailure> callfailures = (List<CallFailure>) query.getResultList(); 
		return callfailures.size();
	}

	public List<Object[]> findUniqueEventCauseAndOccurancesByTAC(int tac) {
		return (List<Object[]>) em.createNativeQuery("SELECT Event_ID, Cause_Code, COUNT(*) FROM CallFailure WHERE UE_Type=? GROUP BY Event_ID, Cause_Code")
													.setParameter(1, tac).getResultList();
	}
	
	public List<Object[]> findNumberOfFailuresAndDuration(Date fromDate, Date toDate){
		return (List<Object[]>) em.createNamedQuery("CallFailure.NumOfFailuresAndDuration")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate).getResultList();
	}

	public List<BigInteger> findAllIMSIsByTimePeriod(Date fromDate, Date toDate) {
		return (List<BigInteger>) em.createNamedQuery("CallFailure.AllIMSIsByTimePeriod").setParameter("fromDate",fromDate).setParameter("toDate",toDate).getResultList();
	}

	public List<BigInteger> findNumberOfFailuresByModelAndTimePeriod(String model, Date fromDate, Date toDate) {
		return (List<BigInteger>) em.createNamedQuery("CallFailure.FailuresByModelOverTime")
				.setParameter("model", model).setParameter("fromDate",fromDate).setParameter("toDate",toDate).getResultList();
	}

	public long findIMSICount(long imsi) {
		return ((BigInteger) em.createNativeQuery("SELECT COUNT(IMSI) FROM CallFailure WHERE IMSI= ?;").setParameter(1, imsi).getSingleResult()).longValue();
	}

	public List<EventCause> findUniqueEventCauseByIMSI(long imsi) {
		return (List<EventCause>) em.createNamedQuery("CallFailure.EventCauseByIMSI").setParameter("imsi", BigInteger.valueOf(imsi)).getResultList();
	}

	public List<Integer> findUniqueCauseCodesByIMSI(long imsi) {
		return (List<Integer>) em.createNativeQuery("SELECT DISTINCT Cause_Code FROM CallFailure WHERE IMSI= ? ORDER BY Cause_Code;")
				.setParameter(1, BigInteger.valueOf(imsi)).getResultList();
	}

	public List<Long> numberOfFailuresByIMSIByTimePeriod(long imsi, Date fromDate, Date toDate) {
		return (List<Long>) em.createNamedQuery("CallFailure.NumOfFailuresByIMSIByTimePeriod")
				.setParameter("imsi", BigInteger.valueOf(imsi)).setParameter("fromDate",fromDate).setParameter("toDate",toDate).getResultList();
	}

	public List<TopMOCGraphEntity> getTopTenMOCGraphical() {
		List<Object[]> results =  (List<Object[]>) em.createNativeQuery("SELECT t1.cellId, t2.country, t2.operator, count(*) AS 'NumberOfFailures',"
													+ " round((count(*)/(SELECT count(*) FROM CallFailure) * 100),2) AS '% of all Failures'"
													+ " FROM CallFailure t1, MccMnc t2 WHERE t2.mcc=t1.market AND t2.mnc=t1.operator GROUP BY t1.cellId, t1.market, t1.operator"
													+ " ORDER BY NumberOfFailures DESC LIMIT 10").getResultList();
		
		List<TopMOCGraphEntity> entities = new ArrayList<TopMOCGraphEntity>();
		
		for(Object[] obj : results){
			TopMOCGraphEntity top = new TopMOCGraphEntity();
			
			top.setCellId((Integer) obj[0]);
			top.setCountry(String.valueOf(obj[1]));
			top.setOperator(String.valueOf(obj[2]));
			top.setNumberOfFailures((BigInteger) obj[3]);
			top.setOfAllFailures((BigDecimal) obj[4]);
			
			entities.add(top);
		}
		
		return entities;
	}
}

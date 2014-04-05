package jpas;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import queryEntities.CountAndDuarationOfIMSI;
import queryEntities.EventIdCauseCodeCombo;
import queryEntities.MOCByFailureClass;
import queryEntities.TopIMSIByFailure;
import queryEntities.TopMOCEntity;
import daos.CallFailureDAO;
import entities.CallFailure;
import entities.EventCause;

@JPA
@SuppressWarnings("unchecked")
public class CallFailureJPA implements CallFailureDAO {	
	@PersistenceContext
	private EntityManager em;

	public void addCallFailure(CallFailure cf) {
		em.persist(cf);
	}
	
	public void addManyCallFailures(List<CallFailure> callFailures){
		for(CallFailure callFailure : callFailures){
			em.persist(callFailure);
		}
	}

	public long getNumberOfCallFailures() {
		Query query = em.createQuery("from CallFailure");
		List<CallFailure> callfailures = (List<CallFailure>) query.getResultList();
		return callfailures.size();
	}

	public List<EventIdCauseCodeCombo> findUniqueEventCauseAndOccurancesByTAC(int tac) {
		List<Object[]> results = (List<Object[]>) em
				.createNativeQuery("SELECT Event_ID, Cause_Code, COUNT(*) FROM CallFailure WHERE UE_Type=? GROUP BY Event_ID, Cause_Code")
				.setParameter(1, tac).getResultList();
		
		List<EventIdCauseCodeCombo> entities = new ArrayList<EventIdCauseCodeCombo>();
		
		for (Object[] obj : results) {
			EventIdCauseCodeCombo top = new EventIdCauseCodeCombo();
			top.setEvent_ID((Integer) obj[0]);
			top.setCause_Code((Integer) obj[1]);
			top.setOccurrences((BigInteger) obj[2]);
			entities.add(top);
		}
		return entities;
	}

	public List<CountAndDuarationOfIMSI> findNumberOfFailuresAndDuration(Date fromDate,
			Date toDate) {
		List<Object[]> results = (List<Object[]>) em
				.createNamedQuery("CallFailure.NumOfFailuresAndDuration")
				.setParameter("fromDate", fromDate)
				.setParameter("toDate", toDate).getResultList();
		
		List<CountAndDuarationOfIMSI> entities = new ArrayList<CountAndDuarationOfIMSI>();
		
		for (Object[] obj : results) {
			CountAndDuarationOfIMSI top = new CountAndDuarationOfIMSI();
			top.setIMSI((BigInteger)obj[0]);
			top.setCount((Long)obj[1]);
			top.setNumofFailures((Long)obj[2]);
			entities.add(top);
		}
		return entities;	
	}

	public List<BigInteger> findAllIMSIsByTimePeriod(Date fromDate, Date toDate) {
		return (List<BigInteger>) em
				.createNamedQuery("CallFailure.AllIMSIsByTimePeriod")
				.setParameter("fromDate", fromDate)
				.setParameter("toDate", toDate).getResultList();
	}

	public List<BigInteger> findNumberOfFailuresByModelAndTimePeriod(
			String model, Date fromDate, Date toDate) {
		return (List<BigInteger>) em
				.createNamedQuery("CallFailure.FailuresByModelOverTime")
				.setParameter("model", model)
				.setParameter("fromDate", fromDate)
				.setParameter("toDate", toDate).getResultList();
	}

	public long findIMSICount(long imsi) {
		return ((BigInteger) em
				.createNativeQuery(
						"SELECT COUNT(IMSI) FROM CallFailure WHERE IMSI= ?")
				.setParameter(1, imsi).getSingleResult()).longValue();
	}

	public List<EventCause> findUniqueEventCauseByIMSI(long imsi) {
		return (List<EventCause>) em
				.createNamedQuery("CallFailure.EventCauseByIMSI")
				.setParameter("imsi", BigInteger.valueOf(imsi)).getResultList();
	}

	public List<Integer> findUniqueCauseCodesByIMSI(long imsi) {
		return (List<Integer>) em
				.createNativeQuery(
						"SELECT DISTINCT Cause_Code FROM CallFailure WHERE IMSI= ? ORDER BY Cause_Code")
				.setParameter(1, BigInteger.valueOf(imsi)).getResultList();
	}

	public List<Long> numberOfFailuresByIMSIByTimePeriod(long imsi,
			Date fromDate, Date toDate) {
		return (List<Long>) em
				.createNamedQuery("CallFailure.NumOfFailuresByIMSIByTimePeriod")
				.setParameter("imsi", BigInteger.valueOf(imsi))
				.setParameter("fromDate", fromDate)
				.setParameter("toDate", toDate).getResultList();
	}

	public List<TopMOCEntity> getTopTenMOCGraphical() {
		List<Object[]> results = (List<Object[]>) em
				.createNativeQuery(
						"SELECT t1.cellId, t2.country, t2.operator, count(*) AS 'NumberOfFailures',"
								+ " round((count(*)/(SELECT count(*) FROM CallFailure) * 100),2) AS '% of all Failures'"
								+ " FROM CallFailure t1, MccMnc t2 WHERE t2.mcc=t1.market AND t2.mnc=t1.operator GROUP BY t1.cellId, t1.market, t1.operator"
								+ " ORDER BY NumberOfFailures DESC LIMIT 10")
				.getResultList();

		List<TopMOCEntity> entities = new ArrayList<TopMOCEntity>();

		for (Object[] obj : results) {
			TopMOCEntity top = new TopMOCEntity();

			top.setCellId((Integer) obj[0]);
			top.setCountry(String.valueOf(obj[1]));
			top.setOperator(String.valueOf(obj[2]));
			top.setNumberOfFailures((BigInteger) obj[3]);
			top.setOfAllFailures((BigDecimal) obj[4]);

			entities.add(top);
		}

		return entities;
	}

	public List<TopMOCEntity> getTopTenMOC(Date fromDate, Date toDate) {
		List<Object[]> results = (List<Object[]>) em
				.createNativeQuery(
						"SELECT t1.cellId, t2.country, t2.operator, count(*)"
								+ " FROM CallFailure t1, MccMnc t2"
								+ " WHERE t2.mcc=t1.market AND t2.mnc=t1.operator AND t1.date >= ?1 AND t1.date <= ?2 GROUP BY t1.cellId, t1.market, t1.operator"
								+ " ORDER BY COUNT(*) DESC LIMIT 10")
				.setParameter(1, fromDate).setParameter(2, toDate)
				.getResultList();
		List<TopMOCEntity> entities = new ArrayList<TopMOCEntity>();

		for (Object[] obj : results) {
			TopMOCEntity top = new TopMOCEntity();

			top.setCellId((Integer) obj[0]);
			top.setCountry(String.valueOf(obj[1]));
			top.setOperator(String.valueOf(obj[2]));
			top.setNumberOfFailures((BigInteger) obj[3]);

			entities.add(top);
		}

		return entities;
	}

	public List<BigInteger> findIMSIsByFailureClass(int failureClassId) {
		return (List<BigInteger>) em.createNativeQuery("SELECT imsi FROM CallFailure WHERE Failure_Class= ? GROUP BY imsi ORDER BY imsi;")
									.setParameter(1, failureClassId).getResultList();
	}

	public List<TopIMSIByFailure> getTopTenIMSI(Date fromDate, Date toDate) {
		List<Object[]> results = (List<Object[]>) em.createNativeQuery("SELECT imsi, COUNT(imsi) FROM CallFailure"
														+ " WHERE date >= ?1 AND date <= ?2 GROUP BY imsi ORDER BY COUNT(imsi) DESC LIMIT 10")
				.setParameter(1, fromDate)
				.setParameter(2, toDate).getResultList();
		
		List<TopIMSIByFailure> entities = new ArrayList<TopIMSIByFailure>();

		for (Object[] obj : results) {
			TopIMSIByFailure top = new TopIMSIByFailure();

			top.setIMSI((BigDecimal) obj[0]);
			top.setNumofFailures((BigInteger) obj[1]);

			entities.add(top);
		}

		return entities;
	}

	public long[] getAllIMSIs() {
		List<Object> imsis = (List<Object>) em.createNativeQuery("SELECT DISTINCT imsi FROM CallFailure").getResultList();
		int size = imsis.size();
		long[] results = new long[size];
		
		for(int i = 0; i < size; i++){
			results[i] = ((BigDecimal) imsis.get(i)).longValue();
		}
		return results;
	}	
	
	public List<MOCByFailureClass> getMOCGraphicalByFailureClass() {
		List<Object[]> results = (List<Object[]>) em
				.createNativeQuery(
						"SELECT t1.cellId, t2.country, t1.Failure_Class, count(*) as Occurences,  t2.operator"
						+ " FROM CallFailure t1, MccMnc t2 WHERE t2.mcc=t1.market AND t2.mnc=t1.operator GROUP BY"
						+ " t1.cellId, t1.market, t1.operator, t1.Failure_Class order by count(*) desc")
				.getResultList();

		List<MOCByFailureClass> entities = new ArrayList<MOCByFailureClass>();

		for (Object[] obj : results) {
			MOCByFailureClass top = new MOCByFailureClass();

			top.setCellId((Integer) obj[0]);
			top.setCountry(String.valueOf(obj[1]));
			top.setFailureClass((Integer) obj[2]);
			top.setOccurences((BigInteger) obj[3]);
			top.setOperator(String.valueOf(obj[4]));
			entities.add(top);
		}

		return entities;
	}
}
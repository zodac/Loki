package daos;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import queryEntities.TopMOCEntity;
import entities.CallFailure;
import entities.EventCause;

public interface CallFailureDAO {
	void addCallFailure(CallFailure cf);
	long getNumberOfCallFailures();
	long findIMSICount(long imsi);
	List<Object[]> findUniqueEventCauseAndOccurancesByTAC(int tac);
	List<Object[]> findNumberOfFailuresAndDuration(Date fromDate, Date toDate);
	List<BigInteger> findAllIMSIsByTimePeriod(Date fromDate, Date toDate);
	List<BigInteger> findNumberOfFailuresByModelAndTimePeriod(String model, Date fromDate, Date toDate);
	List<EventCause> findUniqueEventCauseByIMSI(long imsi);
	List<Integer> findUniqueCauseCodesByIMSI(long imsi);
	List<Long> numberOfFailuresByIMSIByTimePeriod(long imsi, Date fromDate, Date toDate);
	List<TopMOCEntity> getTopTenMOCGraphical();
	List<Object[]> getTopTenMOC(Date fromDate, Date toDate);
}

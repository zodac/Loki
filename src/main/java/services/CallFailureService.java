package services;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebService;

import entities.EventCause;
@WebService
@Remote
public interface CallFailureService {
	long findIMSICount(long imsi);
	List<Object[]> findUniqueEventCauseAndOccurancesByTAC(int tac);
	List<Object[]> findNumberOfFailuresAndDuration(Date fromDate, Date toDate);
	List<BigInteger> findAllIMSIsByTimePeriod(Date fromDate, Date toDate);
	List<BigInteger> findNumberOfFailuresByModelAndTimePeriod(String model, Date fromDate, Date toDate);
	List<EventCause> findUniqueEventCauseByIMSI(long imsi);
	List<Integer> findUniqueCauseCodesByIMSI(long imsi);
	List<Long> numberOfFailuresByIMSIByTimePeriod(long imsi, Date fromDate, Date toDate);
}

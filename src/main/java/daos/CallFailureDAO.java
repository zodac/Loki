package daos;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import queryEntities.CountAndDuarationOfIMSI;
import queryEntities.EventIdCauseCodeCombo;
import queryEntities.MOCByFailureClass;
import queryEntities.TopIMSIByFailure;
import queryEntities.TopMOCEntity;
import entities.CallFailure;
import entities.EventCause;

public interface CallFailureDAO {
	void addCallFailure(CallFailure cf);
	void addManyCallFailures(List<CallFailure> callFailures);
	long getNumberOfCallFailures();
	long findIMSICount(long imsi);
	List<EventIdCauseCodeCombo> findUniqueEventCauseAndOccurancesByTAC(int tac);
	List<CountAndDuarationOfIMSI> findNumberOfFailuresAndDuration(Date fromDate, Date toDate);
	List<BigInteger> findAllIMSIsByTimePeriod(Date fromDate, Date toDate);
	List<BigInteger> findNumberOfFailuresByModelAndTimePeriod(String model, Date fromDate, Date toDate);
	List<EventCause> findUniqueEventCauseByIMSI(long imsi);
	List<Integer> findUniqueCauseCodesByIMSI(long imsi);
	List<Long> numberOfFailuresByIMSIByTimePeriod(long imsi, Date fromDate, Date toDate);
	List<TopMOCEntity> getTopTenMOCGraphical();
	List<TopMOCEntity> getTopTenMOC(Date fromDate, Date toDate);
	List<BigInteger> findIMSIsByFailureClass(int failureClassId);
	List<TopIMSIByFailure> getTopTenIMSI(Date fromDate, Date toDate);
	long[] getAllIMSIs();
	List<MOCByFailureClass> getMOCGraphicalByFailureClass();
}


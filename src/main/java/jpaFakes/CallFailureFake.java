package jpaFakes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Alternative;

import queryEntities.CountAndDuarationOfIMSI;
import queryEntities.EventIdCauseCodeCombo;
import queryEntities.MOCByFailureClass;
import queryEntities.TopIMSIByFailure;
import queryEntities.TopIMSIByFailureClass;
import queryEntities.TopMOCEntity;
import daos.CallFailureDAO;
import entities.CallFailure;
import entities.EventCause;

@Alternative
public class CallFailureFake implements CallFailureDAO{
	private List<CallFailure> callFailures = new ArrayList<>();
	
	public void addCallFailure(CallFailure cf) {
		callFailures.add(cf);
	}

	public void addManyCallFailures(List<CallFailure> callFailures) {
		for(CallFailure callFailure : callFailures){
			this.callFailures.add(callFailure);
		}
	}

	
	public long getNumberOfCallFailures() {
		return callFailures.size();
	}

	
	public long findIMSICount(long imsi) {
		long count = 0;
		for(CallFailure callFailure : callFailures){
			if((callFailure.getImsi()).longValue() == imsi){
				count++;
			}
		}
		return count;
	}

	
	public List<EventIdCauseCodeCombo> findUniqueEventCauseAndOccurancesByTAC(int tac) {
		return new ArrayList<EventIdCauseCodeCombo>();
	}

	
	public List<CountAndDuarationOfIMSI> findNumberOfFailuresAndDuration(Date fromDate, Date toDate) {
		return new ArrayList<CountAndDuarationOfIMSI>();
	}

	
	public List<BigInteger> findAllIMSIsByTimePeriod(Date fromDate, Date toDate) {
		return new ArrayList<BigInteger>();
	}

	
	public List<BigInteger> findNumberOfFailuresByModelAndTimePeriod(String model, Date fromDate, Date toDate) {
		return new ArrayList<BigInteger>();
	}

	
	public List<EventCause> findUniqueEventCauseByIMSI(long imsi) {
		return new ArrayList<EventCause>();
	}

	
	public List<Integer> findUniqueCauseCodesByIMSI(long imsi) {
		return new ArrayList<Integer>();
	}

	
	public List<Long> numberOfFailuresByIMSIByTimePeriod(long imsi, Date fromDate, Date toDate) {
		return new ArrayList<Long>();
	}

	
	public List<TopMOCEntity> getTopTenMOCGraphical() {
		return new ArrayList<TopMOCEntity>();
	}

	
	public List<TopMOCEntity> getTopTenMOC(Date fromDate, Date toDate) {
		return new ArrayList<TopMOCEntity>();
	}

	
	public List<BigInteger> findIMSIsByFailureClass(int failureClassId) {
		return new ArrayList<BigInteger>();
	}

	
	public List<TopIMSIByFailure> getTopTenIMSI(Date fromDate, Date toDate) {
		return new ArrayList<TopIMSIByFailure>();
	}
	
	public long[] getAllIMSIs() {
		int size = callFailures.size();
		long[] results = new long[size];
		
		for(int i = 0; i < size; i++){
			results[i] = callFailures.get(i).getImsi().longValue();
		}
		return results;
	}
	
	public List<MOCByFailureClass> getMOCGraphicalByFailureClass() {
		return new ArrayList<MOCByFailureClass>();
	}

	
	public List<MOCByFailureClass> getTopTenMOCByFailureClass(Date fromDate, Date toDate) {
		return new ArrayList<MOCByFailureClass>();
	}

	
	public List<TopIMSIByFailureClass> getFailureClassesOfIMSI(Date fDate, Date tDate) {
		return new ArrayList<TopIMSIByFailureClass>();
	}
}
package services;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import jpas.JPA;
import queryEntities.TopMOCEntity;
import daos.CallFailureDAO;
import entities.EventCause;
@Stateless
@WebService(endpointInterface="services.CallFailureService")
@Remote(CallFailureService.class)

public class CallFailureEJB implements CallFailureService {
	private CallFailureDAO dao;

	@Inject
	public void setDao(@JPA CallFailureDAO dao) {
		this.dao = dao;
	}

	public List<Object[]> findUniqueEventCauseAndOccurancesByTAC(int tac) {
		return dao.findUniqueEventCauseAndOccurancesByTAC(tac);
	}
	
	public List<Object[]> findNumberOfFailuresAndDuration(Date fromDate, Date toDate){
		return dao.findNumberOfFailuresAndDuration(fromDate, toDate);
	}

	public List<BigInteger> findAllIMSIsByTimePeriod(Date fromDate, Date toDate) {
		return dao.findAllIMSIsByTimePeriod(fromDate, toDate);
	}

	public List<BigInteger> findNumberOfFailuresByModelAndTimePeriod(String model, Date fromDate, Date toDate) {
		return dao.findNumberOfFailuresByModelAndTimePeriod(model, fromDate, toDate);
	}

	public long findIMSICount(long imsi) {
		return dao.findIMSICount(imsi);
	}

	public List<EventCause> findUniqueEventCauseByIMSI(long imsi) {
		return dao.findUniqueEventCauseByIMSI(imsi);
	}

	public List<Integer> findUniqueCauseCodesByIMSI(long imsi) {
		return dao.findUniqueCauseCodesByIMSI(imsi);
	}

	public List<Long> numberOfFailuresByIMSIByTimePeriod(long imsi, Date fromDate, Date toDate) {
		return dao.numberOfFailuresByIMSIByTimePeriod(imsi, fromDate, toDate);
	}

	@Override
	public List<TopMOCEntity> getTopTenMOCGraphical() {
		return dao.getTopTenMOCGraphical();
	}

	@Override
	public List<Object[]> getTopTenMOC(Date fromDate, Date toDate) {
		return dao.getTopTenMOC(fromDate, toDate);
	}
}

package services;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import jpas.JPA;
import queryEntities.CountAndDuarationOfIMSI;
import queryEntities.EventIdCauseCodeCombo;
import queryEntities.TopIMSIByFailure;
import queryEntities.TopMOCEntity;
import queryEntities.MOCByFailureClass;
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

	public List<EventIdCauseCodeCombo> findUniqueEventCauseAndOccurancesByTAC(int tac) {
		return dao.findUniqueEventCauseAndOccurancesByTAC(tac);
	}
	
	public List<CountAndDuarationOfIMSI> findNumberOfFailuresAndDuration(Date fromDate, Date toDate){
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

	public List<TopMOCEntity> getTopTenMOCGraphical() {
		return dao.getTopTenMOCGraphical();
	}

	public List<TopMOCEntity> getTopTenMOC(Date fromDate, Date toDate) {
		return dao.getTopTenMOC(fromDate, toDate);
	}

	public List<BigInteger> findIMSIsByFailureClass(int failureClassId) {
		return dao.findIMSIsByFailureClass(failureClassId);
	}

	public List<TopIMSIByFailure> getTopTenIMSI(Date fDate, Date tDate) {
		return dao.getTopTenIMSI(fDate, tDate);
	}

	public long[] getAllIMSIs() {
		return dao.getAllIMSIs();
	}
	
	public List<MOCByFailureClass> getMOCGraphicalByFailureClass(){
		return dao.getMOCGraphicalByFailureClass();
	}
}


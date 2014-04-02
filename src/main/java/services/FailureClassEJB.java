package services;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import jpas.JPA;
import daos.FailureClassDAO;

@Stateless
@WebService(endpointInterface="services.FailureClassService")
@Remote(FailureClassService.class)
public class FailureClassEJB implements FailureClassService {
	private FailureClassDAO dao;
	
	@Inject
	public void setDao(@JPA FailureClassDAO dao) {
		this.dao = dao;
	}

	@Override
	public long findFailureClassCount(int failureClassId) {
		return dao.getFailureClassCount(failureClassId);
	}

	@Override
	public String[] getAllFailureClassesAndDescriptions() {
		return dao.getAllFailureClassesAndDescriptions();
	}
}

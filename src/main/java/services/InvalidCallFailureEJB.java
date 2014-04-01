package services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import jpas.JPA;
import daos.InvalidCallFailureDAO;
import entities.InvalidCallFailure;
@Stateless
@WebService(endpointInterface="services.InvalidCallFailureService")
@Remote(InvalidCallFailureService.class)

public class InvalidCallFailureEJB implements InvalidCallFailureService {
	private InvalidCallFailureDAO dao;

	@Inject
	public void setDao(@JPA InvalidCallFailureDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<InvalidCallFailure> getAllInvalidCallFailures() {
		return dao.getAllInvalidCallFailures();
	}
}


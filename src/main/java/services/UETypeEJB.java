package services;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import jpas.JPA;
import daos.UETypeDAO;
import entities.UEType;

@Stateless
@WebService(endpointInterface="services.UETypeService")
@Remote(UETypeService.class)
public class UETypeEJB implements UETypeService {
	private UETypeDAO dao;
	
	@Inject
	public void setDao(@JPA UETypeDAO dao) {
		this.dao = dao;
	}

	public UEType getUETypeByModel(String model) {
		return dao.getUETypeByModel(model);
	}

	@Override
	public String[] getAllPhoneModels() {
		return dao.getAllPhoneModels();
	}
}

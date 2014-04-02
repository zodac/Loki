package services;

import javax.ejb.Remote;
import javax.jws.WebService;

import entities.UEType;

@WebService
@Remote
public interface UETypeService {
	UEType getUETypeByModel(String model);
	String[] getAllPhoneModels();
}

package services;

import javax.ejb.Remote;
import javax.jws.WebService;

@WebService
@Remote
public interface FailureClassService {
	 long findFailureClassCount(int failureClassId);
}

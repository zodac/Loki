package services;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebService;

import entities.InvalidCallFailure;
@WebService
@Remote
public interface InvalidCallFailureService {
	List<InvalidCallFailure> getAllInvalidCallFailures();
}


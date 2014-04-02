package daos;

import java.util.List;

import entities.InvalidCallFailure;

public interface InvalidCallFailureDAO {
	void addInvalidCallFailure(InvalidCallFailure icf);
	long getNumberOfInvalidCallFailures();
	List<InvalidCallFailure> getAllInvalidCallFailures();
}

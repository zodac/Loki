package daos;

import entities.InvalidCallFailure;

public interface InvalidCallFailureDAO {
	void addInvalidCallFailure(InvalidCallFailure icf);
	long getNumberOfInvalidCallFailures();
}

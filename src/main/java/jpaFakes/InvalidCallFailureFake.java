package jpaFakes;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Alternative;

import daos.InvalidCallFailureDAO;
import entities.InvalidCallFailure;

@Alternative
public class InvalidCallFailureFake implements InvalidCallFailureDAO{
	private List<InvalidCallFailure> invalidCallFailures = new ArrayList<>();
	
	@Override
	public void addInvalidCallFailure(InvalidCallFailure icf) {
		invalidCallFailures.add(icf);
	}

	@Override
	public long getNumberOfInvalidCallFailures() {
		return invalidCallFailures.size();
	}

	@Override
	public List<InvalidCallFailure> getAllInvalidCallFailures() {
		return invalidCallFailures;
	}

	@Override
	public void addManyInvalidCallFailures(List<InvalidCallFailure> invalidCallFailures) {
		for(InvalidCallFailure invalidCallFailure : invalidCallFailures){
			this.invalidCallFailures.add(invalidCallFailure);
		}
	}
}

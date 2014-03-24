package daos;

import entities.FailureClass;

public interface FailureClassDAO {
	FailureClass getFailureClass(int failureClassId);
	void addFailureClass(FailureClass fc);
	boolean doesFailureClassExist(int failureClassId);
	long getNumberOfFailureClasses();
}

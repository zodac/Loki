package daos;

import entities.FailureClass;

public interface FailureClassDAO {
	FailureClass getFailureClass(int failureClassId);
	boolean addFailureClass(FailureClass fc);
	boolean doesFailureClassExist(int failureClassId);
	long getNumberOfFailureClasses();
	long getFailureClassCount(int failureClassId);
}

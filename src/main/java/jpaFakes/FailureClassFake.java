package jpaFakes;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Alternative;

import daos.FailureClassDAO;
import entities.FailureClass;

@Alternative
public class FailureClassFake implements FailureClassDAO{
	private List<FailureClass> failureClasses = new ArrayList<>();
	
	@Override
	public FailureClass getFailureClass(int failureClassId) {
		for(FailureClass failureClass : failureClasses){
			if(failureClass.getFailureClass() == failureClassId){
				return failureClass;
			}
		}
		return null;
	}

	@Override
	public boolean addFailureClass(FailureClass fc) {
		return failureClasses.add(fc);
	}

	@Override
	public boolean doesFailureClassExist(int failureClassId) {
		for(FailureClass failureClass : failureClasses){
			if(failureClass.getFailureClass() == failureClassId){
				return true;
			}
		}
		return false;
	}

	@Override
	public long getNumberOfFailureClasses() {
		return failureClasses.size();
	}

	@Override
	public long getFailureClassCount(int failureClassId) {
		long count = 0;
		for(FailureClass failureClass : failureClasses){
			if(failureClass.getFailureClass() == failureClassId){
				count++;
			}
		}
		return count;
	}

	@Override
	public String[] getAllFailureClassesAndDescriptions() {
		int size = failureClasses.size();
		String[] results = new String[size];
		
		for(int i = 0; i < size; i++){
			FailureClass fc = failureClasses.get(i);
			results[i] = fc.getClass() + " - " + fc.getDescription(); 
		}
		return results;
	}

	@Override
	public void removeFailureClass(FailureClass fc) {
		int index = -1;
		for(int i = 0; i < failureClasses.size(); i++){
			if(failureClasses.get(i).getFailureClass() == fc.getFailureClass()){
				index = i;
			}
		}
		
		if(index != -1){
			failureClasses.remove(index);
		}
	}
}
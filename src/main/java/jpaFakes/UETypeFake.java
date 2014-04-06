package jpaFakes;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Alternative;

import daos.UETypeDAO;
import entities.UEType;

@Alternative
public class UETypeFake implements UETypeDAO{
	private List<UEType> ueTypes = new ArrayList<>();
	
	@Override
	public UEType getUEType(int tac) {
		for(UEType ueType : ueTypes){
			if(ueType.getTac() == tac){
				return ueType;
			}
		}
		return null;
	}

	@Override
	public boolean addUEType(UEType uet) {
		return ueTypes.add(uet);
	}

	@Override
	public long getNumberOfUETypes() {
		return ueTypes.size();
	}

	@Override
	public boolean doesUETypeExist(int tac) {
		for(UEType ueType : ueTypes){
			if(ueType.getTac() == tac){
				return true;
			}
		}
		return false;
	}

	@Override
	public UEType getUETypeByModel(String model) {
		for(UEType ueType : ueTypes){
			if(ueType.getModel().equals(model)){
				return ueType;
			}
		}
		return null;
	}

	@Override
	public String[] getAllPhoneModels() {
		int size = ueTypes.size();
		String[] results = new String[size];
		
		for(int i = 0; i < size; i++){
			results[i] = ueTypes.get(i).getModel();
		}
		return results;
	}

	@Override
	public void removeUEType(UEType ueType) {
		int index = -1;
		for(int i = 0; i < ueTypes.size(); i++){
			if(ueTypes.get(i).getTac() == ueType.getTac()){
				index = i;
			}
		}
		
		if(index != -1){
			ueTypes.remove(index);
		}
	}
}

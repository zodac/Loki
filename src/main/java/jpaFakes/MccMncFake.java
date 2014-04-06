package jpaFakes;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Alternative;

import daos.MccMncDAO;
import entities.MccMnc;
import entities.MccMncPK;

@Alternative
public class MccMncFake implements MccMncDAO{
	private List<MccMnc> mccMncs = new ArrayList<>();

	@Override
	public MccMnc getMCC_MNCByMCCAndMNC(MccMnc mccmnc) {
		for(MccMnc mccMnc : mccMncs){
			if(mccMnc.getId().equals(mccmnc.getId())){
				return mccMnc;
			}
		}
		return null;
	}

	@Override
	public boolean addMccMnc(MccMnc mccmnc) {
		return mccMncs.add(mccmnc);
	}

	@Override
	public long getNumberOfMccMnc() {
		return mccMncs.size();
	}

	@Override
	public boolean doesMccMncExist(MccMncPK mpk) {
		for(MccMnc mccMnc : mccMncs){
			if(mccMnc.getId().equals(mpk)){
				return true;
			}
		}
		return false;
	}

	@Override
	public MccMnc getMCC_MNC(MccMncPK mpk) {
		for(MccMnc mccMnc : mccMncs){
			if(mccMnc.getId().equals(mpk)){
				return mccMnc;
			}
		}
		return null;
	}

	@Override
	public void removeMccMnc(MccMncPK mpk) {
		int index = -1;
		for(int i = 0; i < mccMncs.size(); i++){
			if(mccMncs.get(i).getId().equals(mpk)){
				index = i;
			}
		}
		
		if(index != -1){
			mccMncs.remove(index);
		}
	}
}
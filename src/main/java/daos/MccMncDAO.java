package daos;

import entities.MccMnc;
import entities.MccMncPK;

public interface MccMncDAO {
	MccMnc getMCC_MNCByMCCAndMNC(MccMnc mccmnc);
	boolean addMccMnc(MccMnc mccmnc);
	long getNumberOfMccMnc();
	boolean doesMccMncExist(MccMncPK mpk);
	MccMnc getMCC_MNC(MccMncPK mpk);
}

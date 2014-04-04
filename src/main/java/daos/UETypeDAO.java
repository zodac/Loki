package daos;

import entities.UEType;

public interface UETypeDAO {
	UEType getUEType(int tac);
	boolean addUEType(UEType uet);
	long getNumberOfUETypes();
	boolean doesUETypeExist(int tac);
	UEType getUETypeByModel(String model);
	String[] getAllPhoneModels();
	void removeUEType(UEType ueType);
}
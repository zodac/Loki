package daos;

import entities.UEType;

public interface UETypeDAO {
	UEType getUEType(int tac);
	void addUEType(UEType uet);
	long getNumberOfUETypes();
	boolean doesUETypeExist(int tac);
	UEType getUETypeByModel(String model);
}

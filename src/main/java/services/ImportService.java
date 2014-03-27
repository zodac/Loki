package services;

import javax.ejb.Remote;
import javax.jws.WebService;
@WebService
@Remote
public interface ImportService {
	public void addToDatabase(Object excelSheet);
}
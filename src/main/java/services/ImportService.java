package services;

import javax.ejb.Remote;
import javax.jws.WebService;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Sheet;
@WebService
@Remote
public interface ImportService {
	public void addToDatabase(Object excelSheet);
}

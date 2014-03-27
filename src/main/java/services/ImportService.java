package services;

import java.io.File;

import javax.ejb.Remote;
import javax.jws.WebService;
@WebService
@Remote
public interface ImportService {
	public void addToDatabase(File uploadedFile, String fileExtension);
}
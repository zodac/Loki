package services;

import java.io.File;

import javax.ejb.Local;

@Local
public interface AutoImportService {
	public int[] addToDatabase(File uploadedFile, String fileExtension);
}
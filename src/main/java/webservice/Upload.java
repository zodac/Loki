package webservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@Path("/Upload")
@Stateless
@LocalBean
public class Upload {
	//@EJB
	//CallFailureService cfEJB; //Needed to import datasets
	
	@POST
	@Consumes("multipart/form-data")
	public void uploadFile(MultipartFormDataInput dataset) {
		String fileName = "";
		createTempFolders();
		Map<String, List<InputPart>> uploadForm = dataset.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("importfile");
		
		for(InputPart inputPart : inputParts){
			try{
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);
				
				if(!fileName.equals("unknown")){
					InputStream inputStream = inputPart.getBody(InputStream.class, null);
					byte[] bytes = IOUtils.toByteArray(inputStream);
					
					String tmpFile = "C:\\tmp\\" + fileName;
					writeFile(bytes, tmpFile);
				}
			} catch(IOException e){
			}
		}
    }
	
	private String getFileName(MultivaluedMap<String, String> header) {
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
 
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
 
				String[] name = filename.split("=");
 
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}
	
	private void writeFile(byte[] content, String filename){
        File file = new File(filename);
        if (!file.exists()) {
            try {
				file.createNewFile();
				FileOutputStream fop = new FileOutputStream(file);
		        fop.write(content);
		        fop.flush();
		        fop.close();
			} catch (IOException e) {
			}
        }
    }
	
	private static void createTempFolders(){
		makeFolder(new File("C:\\tmp"));
		makeFolder(new File("C:\\tmpFinal"));
	}

	private static void makeFolder(File folderName){
		if(!folderName.exists()){
			folderName.mkdir();
		}
	}
}
package webservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import services.ImportService;

@Path("/Upload")
@Stateless
@LocalBean
public class Upload {
	@EJB
	ImportService iEJB;
	
	@POST
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput dataset) {
		String fileName = "";
		Response.ResponseBuilder builder = null;
		Map<String, List<InputPart>> uploadForm = dataset.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("importfile");
		
		for(InputPart inputPart : inputParts){
			try{
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);
				
				String fileExtension = FilenameUtils.getExtension(fileName);
				if(fileExtension.equals("xls") || fileExtension.equals("xlsx")){
					if(!fileName.equals("unknown")){
						InputStream inputStream = inputPart.getBody(InputStream.class, null);
						byte[] bytes = IOUtils.toByteArray(inputStream);
						
						File uploadedFile = writeFile(bytes, fileName);
						iEJB.addToDatabase(uploadedFile, fileExtension);
					}
				} else{
				}
			} catch(IOException e){
				e.printStackTrace();
			}
		}
		builder = Response.ok();
		return builder.build();
    }
	
	private String getFileName(MultivaluedMap<String, String> header) {
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
 
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				String[] name = filename.split("=");
 
				return name[1].trim().replaceAll("\"", "");
			}
		}
		return "unknown";
	}
	
	private File writeFile(byte[] content, String filename){
		createTempFolders();
        File file = new File("C:\\tmp\\" + filename);
        if (file.exists()) {
        	file.delete();
        }
        try {
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(file);
	        out.write(content);
	        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return file;
    }
	
	private static void createTempFolders(){
		makeFolder(new File("C:\\tmp"));
	}

	private static void makeFolder(File folderName){
		if(!folderName.exists()){
			folderName.mkdir();
		}
	}
}
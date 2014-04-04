package webservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	final String DIR = "C:\\Users\\C04352114\\Documents\\Upload\\";

	
	@POST
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput dataset) {
		System.out.println("got to post");
		Map<String, List<InputPart>> uploadForm = dataset.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("importfile");
		int[] results = new int[6];

		for(InputPart inputPart : inputParts){
			System.out.println("got past inputparts");
			try{
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				System.out.println("header: "+header);
				String fileName = getFileName(header);
				System.out.println("inpost file name: "+fileName);
				String fileExtension = FilenameUtils.getExtension(fileName);
				if(fileExtension.equals("xls") || fileExtension.equals("xlsx")){
					if(!fileName.equals("unknown")){
						InputStream inputStream = inputPart.getBody(InputStream.class, null);
						byte[] bytes = IOUtils.toByteArray(inputStream);

						File uploadedFile = writeFile(bytes, fileName);
						System.out.println("built uploadFile");
						results = iEJB.addToDatabase(uploadedFile, fileExtension);
						System.out.println("starting ejb thingy");
					}
				} else{
				}
			} catch(IOException e){
				e.printStackTrace();
			}
		}

		Map<String, String> responseObj = new HashMap<String, String>();
		responseObj.put("Result", Arrays.toString(results));

		return Response.status(Response.Status.ACCEPTED).entity(responseObj).build();
	}

	private String getFileName(MultivaluedMap<String, String> header) {
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

		for (String filename : contentDisposition) {
			System.out.println(filename);
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

	@POST
	@Path("/{fileName}")
	@Consumes("multipart/form-data")
	public void uploadFile(@PathParam("fileName") String fileName) {
		System.out.println("got int new post");

		String fileExtension = FilenameUtils.getExtension(fileName);
		if(fileExtension.equals("xls") || fileExtension.equals("xlsx")){


			File uploadedFile = new File(DIR + fileName);
			System.out.println("built uploadFile");
			iEJB.addToDatabase(uploadedFile, fileExtension);
			Log.addLogList("File uploaded:"+fileName);
		}

	}

}
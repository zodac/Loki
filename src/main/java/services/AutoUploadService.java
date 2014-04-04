package services;

import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

public class AutoUploadService {
	public static void executePost(String filePath){

		try {
			File f = new File(filePath);
			PostMethod filePost = new PostMethod("http://147.252.91.223:8080/Loki/webservice/Upload/" + f.getName());
			Part[] parts = {
					new StringPart("Content-Disposition", filePath),new StringPart("importfile", "importfile"),
					new FilePart(f.getName(), f)
			};
			filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
			System.out.println(parts);
			HttpClient client = new HttpClient();
			int status = client.executeMethod(filePost);



		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

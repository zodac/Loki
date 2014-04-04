package main;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import services.AutoUploadService;
import services.ImportService;

//@Stateless
//@LocalBean
//@SuppressWarnings("rawtypes")
public class AutoUploadListener extends Thread {
	//@EJB
	//ImportService iEJB;

	final int DELAY_IN_MINUTES = 2;
	final Path DIR = Paths.get("C:\\Users\\C04352114\\Documents\\Upload");

	int[] results = new int[6];
	static AutoUploadListener instance = null;

	protected AutoUploadListener() {
	}

	public static AutoUploadListener getInstance() {
		if (instance == null) {
			instance = new AutoUploadListener();
		}
		return instance;
	}

	@SuppressWarnings("rawtypes")
	public void run() {
		boolean found = false;
		String fileName = "";


		while (true) {
			System.out.println("Listening for files...");
			try {
				WatchService watcher = DIR.getFileSystem().newWatchService();
				DIR.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

				WatchKey watckKey = watcher.take();

				List<WatchEvent<?>> events = watckKey.pollEvents();

				for (WatchEvent event : events) {
					if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
						fileName = DIR.toString() + File.separator
								+ event.context().toString();
						System.out.println("Created: " + fileName);
						found = true;
					}
				}

			} catch (Exception e) {
			}
			try {
				if(found){
					System.out.println("found starting execute");
					AutoUploadService.executePost(fileName);
					found = false;
				}
				Thread.sleep(DELAY_IN_MINUTES * 60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

	}

	//	public void excutePost(String fileName){
	//
	//		HttpURLConnection conn = null;
	//		DataOutputStream dos = null;
	//		String existingFileName = fileName;
	//		String lineEnd = "\r\n";
	//		String twoHyphens = "--";
	//		String boundary =  "*****";
	//		int bytesRead, bytesAvailable, bufferSize;
	//		byte[] buffer;
	//		int maxBufferSize = 1*1024*1024;
	//		String urlString = "/Upload";
	//		try{
	//
	//			FileInputStream fileInputStream = new FileInputStream(new File(existingFileName) );
	//			// open a URL connection to the Servlet
	//			URL url = new URL(urlString);
	//			// Open a HTTP connection to the URL
	//			conn = (HttpURLConnection) url.openConnection();
	//			// Allow Inputs
	//			conn.setDoInput(true);
	//			// Allow Outputs
	//			conn.setDoOutput(true);
	//			// Don't use a cached copy.
	//			conn.setUseCaches(false);
	//			// Use a post method.
	//			conn.setRequestMethod("POST");
	//			conn.setRequestProperty("Connection", "Keep-Alive");
	//			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
	//			dos = new DataOutputStream( conn.getOutputStream() );
	//			dos.writeBytes(twoHyphens + boundary + lineEnd);
	//
	//			dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + existingFileName + "\"" + lineEnd); // uploaded_file_name is the Name of the File to be uploaded
	//			dos.writeBytes(lineEnd);
	//			bytesAvailable = fileInputStream.available();
	//			bufferSize = Math.min(bytesAvailable, maxBufferSize);
	//			buffer = new byte[bufferSize];
	//			bytesRead = fileInputStream.read(buffer, 0, bufferSize);
	//			while (bytesRead > 0){
	//				dos.write(buffer, 0, bufferSize);
	//				bytesAvailable = fileInputStream.available();
	//				bufferSize = Math.min(bytesAvailable, maxBufferSize);
	//				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
	//			}
	//			dos.writeBytes(lineEnd);
	//			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
	//			fileInputStream.close();
	//			dos.flush();
	//			dos.close();
	//			System.out.println("finished post");
	//		}
	//
	//		catch (IOException e){
	//			e.printStackTrace();
	//		}
	//		
	//
	//	}

	
}

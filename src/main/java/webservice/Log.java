package webservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Log")
@Stateless
@LocalBean
public class Log {
	private static String LOG_NAME = "log.txt";
	
	/**
	 * Returns a list of logs from the logs file.
	 * 
	 * @return list logs
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getLogList() {
		List<String> logs = new ArrayList<String>();
		try {
			File file = new File(LOG_NAME);
			createNewFile(file);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			
			while (line != null) {
				logs.add(line);
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return logs;
	}

	@GET
	@Path("/{logDetails}")
	public void addLogDetails(@PathParam("logDetails") String logDetails) {
		addLogList(logDetails);
	}
	
	@GET
	@Path("/Clear")
	public void clearLog(){
		resetLog(LOG_NAME);
	}

	public static void resetLog(String logName) {
		File log = new File(logName);
		
		if(log.exists()){
			log.delete();
		}
		
		try {
			File logFile = new File(logName);
			logFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void createNewFile(File file){
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void addLogList(String logItem) {
		try {
			FileWriter filew = new FileWriter(LOG_NAME, true);
			
			filew.write("[" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()).toString() + "] " + logItem + "\n");
			filew.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

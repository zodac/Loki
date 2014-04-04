package webservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
	/**
	 * Returns a list of logs from the logs file.
	 * @return list logs
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getLogList() {

		List<String> logs = new ArrayList<String>();
		try {
			
			//URL url = getClass().getResource("log.txt");
			File file = new File("log.txt");
			
			if(!file.exists()){
				file.createNewFile();
			}
			
			BufferedReader br;
			
			br = new BufferedReader(new FileReader(file));

			String line = br.readLine();

			while (line != null) {
				logs.add(line);
				line = br.readLine();
			}

			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logs;

	}
	
	public static void addLogList(String logItem) {

		try {

			FileWriter filew = new FileWriter("log.txt", true);
			filew.write("\n"+logItem);
			//add date********************************
			filew.close();		

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@GET
    @Path("/{logDetails}")
	public void addLogDetails(@PathParam("logDetails") String logDetails) {
		addLogList(logDetails);
    }
}

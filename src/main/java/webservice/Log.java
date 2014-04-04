package webservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;

@Path("/Log")
@Stateless
@LocalBean
public class Log {
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

			// URL url = getClass().getResource("log.txt");
			File file = new File("log.txt");
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
			
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.setTimeZone(new SimpleTimeZone(0, "GMT"));
			sdf.applyPattern("dd MMM yyyy HH:mm:ss z");
			Date currentDate = new Date();
			String d1 = sdf.format(currentDate).toString();
			filew.write("\n"+d1+" :"+logItem);
			
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

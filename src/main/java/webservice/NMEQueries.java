package webservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import services.CallFailureService;

@Path("/NMEQueries")
@Stateless
@LocalBean
public class NMEQueries {
	@EJB
	CallFailureService cfEJB;
	
	@GET
    @Path("/{tac}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Object[]> findUniqueEventCauseAndOccurancesByModel(@PathParam("tac") int tac) {
        return cfEJB.findUniqueEventCauseAndOccurancesByTAC(tac);
    }
    
    @GET
    @Path("/{fromDate}/{toDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Object[]> findNumberOfFailuresAndDuration(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate) {
    	Date fDate = null;
    	Date tDate = null;
    	
    	try {
			fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromDate.replace('T', ' '));
			tDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toDate.replace('T', ' '));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
        return cfEJB.findNumberOfFailuresAndDuration(fDate, tDate);
    }
}
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
import entities.EventCause;

@Path("/CSRQueries")
@Stateless
@LocalBean
public class CSRQueries {
	@EJB
	CallFailureService cfEJB;
	
	@GET
    @Path("/EC/{imsi}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EventCause> findUniqueEventCauseByIMSI(@PathParam("imsi") long imsi) {
        return cfEJB.findUniqueEventCauseByIMSI(imsi);
    }
	
	@GET
    @Path("/CC/{imsi}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> findUniqueCauseByIMSI(@PathParam("imsi") long imsi) {
        return cfEJB.findUniqueCauseCodesByIMSI(imsi);
    }
	
	@GET
    @Path("/{imsi}/{fromDate}/{toDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Long> numberOfFailuresByIMSIByTimePeriod(@PathParam("imsi") long imsi, @PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate) {
		Date fDate = trimDate(fromDate);
		Date tDate = trimDate(toDate);
		
        return cfEJB.numberOfFailuresByIMSIByTimePeriod(imsi, fDate, tDate);
    }
	
	public static Date trimDate(String date) {
		Date outputDate = null;
		try {
			outputDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date.replace('T', ' '));
		} catch (ParseException e) {
			try {
				outputDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date.replace('T', ' '));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return outputDate;
	}
}
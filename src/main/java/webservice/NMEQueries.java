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

import queryEntities.CountAndDuarationOfIMSI;
import queryEntities.EventIdCauseCodeCombo;
import queryEntities.MOCByFailureClass;
import queryEntities.TopIMSIByFailure;
import queryEntities.TopIMSIByFailureClass;
import queryEntities.TopMOCEntity;
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
    public List<EventIdCauseCodeCombo> findUniqueEventCauseAndOccurancesByModel(@PathParam("tac") int tac) {
        return cfEJB.findUniqueEventCauseAndOccurancesByTAC(tac);
    }
    
    @GET
    @Path("/FD/{fromDate}/{toDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CountAndDuarationOfIMSI> findNumberOfFailuresAndDuration(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate) {
    	Date fDate = trimDate(fromDate);
		Date tDate = trimDate(toDate);
		
        return cfEJB.findNumberOfFailuresAndDuration(fDate, tDate);
    }
    
    @GET
    @Path("/{fromDate}/{toDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TopMOCEntity> getTopTenMOC(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate) {
    	Date fDate = trimDate(fromDate);
		Date tDate = trimDate(toDate);
		
        return cfEJB.getTopTenMOC(fDate, tDate);
    }
    
    @GET
    @Path("/IMSI/{fromDate}/{toDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TopIMSIByFailure> getTopTenIMSI(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate) {
    	Date fDate = trimDate(fromDate);
		Date tDate = trimDate(toDate);
		
        return cfEJB.getTopTenIMSI(fDate, tDate);
    }
    
    @GET
    @Path("/IMSIFailClass/{fromDate}/{toDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TopIMSIByFailureClass> getFailureClassesOfIMSI(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate) {
    	Date fDate = trimDate(fromDate);
		Date tDate = trimDate(toDate);
		
        return cfEJB.getFailureClassesOfIMSI(fDate, tDate);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TopMOCEntity> getTopTenMOCGraphical() {
        return cfEJB.getTopTenMOCGraphical();
    }

    @GET
    @Path("/FailClass")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MOCByFailureClass> getMOCGraphicalByFailureClass() {
        return cfEJB.getMOCGraphicalByFailureClass();
    }
    
    @GET
    @Path("/FailClass/{fromDate}/{toDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MOCByFailureClass> getTopTenMOCByFailureClass(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate) {
    	Date fDate = trimDate(fromDate);
		Date tDate = trimDate(toDate);
		
        return cfEJB.getTopTenMOCByFailureClass(fDate, tDate);
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
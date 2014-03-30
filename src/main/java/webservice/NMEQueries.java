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
import queryEntities.TopIMSIByFailure;
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
    	Date fDate = null;
    	Date tDate = null;
    	
    	try {
			fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromDate.replace('T', ' '));
			tDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toDate.replace('T', ' '));
		} catch (ParseException e) {
			try{
				fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fromDate.replace('T', ' '));
				tDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(toDate.replace('T', ' '));
			} catch (ParseException e1){
				e1.printStackTrace();
			}
		}
        return cfEJB.findNumberOfFailuresAndDuration(fDate, tDate);
    }
    
    @GET
    @Path("/{fromDate}/{toDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TopMOCEntity> getTopTenMOC(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate) {
    	Date fDate = null;
    	Date tDate = null;
    	
    	try {
			fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromDate.replace('T', ' '));
			tDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toDate.replace('T', ' '));
		} catch (ParseException e) {
			try{
				fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fromDate.replace('T', ' '));
				tDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(toDate.replace('T', ' '));
			} catch (ParseException e1){
				e1.printStackTrace();
			}
		}
        return cfEJB.getTopTenMOC(fDate, tDate);
    }
    
    @GET
    @Path("/IMSI/{fromDate}/{toDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TopIMSIByFailure> getTopTenIMSI(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate) {
    	Date fDate = null;
    	Date tDate = null;
    	
    	try {
			fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromDate.replace('T', ' '));
			tDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toDate.replace('T', ' '));
		} catch (ParseException e) {
			try{
				fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fromDate.replace('T', ' '));
				tDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(toDate.replace('T', ' '));
			} catch (ParseException e1){
				e1.printStackTrace();
			}
		}
        return cfEJB.getTopTenIMSI(fDate, tDate);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TopMOCEntity> getTopTenMOCGraphical() {
        return cfEJB.getTopTenMOCGraphical();
    }

}
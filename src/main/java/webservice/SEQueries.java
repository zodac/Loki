package webservice;

import java.math.BigInteger;
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

@Path("/SEQueries")
@Stateless
@LocalBean
public class SEQueries {
	@EJB
	CallFailureService cfEJB;
    
    @GET
    @Path("/{fromDate}/{toDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BigInteger> findAllIMSIsByTimePeriod(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate) {
		Date fDate = trimDate(fromDate);
		Date tDate = trimDate(toDate);
				
        return cfEJB.findAllIMSIsByTimePeriod(fDate, tDate);
    }
    
    @GET
    @Path("/{model}/{fromDate}/{toDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BigInteger> findNumberOfFailuresByModelAndTimePeriod(@PathParam("model") String model, @PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate) {
    	Date fDate = trimDate(fromDate);
		Date tDate = trimDate(toDate);
    	
        return cfEJB.findNumberOfFailuresByModelAndTimePeriod(model, fDate, tDate);
    }
    
    @GET
    @Path("/{failureClassId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BigInteger> findIMSIsByFailureClass(@PathParam("failureClassId") int failureClassId) {    	
        return cfEJB.findIMSIsByFailureClass(failureClassId);
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
package webservice;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import services.CallFailureService;

@Path("/IMSI")
@Stateless
@LocalBean
public class IMSI {
	@EJB
	private CallFailureService cfEJB;
	
	@GET
    @Path("/{imsi}")
    @Produces(MediaType.APPLICATION_JSON)
    public long findIMSICount(@PathParam("imsi") long imsi) {
        return cfEJB.findIMSICount(imsi);
    }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public long[] getAllIMSIs(){
		return cfEJB.getAllIMSIs();
	}
}
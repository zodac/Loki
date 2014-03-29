package webservice;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import services.FailureClassService;

@Path("/FailureClass")
@Stateless
@LocalBean
public class FailureClass {
	@EJB
	private FailureClassService fcEJB;
	
	@GET
    @Path("/{failureClassId}")
    @Produces(MediaType.APPLICATION_JSON)
    public long findFailureClassCount(@PathParam("failureClassId") int failureClassId) {
        return fcEJB.findFailureClassCount(failureClassId);
    }
}
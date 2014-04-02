package webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.InvalidCallFailure;
import services.InvalidCallFailureService;


@Path("/InvalidCallFailure")
@Stateless
@LocalBean
public class InvalidCallFailures {
	@EJB
	private InvalidCallFailureService icfEJB;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<InvalidCallFailure> getAllInvalidCallFailures() {
        return icfEJB.getAllInvalidCallFailures();
    }
}
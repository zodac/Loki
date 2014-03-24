package webservice;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import services.UETypeService;
import entities.UEType;

@Path("/Phones")
@Stateless
@LocalBean
public class UserEquipment {
	@EJB
	UETypeService uetEJB;
	
	@GET
    @Path("/{model}")
    @Produces(MediaType.APPLICATION_JSON)
    public UEType getUETypeByModel(@PathParam("model") String model) {
        return uetEJB.getUETypeByModel(model);
    }
}
package webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import services.UserService;
import entities.User;

@Path("/Users")
@Stateless
@LocalBean
public class Users {
	@EJB
	private UserService userEJB;
	
	/**
	 * Returns a user matching a given user name
	 * 
	 * @param userName
	 * @return the matching user
	 */
	@GET
    @Path("/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userName") String userName) {
        return userEJB.getUserByUsername(userName);
    }
	
	/**
	 * Registers a user using form information
	 * 
	 * @param userName
	 * @param password
	 * @param userType
	 * @param email
	 * @param phone
	 * @param fname
	 * @param lname
	 * @return	Response status indicating success or failure
	 */
	@GET
	@Path("/{userName}/{password}/{userType}/{email}/{phone}/{fname}/{lname}")
	@Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(@PathParam("userName") String userName, @PathParam("password") String password, @PathParam("userType") String userType,
    							 @PathParam("email") String email, @PathParam("phone") String phone, @PathParam("fname") String fname, @PathParam("lname") String lname){
    	User user = new User();
    	user.setUserName(userName);
    	user.setUserPassword(password);
    	user.setUsertype(userType);
    	
    	System.out.println("Email: " + email + ", Phone: " + phone + ", FName: " + fname + ", LName: " + lname);
    	
    	userEJB.registerUser(user);
    	Response.ResponseBuilder builder = Response.ok();
    	
    	return builder.build();
    }
	
	/**
	 * Returns a list of all users currently in the database
	 * @return list of users
	 */
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserList() {
        return userEJB.findAllUsers();
    }
}
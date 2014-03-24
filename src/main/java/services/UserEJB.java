package services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import jpas.JPA;
import daos.UserDAO;
import entities.User;

@Stateless
@WebService(endpointInterface="services.UserService")
@Remote(UserService.class)
public class UserEJB implements UserService {
	private UserDAO dao;
	
	@Inject
	public void setDao(@JPA UserDAO dao) {
		this.dao = dao;
	}

	public User getUserByUsername(String userName) {
		User user = dao.getUserByUsername(userName);
		return user;
	}

	public List<User> findAllUsers() {
		List<User> users = dao.findAllUsers();
		return users;
	}

	public void registerUser(User newUser) {
		dao.registerUser(newUser);		
		
	}

}

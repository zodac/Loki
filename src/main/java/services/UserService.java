package services;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebService;

import entities.User;

@WebService
@Remote
public interface UserService {
	 void registerUser(User newUser);
	 User getUserByUsername(String userName);
	 List<User> findAllUsers();
}

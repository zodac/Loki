package daos;

import java.util.List;

import entities.User;

public interface UserDAO {
	void registerUser(User newUser);
	User getUserByUsername(String userName);
	List<User> findAllUsers();
}
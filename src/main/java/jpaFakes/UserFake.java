package jpaFakes;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Alternative;

import daos.UserDAO;
import entities.User;

@Alternative
public class UserFake implements UserDAO{
	
	private List<User> users = new ArrayList<>();

	@Override
	public void registerUser(User newUser) {
		users.add(newUser);
	}

	@Override
	public User getUserByUsername(String userName) {
		for(User user : users){
			if(user.getUserName().equals(userName)){
				return user;
			}
		}
		return null;
	}

	@Override
	public List<User> findAllUsers() {
		return users;
	}
}

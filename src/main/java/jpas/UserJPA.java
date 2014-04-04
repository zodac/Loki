package jpas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import daos.UserDAO;
import entities.User;

@JPA
@SuppressWarnings("unchecked")
public class UserJPA implements UserDAO {

	@PersistenceContext
	private EntityManager em;

	public void registerUser(User newUser) {
		if(em.find(User.class, newUser.getUserName()) == null){
			em.persist(newUser);
		}
	}

	public User getUserByUsername(String userName) {
		return em.find(User.class, userName);
	}

	public List<User> findAllUsers() {
		return (List<User>) em.createNamedQuery("User.findAll").getResultList();
	}
}

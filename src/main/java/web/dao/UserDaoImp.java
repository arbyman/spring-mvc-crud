package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(User user) {
		entityManager.persist(user);
	}

	@Override
	public User getUserById(long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return entityManager.createQuery("from User").getResultList();
	}

	@Override
	public void delete(long id) {
		entityManager.remove(getUserById(id));
	}

	@Override
	public void update(long id, User updatedUser) {
		entityManager.merge(updatedUser);
	}
}

package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void add(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public User getUserById(long id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery("select u from User u where u.id = :id").setParameter("id", id);
		User user = (User) query.getSingleResult();
		em.close();
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		EntityManager em = entityManagerFactory.createEntityManager();
		List<User> users = em.createQuery("from User").getResultList();
		em.close();
		return users;
	}

	@Override
	public void delete(long id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("delete from User u where u.id = :id").setParameter("id", id);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void update(long id, User updatedUser) {
		EntityManager em = entityManagerFactory.createEntityManager();
		String name = updatedUser.getName();
		byte age = updatedUser.getAge();
		em.getTransaction().begin();
		Query query = em.createQuery("update User u set u.name = :name, u.age = :age where u.id = :id")
				.setParameter("id", id)
				.setParameter("name", name)
				.setParameter("age", age);
		query.executeUpdate();
		em.getTransaction().commit();
	}
}

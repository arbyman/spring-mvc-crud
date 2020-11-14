package web.service;

import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

	final UserDao userDao;

	public UserServiceImp(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void add(User user) {
		user.setRoles(getRoles(user.getIsAdmin()));
		userDao.add(user);
	}

	@Override
	public User getUserById(long id) {
		return userDao.getUserById(id);
	}

	@Override
	public User getUserByName(String username) {
		return userDao.getUserByName(username);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public void delete(long id) {
		userDao.delete(id);
	}

	@Override
	public void update(long id, User user) {
		user.setRoles(getRoles(user.getIsAdmin()));
		userDao.update(id, user);
	}

	private Set<Role> getRoles(String isAdmin) {
		HashSet<Role> roles = new HashSet<>();
		roles.add(new Role("ROLE_USER"));
		if (isAdmin != null) {
			roles.add(new Role(isAdmin));
		}
		return roles;
	}

}

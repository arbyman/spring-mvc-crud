package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserDao userDao;

	@Transactional
	@Override
	public void add(User user) {
		userDao.add(user);
	}

	@Transactional
	@Override
	public User getUserById(long id) {
		return userDao.getUserById(id);
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
		userDao.update(id, user);
	}
}

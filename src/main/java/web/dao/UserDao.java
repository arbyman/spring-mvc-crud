package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
	void add(User user);
	User getUserById(long id);
	User getUserByName(String username);
	List<User> getAllUsers();
	void delete(long id);
	void update(long id, User user);
}

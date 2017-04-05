package nl.cap.csd.capbox.commons.dao;


import nl.cap.csd.capbox.commons.model.users.User;

public interface UserDao {

	void create(User user);
	void delete(String email);
	User find(String email);

}

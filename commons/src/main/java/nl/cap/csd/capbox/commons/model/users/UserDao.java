package nl.cap.csd.capbox.commons.model.users;


public interface UserDao {

	void create(User user);
	void delete(String email);
	User find(String email);

}

package nl.cap.csd.capbox.application.security;


public interface UserDao {

	void create(User user);
	void delete(String email);
	User find(String email);

}

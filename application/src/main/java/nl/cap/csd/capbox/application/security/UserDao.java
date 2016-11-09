package authenticationprovider;


public interface UserDao {

	void create(User user);
	void delete(String email);
	User find(String email);
}

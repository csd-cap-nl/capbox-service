package authenticationprovider;


public class User {

	final String email;
	final byte[] salt;
	final byte[] hash;

	User(String email, byte[] salt, byte[] hash) {
		this.email = email;
		this.salt = salt;
		this.hash = hash;
	}
}

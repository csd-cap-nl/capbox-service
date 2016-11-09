package nl.cap.csd.capbox.commons.services.authentication;

public interface AuthenticationProvider {

	void create(String email, char[] password);
	boolean authenticate(String email, char[] password);
	void delete(String email);
}

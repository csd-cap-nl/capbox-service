package nl.cap.csd.capbox.application.security;

public interface AuthenticationProvider {

	void create(String email, char[] password);
	boolean authenticate(String email, char[] password);
	void delete(String email);
}

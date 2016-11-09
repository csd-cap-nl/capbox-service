package authenticationprovider;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

import static authenticationprovider.FunctionUtil.runtimeException;

class AuthenticationProviderImpl implements AuthenticationProvider {

	private final UserDao userDao;
	private final SecureRandom secureRandom = new SecureRandom();
	private final SecretKeyFactory secretKeyFactory = runtimeException(() -> SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512"));
	private static final int ITERATIONS = 100000;

	AuthenticationProviderImpl(UserDao userDao){
		this.userDao = userDao;
	}

	@Override
	public void create(String email, char[] password) {
		byte[] salt = newSalt();
		userDao.create(new User(email, salt, hash(password, salt)));
	}

	@Override
	public boolean authenticate(String email, char[] password) {
		User user = userDao.find(email);
		return Arrays.equals(user.hash, hash(password, user.salt));
	}

	@Override
	public void delete(String email) {
		userDao.delete(email);
	}

	private byte[] newSalt(){
		byte[] salt = new byte[16];
		this.secureRandom.nextBytes(salt);
		return salt;
	}

	private byte[] hash(char[] password, byte[] salt){
		return runtimeException(() ->
						secretKeyFactory
						.generateSecret(new PBEKeySpec(password, salt, ITERATIONS, 64))
						.getEncoded()
		);
	}
}

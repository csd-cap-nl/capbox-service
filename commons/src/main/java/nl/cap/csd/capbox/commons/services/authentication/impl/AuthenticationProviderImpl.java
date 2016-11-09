package nl.cap.csd.capbox.commons.services.authentication.impl;


import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import nl.cap.csd.capbox.commons.helpers.RandomProvider;
import nl.cap.csd.capbox.commons.model.users.User;
import nl.cap.csd.capbox.commons.model.users.UserDao;
import nl.cap.csd.capbox.commons.services.authentication.AuthenticationProvider;
import nl.cap.csd.capbox.commons.services.authentication.FunctionUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationProviderImpl implements AuthenticationProvider {

    private final UserDao userDao;
    private final RandomProvider secureRandom;
    private final SecretKeyFactory secretKeyFactory = FunctionUtil.runtimeException(() -> SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512"));
    private static final int ITERATIONS = 100000;

    @Autowired
    public AuthenticationProviderImpl(UserDao userDao, final RandomProvider secureRandom) {
        this.userDao = userDao;
        this.secureRandom = secureRandom;
    }

    @Override
    public void create(String email, char[] password) {
        byte[] salt = newSalt();
        final User user = new User();
        user.setEmail(email);
        user.setPassword(hash(password, salt));
        user.setSalt(salt);
        userDao.create(user);
    }

    @Override
    public boolean authenticate(String email, char[] password) {
        User user = userDao.find(email);
        return Arrays.equals(user.getPassword(), hash(password, user.getSalt()));
    }

    @Override
    public void delete(String email) {
        userDao.delete(email);
    }

    private byte[] newSalt() {
        byte[] salt = new byte[16];
        this.secureRandom.nextBytes(salt);
        return salt;
    }

    private byte[] hash(char[] password, byte[] salt) {
        return FunctionUtil.runtimeException(() ->
                secretKeyFactory
                        .generateSecret(new PBEKeySpec(password, salt, ITERATIONS, 64))
                        .getEncoded()
        );
    }
}

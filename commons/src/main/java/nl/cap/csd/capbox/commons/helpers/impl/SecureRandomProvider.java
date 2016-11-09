package nl.cap.csd.capbox.commons.helpers.impl;

import java.security.SecureRandom;
import java.util.Random;
import nl.cap.csd.capbox.commons.helpers.RandomProvider;

public class SecureRandomProvider implements RandomProvider {

    private Random random = new SecureRandom();

    @Override
    public void nextBytes(final byte[] salt) {
        random.nextBytes(salt);
    }

}

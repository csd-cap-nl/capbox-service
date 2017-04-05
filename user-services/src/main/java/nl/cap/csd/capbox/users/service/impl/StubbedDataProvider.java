package nl.cap.csd.capbox.users.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import nl.cap.csd.capbox.users.model.web.User;
import nl.cap.csd.capbox.users.service.DataProvider;

public class StubbedDataProvider implements DataProvider {

    private Map<Long, User> users = new HashMap<>();

    public StubbedDataProvider() {
        createUser(new User("Test", "test2", ""));
    }

    @Override
    public Collection<User> getUserList() {
        return users.values();
    }

    @Override
    public User getUser(final String userId) {
        try {
            return users.get(userId);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public void updateUser(final User userData) {
        checkforUserName(userData);
        users.put(userData.getId(), userData);
    }

    private void checkforUserName(final User userData) {
        final Long id = userData.getId();
        final String userName = userData.getUserName();
        for (User user : users.values()) {
            if (user.getUserName().equals(userName) && user.getId().equals(id)) {
                throw new IllegalArgumentException("Duplicate user name");
            }
        }
    }

    @Override
    public long createUser(final User userData) {
        if (userData.getId() != null) {
            userData.setId(null);
        }
        checkforUserName(userData);
        generateId(userData);
        users.put(userData.getId(), userData);
        return userData.getId();
    }

    private void generateId(final User userData) {
        userData.setId(users.values()
                .stream()
                .mapToLong(User::getId)
                .max()
                .orElse(0)+1);
    }

    @Override
    public void deleteUser(final String userId) {
        users.remove(userId);
    }

}

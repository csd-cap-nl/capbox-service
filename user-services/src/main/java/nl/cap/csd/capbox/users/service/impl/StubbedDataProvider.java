package nl.cap.csd.capbox.users.service.impl;

import java.util.ArrayList;
import java.util.List;
import nl.cap.csd.capbox.users.model.web.User;
import nl.cap.csd.capbox.users.service.DataProvider;

public class StubbedDataProvider implements DataProvider {

    private List<User> users = new ArrayList<>();

    public StubbedDataProvider() {
        users.add( new User(123L, "Test", "test2", ""));
    }

    @Override
    public List<User> getUserList() {
        return users;
    }

    @Override
    public User getUser(final String userId) {
        if ("123".equals(userId)) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void updateUser(final User userData) {

    }
}

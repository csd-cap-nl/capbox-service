package nl.cap.csd.capbox.users.service.impl;

import java.util.ArrayList;
import java.util.List;
import nl.cap.csd.capbox.users.model.web.User;
import nl.cap.csd.capbox.users.service.DataProvider;

public class StubbedDataProvider implements DataProvider {

    private List<User> users = new ArrayList<>();

    public StubbedDataProvider() {
        users.add( new User(123L, "Test123", "test123", ""));
        users.add( new User(1L, "Test1", "test1", ""));
        users.add( new User(2L, "Test2", "test2", ""));
        users.add( new User(3L, "Test3", "test3", ""));
    }

    @Override
    public List<User> getUserList() {
        return users;
    }

    @Override
    public User getUser(final String id) {
//        public User getUser(final String userId) {
//    	for (User user: users) {
//    		if (user.getUserName().equals(userId)) {
//    			return user;
//    		}
//    	}
    	for (User user: users) {
    		if (user.getId() == Long.parseLong(id)) {
    			return user;
    		}
    	}
    	return null;
//        if ("123".equals(userId)) {
//            return users.get(0);
//        } else {
//            return null;
//        }
    }

    @Override
    public void updateUser(final User userData) {

    }
}

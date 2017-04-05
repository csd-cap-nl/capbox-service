package nl.cap.csd.capbox.users.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import nl.cap.csd.capbox.users.model.web.User;

public interface DataProvider {

    Collection<User> getUserList();

    User getUser(String userId);

    void updateUser(User userData);

    long createUser(User userData);

    void deleteUser(String userId);

}

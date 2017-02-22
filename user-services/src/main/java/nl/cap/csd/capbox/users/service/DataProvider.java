package nl.cap.csd.capbox.users.service;

import java.util.List;
import nl.cap.csd.capbox.users.model.web.User;

public interface DataProvider {

    List<User> getUserList();

    User getUser(String id);
//    User getUser(String userId);

    void updateUser(User userData);
}

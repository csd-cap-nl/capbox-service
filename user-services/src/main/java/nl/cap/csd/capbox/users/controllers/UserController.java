package nl.cap.csd.capbox.users.controllers;

import java.util.Collection;
import java.util.List;
import nl.cap.csd.capbox.commons.services.version.VersionInformation;
import nl.cap.csd.capbox.commons.services.version.VersionedBean;
import nl.cap.csd.capbox.users.model.web.User;
import nl.cap.csd.capbox.users.service.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// TODO Security
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController implements VersionedBean {

    @Autowired
    private DataProvider dataProvider;

    @GetMapping("/api/user")
    public Collection<User> getUsers() {
        return dataProvider.getUserList();
    }

    @PostMapping("/api/user")
    public ResponseEntity<String> createUser(@RequestBody final User userData) {
        final long userId = dataProvider.createUser(userData);
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") final String userId) {
        final User result = dataProvider.getUser(userId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") final String userId, @RequestBody final User userData) {
        if (userData.getId() != Long.parseLong(userId)) {
            return ResponseEntity.badRequest().body("Data mismatch");
        }
        dataProvider.updateUser(userData);
        return ResponseEntity.ok("Ok");
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") final String userId) {
        dataProvider.deleteUser(userId);
        return ResponseEntity.ok("Ok");
    }

    @Override
    public VersionInformation version() {
        return new VersionInformation("User services", "1.0");
    }

}

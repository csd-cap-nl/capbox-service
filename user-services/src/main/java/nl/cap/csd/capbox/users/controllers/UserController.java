package nl.cap.csd.capbox.users.controllers;

import java.util.List;
import nl.cap.csd.capbox.commons.services.version.VersionInformation;
import nl.cap.csd.capbox.commons.services.version.VersionedBean;
import nl.cap.csd.capbox.users.model.web.User;
import nl.cap.csd.capbox.users.service.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements VersionedBean {

    @Autowired
    private DataProvider dataProvider;

    @Value("${application.version}")
    private String applicationVersion;

    @Value("${application.name}")
    private String applicationName;

    @Override
    public VersionInformation version() {
        return new VersionInformation(applicationName, applicationVersion);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/user")
    public List<User> getUser() {
        return dataProvider.getUserList();
    }

    //http://localhost:8080/api/user/123
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") final String id) {
//   public ResponseEntity<?> getUser(@PathVariable("id") final String userId) {
     //   final User result = dataProvider.getUser(userId);
        final User result = dataProvider.getUser(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") final String id, @RequestBody final User userData) {
        if (userData.getId() != Long.parseLong(id)) {
            return ResponseEntity.badRequest().body("Data mismatch");
        }
        dataProvider.updateUser(userData);
        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/api/user")
    public ResponseEntity<String> createUser() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not implemented");
//        return dataProvider.getUser(userId);
    }
}

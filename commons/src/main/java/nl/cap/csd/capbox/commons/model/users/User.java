package nl.cap.csd.capbox.commons.model.users;

import java.util.Base64;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkId")
    private long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "password", nullable = false)
    private String internalPassword;

    @Column(name = "salt", nullable = false)
    private String internalSalt;

    @ManyToMany(targetEntity = Role.class)
    private Set<Role> roles;

    public final long getId() {
        return id;
    }

    public final String getUsername() {
        return username;
    }

    public final String getFullname() {
        return fullname;
    }

    public final byte[] getPassword() {
        return Base64.getDecoder().decode(internalPassword);
    }

    public final void setPassword(final byte[] password) {
        this.internalPassword = Base64.getEncoder().encodeToString(password);
    }

    public final byte[] getSalt() {
        return Base64.getDecoder().decode(internalSalt);
    }

    public final void setUsername(final String username) {
        this.username = username;
    }

    public final void setFullname(final String fullname) {
        this.fullname = fullname;
    }

    public final void setSalt(final byte[] salt) {
        this.internalSalt = Base64.getEncoder().encodeToString(salt);
    }

    public final Set<Role> getRoles() {
        return roles;
    }

    public final void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(final String email) {
        this.email = email;
        return this;
    }

}

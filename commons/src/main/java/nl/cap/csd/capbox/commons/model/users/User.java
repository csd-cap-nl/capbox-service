package nl.cap.csd.capbox.commons.model.users;

import java.util.List;
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

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "salt", nullable = false)
    private String salt;

    @ManyToMany
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

    public final String getPassword() {
        return password;
    }

    public final String getSalt() {
        return salt;
    }

    public final void setUsername(final String username) {
        this.username = username;
    }

    public final void setFullname(final String fullname) {
        this.fullname = fullname;
    }

    public final void setPassword(final String password) {
        this.password = password;
    }

    public final void setSalt(final String salt) {
        this.salt = salt;
    }

    public final Set<Role> getRoles() {
        return roles;
    }

    public final void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

}

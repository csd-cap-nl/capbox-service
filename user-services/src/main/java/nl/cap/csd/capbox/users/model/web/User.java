package nl.cap.csd.capbox.users.model.web;

public class User {

    private Long id;
    private String userName;
    private String fullName;
    private String email;

    public User() {
    }

    public User(final String userName, final String fullName, final String email) {
        this.id = null;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public User setId(final Long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(final String userName) {
        this.userName = userName;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public User setFullName(final String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(final String email) {
        this.email = email;
        return this;
    }
}

package nl.cap.csd.capbox.users.model.web;

public class User {

    private long id;
    private String userName;
    private String fullName;
    private String email;

    public User() {
    }

    public User(final long id, final String userName, final String fullName, final String email) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public User setId(final long id) {
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

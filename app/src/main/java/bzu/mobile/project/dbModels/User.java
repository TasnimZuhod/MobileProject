package bzu.mobile.project.dbModels;

public class User {

    private String userEmail;
    private String userPassword;

    public User() {}

    public User(String email, String password) {
        this.userEmail = email;
        this.userPassword = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}

package db;

public class UsersDataSet {

    private String login;
    private String password;

    public UsersDataSet(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}

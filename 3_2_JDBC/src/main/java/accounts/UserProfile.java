package accounts;

/**
 * Created by Andrey on 04.11.17.
 */
public class UserProfile {

    private String login;
    private String password;

    public static final UserProfile EMPTY = new UserProfile("", "");

    public UserProfile(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEmpty() {
        return login.isEmpty();
    }

}

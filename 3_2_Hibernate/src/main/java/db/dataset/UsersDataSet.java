package db.dataset;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable {

    private static final long serialVersionUID = -2256713123398388840L;

    private enum gson {
        INSTANCE;

        private Gson instance = new Gson();

        public String toString(UsersDataSet usersDataSet) {
            return instance.toJson(usersDataSet);
        }

    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String login;

    @Column(name = "password")
    private String password;


    public UsersDataSet() {
    }

    public UsersDataSet(long id, String login, String password) {

        this.id = id;
        this.login = login;
        this.password = password;

    }

    public UsersDataSet(String login, String password) {

        this.id = -1;
        this.login = login;
        this.password = password;

    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String name) {
        this.login = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return gson.INSTANCE.toString(this);
    }
}

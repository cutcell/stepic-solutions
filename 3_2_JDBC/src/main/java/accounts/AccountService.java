package accounts;

import db.DBService;

import java.util.Optional;

/**
 * Created by Andrey on 04.11.17.
 */
public class AccountService {

    private DBService dbService;

    public AccountService() {

        this.dbService = new DBService();

    }

    public Optional<UserProfile> getUser(String login) {

        return Optional.ofNullable(dbService.getUser(login));

    }

    public void addNewUser(UserProfile userProfile) {

        dbService.addUser(userProfile);

    }

    public void prepareDB() {

        if (!dbService.usersTableExists()) {
            dbService.makeUsersTable();
        }

    }



}

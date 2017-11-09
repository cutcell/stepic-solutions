package db;

import accounts.UserProfile;

import java.sql.*;

/**
 * Created by Andrey on 04.11.17.
 */
public class DBService {

    private final Connection connection;
    private final UsersDAO usersDAO;


    private static Connection getH2Connection() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:./h2db", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;

    }

    public DBService() {
        this.connection = getH2Connection();
        this.usersDAO = new UsersDAO(connection);
    }

    public UserProfile getUser(String login) {

        UserProfile userProfile = null;
        try {
            UsersDataSet usersDataSet = usersDAO.getUser(login);
            userProfile = new UserProfile(usersDataSet.getLogin(), usersDataSet.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userProfile;

    }

    public void addUser(UserProfile userProfile) {

        try {
            connection.setAutoCommit(false);
            usersDAO.addUser(new UsersDataSet(userProfile.getLogin(), userProfile.getPassword()));
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void cleanUp() {

        try {
            usersDAO.dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void makeUsersTable() {

        try {
            usersDAO.createUsersTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean usersTableExists() {

        try {
            DatabaseMetaData dbMetadata = connection.getMetaData();
            ResultSet tables = dbMetadata.getTables(null, null, "USERS", new String[] {"TABLE"});
            return tables.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}

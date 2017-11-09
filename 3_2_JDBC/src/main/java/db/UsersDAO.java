package db;

import db.executor.QueryExecutor;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {

    QueryExecutor executor;

    public UsersDAO(Connection connection) {
        this.executor = new QueryExecutor(connection);
    }

    public UsersDataSet getUser(String login) throws SQLException {

        String sql = String.format("SELECT * FROM users WHERE login='%s'", login);
        return executor.execQuery(sql,
                result -> {
                    result.next();
                    return new UsersDataSet(result.getString(1), result.getString(2));
                });


    }

    public void addUser(UsersDataSet usersDataSet) throws SQLException {

        String sql = String.format("INSERT INTO users (login, password) VALUES ('%s', '%s')",
                usersDataSet.getLogin(), usersDataSet.getPassword());
        executor.execUpdate(sql);

    }

    public void createUsersTable() throws SQLException {

        executor.execUpdate("CREATE TABLE users (" +
                "id INT IDENTITY(1,1) PRIMARY KEY," +
                "login VARCHAR(255), " +
                "password VARCHAR(255))");

    }

    public void dropTable() throws SQLException {

        executor.execUpdate("DROP TABLE users");

    }


}

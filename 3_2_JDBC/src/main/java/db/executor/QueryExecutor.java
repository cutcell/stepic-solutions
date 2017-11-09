package db.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;

public class QueryExecutor {

    private final Connection connection;

    public QueryExecutor(Connection connection) {
        this.connection = connection;
    }

    public void execUpdate(String sql) {

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public <T> T execQuery(String sql, ResultHandler<T> handler) throws SQLException {

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);
            T value = handler.handle(resultSet);
            resultSet.close();
            return value;
        }

    }


}

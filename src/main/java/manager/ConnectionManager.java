package manager;

import java.sql.*;

public class ConnectionManager {
    private static final String COM_MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_CONNECTION_URL = "jdbc:mysql://localhost/caching?user=root&password=";
    private static ConnectionManager instance;
    private Connection connection = null;

    public static ConnectionManager getInstance(){
        if (instance == null) {
            instance = new ConnectionManager();
            return instance;
        } else {
            return instance;
        }
    }

    public Connection getConnection(){
        try {
            Class.forName(COM_MYSQL_JDBC_DRIVER);
            if (connection == null)
                connection = DriverManager.getConnection(DATABASE_CONNECTION_URL);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void disconnect(Connection connection, ResultSet resultSet, Statement statement) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}

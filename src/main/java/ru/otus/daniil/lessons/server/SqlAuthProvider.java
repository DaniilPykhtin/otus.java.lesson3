package ru.otus.daniil.lessons.lesson22.server;

import java.sql.*;

public class SqlAuthProvider implements AuthenticationProvider {

    private static final String URL = "jdbc:sqlite::resource:auth.db";
    private static final String AUTH_QUERY = "select login, userRole from clients where login = '%s' and password = '%s';";
    private static final String REG_QUERY = "insert into clients (login,password,userRole) values ('%s','%s','%s');";

    private Connection conn;

    public SqlAuthProvider() {

        try {
            this.conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] authUser(String login, String password) {
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format(AUTH_QUERY, login, password));
            if (resultSet.next()) {
                String[] resString = new String[2];
                resString[0] = resultSet.getString("login");
                resString[1] = resultSet.getString("userRole");

                return resString;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean regUser(String login, String password, String userRole) {

        try (Statement statement = conn.createStatement()) {
            statement.execute(String.format(REG_QUERY, login, password, userRole));
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

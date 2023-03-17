package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    Connection connection = util.getConnection();
    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {

    }

    public void createUsersTable() throws SQLException {
        String sqlCreateeTable = "CREATE TABLE IF NOT EXISTS Users " +
                "(id MEDIUMINT NOT NULL AUTO_INCREMENT, name VARCHAR(30) NOT NULL, " +
                "lastName VARCHAR(30) NOT NULL, age SMALLINT NOT NULL, PRIMARY KEY (id))";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCreateeTable);
        }
        System.out.println("Таблица создана");
    }

    public void dropUsersTable() throws SQLException {
        String sqlDropTable = "DROP TABLE IF EXISTS Users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlDropTable);
        }
        System.out.println("Таблица удаленна");
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sqlSaveUser = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?) ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSaveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        }
        System.out.println("Пользователь " + name + " добавлен ");
    }

    public void removeUserById(long id) throws SQLException {
        String sqlRemoveUser = "DELETE FROM Users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveUser)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
        System.out.println("Пользователь " + id + " удалён ");
    }

    public List<User> getAllUsers() throws SQLException {
        ArrayList<User> userArrayList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                userArrayList.add(user);
            }
        }
        return userArrayList;
    }

    public void cleanUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("TRUNCATE TABLE Users");
        }
        System.out.println("Таблица очищена");
    }
}

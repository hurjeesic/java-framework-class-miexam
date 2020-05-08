package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User get(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new GetStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(id, connection);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //리턴
        return user;
    }

    public void insert(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new InsertStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(user, connection);
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            user.setId(resultSet.getInt(1));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new UpdateStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(user, connection);
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new DeleteStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(id, connection);
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

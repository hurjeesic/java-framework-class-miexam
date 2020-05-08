package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatementStrategy implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Object object, Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement =
                connection.prepareStatement("delete from userinfo where id = ?");
        preparedStatement.setInt(1, (Integer)object);
        return preparedStatement;
    }
}

package ru.ezhov.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import ru.ezhov.User;

/**
 *
 * @author deonisius
 */
public class LoginChecked {

    private String query = "select id, name, pass from V_E_users where name=? and pass = ?";
    private String email;
    private String pass;

    public LoginChecked(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public User getUser() throws SQLException, Exception {
        DataSource dataSource = ConnectionDAO.getDataSource();

        User user = null;
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, pass);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    while (resultSet.next()) {
                        user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                    }
                }
            }
        }

        return user;
    }
}

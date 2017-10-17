/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class CookieUserCheck {

    private String query = "select id, name, pass from V_E_users where name=?";
    private String user;

    public CookieUserCheck(String user) {
        this.user = user;
    }

    public User getUser() throws SQLException, Exception {
        DataSource dataSource = ConnectionDAO.getDataSource();
        User userObject = null;
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
                preparedStatement.setString(1, user);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    while (resultSet.next()) {
                        userObject = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                    }
                }
            }
        }

        return userObject;
    }
}

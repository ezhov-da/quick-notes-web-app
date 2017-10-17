package ru.ezhov.db;

import java.sql.Connection;
import java.sql.ResultSet;
import javax.sql.DataSource;

/**
 *
 * @author deonisius
 */
public class IdNoteHolder {

    public static synchronized int getIdNote() throws Exception {
        DataSource dataSource = ConnectionDAO.getDataSource();
        int id = -100;
        try (Connection connection = dataSource.getConnection();) {
            try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT nextval('note') as id;");) {
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                    return id;
                }

            }
        }
        throw new Exception("Ошибка получения id");

    }
}

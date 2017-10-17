package ru.ezhov.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * Получаем список
 *
 * @author deonisius
 */
public class SelectNotes {

    private static final String GET_NOTES = "select idNote, name, idUser from V_E_not_hide_note where idUser = ?";
    private int idUser;

    public SelectNotes(int idUser) {
        this.idUser = idUser;
    }

    public List<Note> getNotes() throws SQLException, Exception {
        List<Note> list = new ArrayList();
        DataSource dataSource = ConnectionDAO.getDataSource();
        try (Connection connection = dataSource.getConnection();) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(GET_NOTES)) {
                preparedStatement.setInt(1, idUser);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("idNote");
                        String name = resultSet.getString("name");

                        Note note = new Note(id, name);
                        list.add(note);
                    }
                }
            }

        }
        return list;
    }

}

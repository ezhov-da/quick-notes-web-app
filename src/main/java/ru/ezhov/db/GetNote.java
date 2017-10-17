package ru.ezhov.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.sql.DataSource;

/**
 *
 * @author deonisius
 */
public class GetNote {

    private static final String SELECT_NOTE_DETAIL = "select id, name, text, link, dateAdd, dateEdit from V_E_note_detail where id = ?";

    public NoteDetail getNoteDetail(int id) throws SQLException, Exception {

        NoteDetail note = null;
        DataSource dataSource = ConnectionDAO.getDataSource();
        try (Connection connection = dataSource.getConnection();) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTE_DETAIL);) {

                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String text = resultSet.getString("text");
                    String link = resultSet.getString("link");

                    Timestamp timestampAdd = resultSet.getTimestamp("dateAdd");
                    Timestamp timestampEdit = resultSet.getTimestamp("dateEdit");

                    note = new NoteDetail(text, link, id, name, timestampAdd, timestampEdit);
                }
            }

        }
        return note;
    }
}

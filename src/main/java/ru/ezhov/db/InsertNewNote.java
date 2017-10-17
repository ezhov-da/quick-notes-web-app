package ru.ezhov.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.sql.DataSource;

/**
 *
 * @author deonisius
 */
public class InsertNewNote {

    private static final String INSERT_DATE_ADD = "insert into T_E_note_timestamp(id,dateAdd) values(?, ?)";
    private static final String INSERT_LINK_ID_USER_ID_NOTE = "insert into T_E_link_user_note (idUser, idNote) values(?, ?)";
    private static final String INSERT_NOTE = "insert into T_E_note (id, name) values(?, ?)";
    private static final String INSERT_NOTE_DETAIL = "insert into T_E_note_detail ( id, text, link) values(?, ?, ?)";

    private final int id;
    private final int idUser;
    private final String name;
    private final String text;
    private final String link;
    private Timestamp timestampAdd;

    public InsertNewNote(int id, String name, String text, String link, int idUser, Timestamp timestampAdd) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.link = link;
        this.idUser = idUser;
        this.timestampAdd = timestampAdd;
    }

    public void insert() throws SQLException, Exception {
        DataSource dataSource = ConnectionDAO.getDataSource();
        try (Connection connection = dataSource.getConnection();) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LINK_ID_USER_ID_NOTE);) {
                preparedStatement.setInt(1, idUser);
                preparedStatement.setInt(2, id);
                preparedStatement.execute();
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NOTE);) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.execute();
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NOTE_DETAIL);) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, text);
                preparedStatement.setString(3, link);
                preparedStatement.execute();
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DATE_ADD);) {
                preparedStatement.setInt(1, id);
                preparedStatement.setTimestamp(2, timestampAdd);
                preparedStatement.execute();
            }
            connection.commit();
        }
    }

}

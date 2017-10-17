package ru.ezhov.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author deonisius
 */
public class HideNoteBase {

    private static final String HIDE_NOTE = "update T_E_note set isHide = cast(1 as bit) where id = ?";

    private int id;

    public HideNoteBase(int id) {
        this.id = id;
    }

    public void hide() throws SQLException, Exception {
        DataSource dataSource = ConnectionDAO.getDataSource();
        try (Connection connection = dataSource.getConnection();) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(HIDE_NOTE);) {
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            }
            connection.commit();
        }
    }
}

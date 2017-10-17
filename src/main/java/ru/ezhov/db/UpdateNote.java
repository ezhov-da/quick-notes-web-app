package ru.ezhov.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author deonisius
 */
public class UpdateNote {

    private static final String UPDATE_NAME = "update T_E_note t0 \n"
            + "	set name = ? \n"
            + "from T_E_link_user_note t1\n"
            + "where \n"
            + "	t0.id = t1.idNote\n"
            + "	and\n"
            + "	t0.id = ?\n"
            + "	and\n"
            + "	t1.idUser = ?";
    private static final String UPDATE_DETAIL = "update T_E_note_detail t0\n"
            + "	set \n"
            + "		text = ?, \n"
            + "		link = ? \n"
            + "from T_E_link_user_note t1\n"
            + "where \n"
            + "	t0.id = t1.idNote\n"
            + "	and\n"
            + "	t0.id = ?\n"
            + "	and\n"
            + "	t1.idUser = ?	";

    private static final String UPDATE_DATE_EDIT
            = "update T_E_note_timestamp t0\n"
            + "	set \n"
            + "		dateEdit = ?\n"
            + "from T_E_link_user_note t1\n"
            + "where \n"
            + "	t0.id = t1.idNote\n"
            + "	and\n"
            + "	t0.id = ?\n"
            + "	and\n"
            + "	t1.idUser = ?";

    private NoteDetail noteDetail;
    private int idUser;

    public UpdateNote(NoteDetail noteDetail, int idUser) {
        this.noteDetail = noteDetail;
        this.idUser = idUser;
    }

    public void update() throws SQLException, Exception {
        DataSource dataSource = ConnectionDAO.getDataSource();
        try (Connection connection = dataSource.getConnection();) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NAME);) {
                preparedStatement.setString(1, noteDetail.getName());
                preparedStatement.setInt(2, noteDetail.getId());
                preparedStatement.setInt(3, idUser);
                preparedStatement.execute();
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DETAIL);) {

                preparedStatement.setString(1, noteDetail.getText());
                preparedStatement.setString(2, noteDetail.getLink());
                preparedStatement.setInt(3, noteDetail.getId());
                preparedStatement.setInt(4, idUser);
                preparedStatement.execute();
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DATE_EDIT);) {
                preparedStatement.setTimestamp(1, noteDetail.getDateEdit());
                preparedStatement.setInt(2, noteDetail.getId());
                preparedStatement.setInt(3, idUser);
                preparedStatement.execute();
            }
            connection.commit();
        }
    }
}

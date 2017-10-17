package ru.ezhov.db;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Класс, который подключается
 *
 * @author deonisius
 */
public class ConnectionDAO {

    public static DataSource getDataSource() throws Exception {
        InitialContext cxt = new InitialContext();
        if (cxt == null) {
            throw new Exception("Uh oh -- no context!");
        }

        DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");

        if (ds == null) {
            throw new Exception("Data source not found!");
        }
        return ds;
    }
}

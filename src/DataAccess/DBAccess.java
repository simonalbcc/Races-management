package DataAccess;

import java.sql.SQLException;

public class DBAccess {
    public void closeConnection() throws SQLException {
        SingletonConnexion.getInstance().close();
    }
}

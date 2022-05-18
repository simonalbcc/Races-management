package DataAccess;

import Exception.DataException;

import java.sql.SQLException;

public class DBAccess {
    public void closeConnection() throws DataException {
        try {
            SingletonConnexion.getInstance().close();
        } catch (SQLException exception) {
            throw new DataException(exception);
        }
    }
}

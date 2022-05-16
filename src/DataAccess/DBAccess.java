package DataAccess;

import Exception.DataBaseException;

import java.sql.SQLException;

public class DBAccess {
    public void closeConnection() throws DataBaseException {
        try {
            SingletonConnexion.getInstance().close();
        } catch (SQLException e) {
            throw new DataBaseException();
        }
    }
}

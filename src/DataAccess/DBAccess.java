//region packages & imports
package DataAccess;

import Exception.DataException;
import java.sql.Connection;
import java.sql.SQLException;
//endregion

public class DBAccess {
    private Connection connection;
    public DBAccess()throws DataException {
        connection = SingletonConnexion.getInstance();
    }


    public void closeConnection() throws DataException {
        try {
            connection.close();
        } catch (SQLException exception) {
            throw new DataException();
        }
    }

}

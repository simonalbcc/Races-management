//region packages & imports
package DataAccess;

import java.sql.*;

import Exception.DataException;


public class SingletonConnexion {
    private static Connection singletonConnexion;
    private SingletonConnexion()throws DataException {
        try {
            singletonConnexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Races", "root", "SimonAlexis2022");
        } catch (SQLException exception) {
            throw new DataException(exception);
        }
    }



    public static Connection getInstance() throws DataException {
        if(singletonConnexion == null){
            new SingletonConnexion();
        }
        return singletonConnexion;
    }
}

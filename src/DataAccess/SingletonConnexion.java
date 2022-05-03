package DataAccess;
import java.sql.*;

public class SingletonConnexion {
    private static Connection singletonConnexion;
    private SingletonConnexion()throws SQLException{
        singletonConnexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Races", "root", "Simon_Alexis2022");
    }

    public static Connection getInstance() throws SQLException{
        if(singletonConnexion == null){
            new SingletonConnexion();
        }
        return singletonConnexion;
    }
}

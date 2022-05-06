package DataAccess;

import Model.Driver;

import java.sql.*;

public class DriverDBAccess {

    public void getADriver(){
        Driver driver;
        try{
            String sql = "select * from Driver where serial_number = ?";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            statement.setInt(1, 45678);

            ResultSet data = statement.executeQuery();
            data.next();
        } catch (SQLException exception){

        }
    }

    // close connection = close program




}

package DataAccess;

import Model.Driver;
import java.sql.*;

public class DriverDBAccess {

    public void addDriver(Driver driver){
        try{
            String sql = "insert into Driver values(?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            statement.setInt(1, driver.getSerialNumber()); // sequence
            statement.setString(2, driver.getLastNameFirstName());
            statement.setInt(3, driver.getPhoneNumber());
            statement.setString(4, driver.getStreetAndNumber());
            statement.setString(5, driver.getNationality());
            statement.setString(6, driver.getTeam().getName());
            statement.setBoolean(7, driver.isHasRenewedCommitmentContract()); // à mettre sous forme de case cochée
            statement.setDate(8, new java.sql.Date(driver.getBirthdate().getTimeInMillis()));
            statement.setString(9, driver.getHome().getWording());


            int insertedLinesNumber = statement.executeUpdate();

        } catch (SQLException exception){

        }
    }

    // close connection = close program




}

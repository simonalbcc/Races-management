package DataAccess;

import Model.*;
import Model.Driver;
import Exception.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DriverDBAccess implements DriverDAO {

    public void addDriver(Model.Driver driver) throws AddDriverException {
        try{
            String sql = "insert into Driver (number, last_name_first_name, phone_number, street_and_number, nationality, team, has_renewed_commitment_contract, birthdate, home)values(?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            statement.setInt(1, driver.getNumber());
            statement.setString(2, driver.getLastNameFirstName());
            if(driver.getNumber() == null){
                statement.setNull(3,Types.INTEGER);
            }else{
                statement.setString(3, driver.getPhoneNumber());
            }
            statement.setString(4, driver.getStreetAndNumber());
            statement.setString(5, driver.getNationality());
            statement.setString(6, driver.getTeam().getName());
            statement.setBoolean(7, driver.isHasRenewedCommitmentContract());
            statement.setDate(8, new java.sql.Date(driver.getBirthdate().getTimeInMillis()));
            statement.setInt(9, driver.getHome().getNumber());

            statement.executeUpdate();

        } catch (Exception exception){
            throw new AddDriverException(driver, exception);
        }
    }

    public void updateDriver(Driver driver) throws UpdateException {
        try{
            String sql = "update Driver set last_name_first_name = ?, phone_number = ?, street_and_number = ?, nationality = ?, team = ?, has_renewed_commitment_contract = ?, birthdate = ?, home = ? " +
                         "where number = ?";
            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            statement.setString(1, driver.getLastNameFirstName());
            statement.setString(2, driver.getPhoneNumber());
            statement.setString(3, driver.getStreetAndNumber());
            statement.setString(4, driver.getNationality());
            statement.setString(5, driver.getTeam().getName());
            statement.setBoolean(6, driver.isHasRenewedCommitmentContract());
            statement.setDate(7, new java.sql.Date(driver.getBirthdate().getTimeInMillis()));
            statement.setInt(8, driver.getHome().getNumber());
            statement.setInt(9, driver.getNumber());

            statement.executeUpdate();

        } catch (Exception exception){
           throw new UpdateException(exception, driver);
        }
    }

    public void deleteDriver(int driverNumber) throws DeleteDriverException {
        try{
            String sql = "delete from Accident where driver = ?;";
            String sql2 = "delete from Ranking where driver = ?;";
            String sql3 = "delete from Driver where number = ?;";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);
            PreparedStatement statement2 = SingletonConnexion.getInstance().prepareStatement(sql2);
            PreparedStatement statement3 = SingletonConnexion.getInstance().prepareStatement(sql3);

            statement.setInt(1,driverNumber);
            statement2.setInt(1,driverNumber);
            statement3.setInt(1,driverNumber);

            statement.executeUpdate();
            statement2.executeUpdate();
            statement3.executeUpdate();

        } catch (Exception exception){
           throw new DeleteDriverException(exception);
        }
    }

    public ArrayList<Driver> getAllDrivers()throws DataBaseException{
        ArrayList<Driver> drivers = new  ArrayList<Driver>();
        try{
            Driver driver;

            String sql = "select driver.number, " +
                    "            last_name_first_name, " +
                                "phone_number, " +
                                "street_and_number, " +
                                "nationality, " +

                                "team," +
                                "web_site_address, " +

                                "has_renewed_commitment_contract, " +
                                "birthdate, " +

                                "loc.number, " +
                                "postal_code, " +
                                "city_name, " +
                                "country " +
                                "from Driver driver inner join Team team on driver.team = team.name inner join Locality loc on driver.home = loc.number" +
                                " order by driver.number";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            ResultSet data = statement.executeQuery();

            while(data.next()){
                GregorianCalendar birthdate = new GregorianCalendar();
                birthdate.setTime(data.getDate(9));

                driver = new Driver(data.getInt(1),
                                    data.getString(2),
                                    data.getString(3),
                                    data.getString(4),
                                    data.getString(5),
                                    new Team(data.getString(6), data.getString(7)),
                                    data.getBoolean(8),
                                    birthdate,
                                    new Locality(data.getInt(10), data.getInt(11), data.getString(12), data.getString(13)));
                drivers.add(driver);
            }

        } catch (SQLException exception){
            throw new DataBaseException(exception);
        }
        return drivers;
    }
    public Driver getADriver(int driverNumber)throws SelectADriverException{
        Driver driver;
        try{

            String sql = "select driver.number, " +
                                "last_name_first_name, " +
                                "phone_number, " +
                                "street_and_number, " +
                                "nationality, " +

                                "team," +
                                "web_site_address, " +

                                "has_renewed_commitment_contract, " +
                                "birthdate, " +

                                "loc.number, " +
                                "postal_code, " +
                                "city_name, " +
                                "country " +
                                "from Driver driver inner join Team team on driver.team = team.name inner join Locality loc on driver.home = loc.number" +
                                " where driver.number = ?";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            statement.setInt(1, driverNumber);

            ResultSet data = statement.executeQuery();

            data.next();

            GregorianCalendar birthdate = new GregorianCalendar();
            birthdate.setTime(data.getDate(9));

            driver = new Driver(data.getInt(1),
                                data.getString(2),
                                data.getString(3),
                                data.getString(4),
                                data.getString(5),
                                new Team(data.getString(6), data.getString(7)),
                                data.getBoolean(8),
                                birthdate,
                                new Locality(data.getInt(10), data.getInt(11), data.getString(12), data.getString(13)));
        } catch (SQLException exception){
            throw new SelectADriverException(exception);
        }
        return driver;
    }

}





    // close connection = close program


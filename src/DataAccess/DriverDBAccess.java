package DataAccess;

import Model.*;
import Model.Driver;
import Exception.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DriverDBAccess implements DriverDAO {
    private Connection connection;
    public DriverDBAccess()throws DataException {
        connection = SingletonConnexion.getInstance();
    }

    public void addDriver(Driver driver) throws AddDriverException {
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
            statement.setBoolean(7, driver.getHasRenewedCommitmentContract());
            statement.setDate(8, new java.sql.Date(driver.getBirthdate().getTimeInMillis()));
            statement.setInt(9, driver.getHome().getNumber());

            statement.executeUpdate();

        } catch (Exception exception){
            throw new AddDriverException(driver, exception);
        }
    }
    public void addDriverToRanking(Driver driver) throws AddDriverException {
        try{
            String sql = "";
            PreparedStatement statement = connection.prepareStatement(sql);

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
            statement.setBoolean(7, driver.getHasRenewedCommitmentContract());
            statement.setDate(8, new java.sql.Date(driver.getBirthdate().getTimeInMillis()));
            statement.setInt(9, driver.getHome().getNumber());

            statement.executeUpdate();

        } catch (Exception exception){
            throw new AddDriverException(driver, exception); // à changer
        }
    }
    public void updateDriver(Driver driver) throws UpdateException {
        try{
            String sql = "update Driver set last_name_first_name = ?, phone_number = ?, street_and_number = ?, nationality = ?, team = ?, has_renewed_commitment_contract = ?, birthdate = ?, home = ? " +
                         "where number = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, driver.getLastNameFirstName());
            statement.setString(2, driver.getPhoneNumber());
            statement.setString(3, driver.getStreetAndNumber());
            statement.setString(4, driver.getNationality());
            statement.setString(5, driver.getTeam().getName());
            statement.setBoolean(6, driver.getHasRenewedCommitmentContract());
            statement.setDate(7, new java.sql.Date(driver.getBirthdate().getTimeInMillis()));
            statement.setInt(8, driver.getHome().getNumber());
            statement.setInt(9, driver.getNumber());

            statement.executeUpdate();

        } catch (SQLException exception){
           throw new UpdateException(exception, driver); // à vérifier
        }
    }
    public void deleteDriver(int driverNumber) throws DeleteDriverException {
        try{
            String sql = "delete from Accident where driver = ?;";
            String sql2 = "delete from Ranking where driver = ?;";
            String sql3 = "delete from Driver where number = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            PreparedStatement statement3 = connection.prepareStatement(sql3);

            statement.setInt(1,driverNumber);
            statement2.setInt(1,driverNumber);
            statement3.setInt(1,driverNumber);

            statement.executeUpdate();
            statement2.executeUpdate();
            statement3.executeUpdate();

        } catch (SQLException exception){
           throw new DeleteDriverException(exception);
        }
    }


    public ArrayList<Driver> getAllDrivers()throws DataException{
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

            PreparedStatement statement = connection.prepareStatement(sql);

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
            throw new DataException(exception);
        }
        return drivers;
    }
    public Driver getADriver(int driverNumber)throws DriverException{
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

            PreparedStatement statement = connection.prepareStatement(sql);
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
            throw new DriverException();
        }
        return driver;
    }
    public Driver getADriver(String name)throws DriverException{
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
                    " where driver.last_name_first_name = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);

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

        } catch (SQLException exception){ // à vérifier
            throw new DriverException();
        }
        return driver;
    }


}


// à optimiser


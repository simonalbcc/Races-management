package DataAccess;

import Model.*;
import Model.Driver;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DriverDBAccess implements DAO {

    public int addDriver(Model.Driver driver){
        int insertedLinesNumber = 0;
        try{
            String sql = "insert into Driver (last_name_first_name, phone_number, street_and_number, nationality, team, has_renewed_commitment_contract, birthdate)values(?,?,?,?,?,?,?)";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            statement.setString(1, driver.getLastNameFirstName());
            statement.setLong(2, driver.getPhoneNumber());
            statement.setString(3, driver.getStreetAndNumber());
            statement.setString(4, driver.getNationality());
            statement.setString(5, driver.getTeam().getName());
            statement.setBoolean(6, driver.isHasRenewedCommitmentContract());
            statement.setDate(7, new java.sql.Date(driver.getBirthdate().getTimeInMillis()));
            insertedLinesNumber += addLocality(driver);


            insertedLinesNumber += statement.executeUpdate();

        } catch (SQLException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        return insertedLinesNumber;
    }
    public int addLocality(Model.Driver driver){
        int insertedLinesNumber = 0;
        try{
            String sql = "insert into Locality (city_name, postal_code, country) values(?,?,?) inner join Driver d on l.number = d.home ";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            statement.setString(1, driver.getHome().getCity());
            statement.setInt(2, driver.getHome().getPostalCode());
            statement.setString(3, driver.getHome().getCountry());

            insertedLinesNumber += statement.executeUpdate();

        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return insertedLinesNumber;
    }
    public void updateDriver(){

    }
    public void deleteDriver(){

    }

    public ArrayList<Team> getAllTeams(){
        ArrayList<Team> teams = new  ArrayList<Team>();
        try{
            String sql = "select * from Team";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            ResultSet data = statement.executeQuery();

            while(data.next()){
                teams.add(new Team(data.getString(1), data.getString(2)));
            }


        } catch (SQLException exception){

        }
        return teams;
    }
    public ArrayList<Driver> getAllDrivers(){
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
                                    data.getLong(3),
                                    data.getString(4),
                                    data.getString(5),
                                    new Team(data.getString(6), data.getString(7)),
                                    data.getBoolean(8),
                                    birthdate,
                                    new Locality(data.getInt(10), data.getInt(11), data.getString(12), data.getString(13)));
                drivers.add(driver);
            }

        } catch (SQLException exception){
            exception.printStackTrace(); // à changer
        }
        return drivers;
    }



}

    // close connection = close program


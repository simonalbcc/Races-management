package DataAccess;

import Model.*;
import Model.Driver;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Date;

public class DriverDBAccess implements DAO {

    public int addDriver(Model.Driver driver){
        int insertedLinesNumber = 0;
        Locality locality;
        try{
            String sql = "insert into Driver (number, last_name_first_name, phone_number, street_and_number, nationality, team, has_renewed_commitment_contract, birthdate, home)values(?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            statement.setInt(1, driver.getSerialNumber());
            statement.setString(2, driver.getLastNameFirstName());
            statement.setLong(3, driver.getPhoneNumber());
            statement.setString(4, driver.getStreetAndNumber());
            statement.setString(5, driver.getNationality());
            statement.setString(6, driver.getTeam().getName());
            statement.setBoolean(7, driver.isHasRenewedCommitmentContract());
            statement.setDate(8, new java.sql.Date(driver.getBirthdate().getTimeInMillis()));
            locality = checkLocality(driver.getHome());

            if(locality != null){
                statement.setInt(9, locality.getNumber());
            } else {

            }

            insertedLinesNumber += statement.executeUpdate();

        } catch (SQLException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage()); // changer
        }
        return insertedLinesNumber;
    }

    public Locality checkLocality(Locality locality){
        Locality localityDB = null;
        try{
            String sql = "select * from Locality where city_name = "+locality.getCity()+" and postal_code = "+locality.getPostalCode()+" and country = "+locality.getCountry();
            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            ResultSet data = statement.executeQuery();
            localityDB = new Locality(data.getInt(1), data.getInt(3), data.getString(2), data.getString(4));

        } catch (SQLException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage()); // changer
        }
        return localityDB;
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

    public ArrayList<String> getAllCircuitsNames(){
        ArrayList<String> circuits = new ArrayList<String>();
        try{
            String circuit;

            String sql = "select name from Circuit";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            ResultSet data = statement.executeQuery();

            while(data.next()){
                circuit = data.getString(1);
                circuits.add(circuit);
            }

        } catch (SQLException exception){
            exception.printStackTrace(); // à changer
        }
        return circuits;
    }
    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName){
        ArrayList<Date> dates = new ArrayList<Date>();
        try{

            String sql = "select date from Race where circuit = ? ";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);
            statement.setString(1,circuitName);

            ResultSet data = statement.executeQuery();

            while(data.next()){
                dates.add(data.getDate(1));
            }

        } catch (SQLException exception){
            exception.printStackTrace(); // à changer
        }
        return dates;
    }
}



    // close connection = close program


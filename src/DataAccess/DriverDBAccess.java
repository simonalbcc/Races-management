package DataAccess;

import Model.*;
import Model.Driver;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DriverDBAccess {

    public void addDriver(Model.Driver driver){
        try{
            String sql = "insert into Driver values(?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            statement.setString(2, driver.getLastNameFirstName());
            statement.setLong(3, driver.getPhoneNumber());
            statement.setString(4, driver.getStreetAndNumber());
            statement.setString(5, driver.getNationality());
            statement.setString(6, driver.getTeam().getName());
            statement.setBoolean(7, driver.isHasRenewedCommitmentContract()); // à mettre sous forme de case cochée
            statement.setDate(8, new java.sql.Date(driver.getBirthdate().getTimeInMillis()));
            addLocality(driver);


            int insertedLinesNumber = statement.executeUpdate();

        } catch (SQLException exception){

        }
    }
    public void addLocality(Model.Driver driver){
        try{
            String sql = "insert into Locality values(?,?,?,?)";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            statement.setString(2, driver.getStreetAndNumber());
            statement.setInt(3, driver.getHome().getPostalCode());
            statement.setString(4, driver.getHome().getCountry());

            int insertedLinesNumber = statement.executeUpdate();

        } catch (SQLException exception){

        }
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
            Team team;
            java.sql.Date date;
            GregorianCalendar birthdate = new GregorianCalendar();
            Locality home;

            String sql = "select driver.number, last_name_first_name, phone_number, street_and_number, nationality, team,web_site_address, has_renewed_commitment_contract, birthdate,postal_code,city_name, country from Driver driver inner join Team team on driver.team = team.name inner join Locality loc on driver.home = loc.number";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            ResultSet data = statement.executeQuery();

            while(data.next()){

                date = data.getDate(9);
                birthdate.setTime(date);

                driver = new Driver(data.getInt(1),
                                    data.getString(2),
                                    data.getLong(3),
                                    data.getString(4),
                                    data.getString(5),
                                    new Team(data.getString(6), data.getString(7)),
                                    data.getBoolean(8),
                                    birthdate,
                                    new Locality(data.getInt(10), data.getString(11), data.getString(12)));
                drivers.add(driver);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return drivers;
    }



}

    // close connection = close program


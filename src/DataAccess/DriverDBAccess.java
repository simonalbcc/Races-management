package DataAccess;

import Model.*;

import java.sql.*;
import java.util.ArrayList;

public class DriverDBAccess {

    public void addDriver(Model.Driver driver){
        try{
            String sql = "insert into Driver values(?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            statement.setString(2, driver.getLastNameFirstName());
            statement.setInt(3, driver.getPhoneNumber());
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



}

    // close connection = close program


package Controller;

import Business.DriverManager;
import DataAccess.DAO;
import DataAccess.DriverDBAccess;
import Model.Driver;
import Model.Locality;
import Model.Ranking;
import Model.Team;

import java.util.ArrayList;
import java.util.Date;

public class Controller {
    private DAO dataAccess;
    private DriverManager driverManager;
    public Controller() {
        dataAccess = new DriverDBAccess();
        driverManager = new DriverManager();
    }

    public void addDriver(Driver driver){
       driverManager.addDriver(driver);
    }
    public ArrayList<Team> getAllTeams(){
        return driverManager.getAllTeams();
    }
    public ArrayList<Driver> getAllDrivers(){
        return driverManager.getAllDrivers();
    }

    public ArrayList<Ranking> getARaceRanking(String circuitName, String raceDate){return driverManager.getARaceRanking(circuitName,raceDate);}
}





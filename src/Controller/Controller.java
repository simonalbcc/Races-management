package Controller;

import Business.DriverManager;
import DataAccess.DAO;
import DataAccess.DriverDBAccess;
import Model.*;

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

    public ArrayList<String> getAllCircuitsNames(){
        return driverManager.getAllCircuitsNames();
    }
    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName) {return driverManager.getRaceDatesOfACircuit(circuitName);}
    public ArrayList<Ranking> getARaceRanking(String circuitName, String raceDate){return driverManager.getARaceRanking(circuitName,raceDate);}

    public ArrayList<Accident> getAccidentedDrivers(String startDate, String endDate){return driverManager.getAccidentedDrivers(startDate, endDate);}
}





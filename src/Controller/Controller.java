package Controller;

import Business.DriverManager;
import Model.*;
import Exception.DriverException;
import java.util.ArrayList;
import java.util.Date;

public class Controller {
    private DriverManager driverManager;
    public Controller() {
        driverManager = new DriverManager();
    }

    public void deleteDriver(int driverNumber){driverManager.deleteDriver(driverNumber); }

    public void addDriver(Driver driver) throws DriverException {
       driverManager.addDriver(driver);
    }
    public Integer getNumberLocality(Locality locality){
       return driverManager.getNumberLocality(locality);
    }
    public void createLocality(Locality locality){
        driverManager.createLocality(locality);
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

    public ArrayList<Accident> getAccidentedDrivers(Date startDate, Date endDate){return driverManager.getAccidentedDrivers(startDate, endDate);}
}





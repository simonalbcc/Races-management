package Controller;

import Business.DriverManager;
import Model.*;

import java.util.ArrayList;
import java.util.Date;

public class Controller {
    private DriverManager driverManager;
    public Controller() {
        driverManager = new DriverManager();
    }

    public void deleteDriver(Integer driverNumber)throws Exception{driverManager.deleteDriver(driverNumber); }
    public void addDriver(Driver driver) throws Exception {
       driverManager.addDriver(driver);
    }
    public void updateDriver(Driver driver)throws Exception{

    }


    public Integer getNumberLocality(Locality locality)throws Exception{
       return driverManager.getNumberLocality(locality);
    }
    public void createLocality(Locality locality)throws Exception{
        driverManager.createLocality(locality);
    }

    public ArrayList<Team> getAllTeams()throws Exception{
        return driverManager.getAllTeams();
    }
    public ArrayList<Driver> getAllDrivers(){
        return driverManager.getAllDrivers();
    }

    public ArrayList<String> getAllCircuitsNames()throws Exception{
        return driverManager.getAllCircuitsNames();
    }
    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName)throws Exception {return driverManager.getRaceDatesOfACircuit(circuitName);}
    public ArrayList<Ranking> getARaceRanking(String circuitName, String raceDate)throws Exception{return driverManager.getARaceRanking(circuitName,raceDate);}

    public ArrayList<Accident> getAccidentedDrivers(Date startDate, Date endDate)throws Exception{return driverManager.getAccidentedDrivers(startDate, endDate);}
}





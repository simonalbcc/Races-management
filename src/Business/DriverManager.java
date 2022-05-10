package Business;

import DataAccess.DriverDBAccess;
import DataAccess.DAO;
import Model.*;
import java.util.ArrayList;
import java.util.Date;

public class DriverManager {
    private DAO dataAccess;

    public DriverManager() {
        dataAccess = new DriverDBAccess();
    }

    public void addDriver(Driver driver){
        dataAccess.addDriver(driver);
    }
    public Integer getNumberLocality(Locality locality){
        return dataAccess.getNumberLocality(locality);
    }
    public void createLocality(Locality locality){
        dataAccess.createLocality(locality);
    }

    public ArrayList<Team> getAllTeams(){
        return dataAccess.getAllTeams();
    }
    public ArrayList<Driver> getAllDrivers(){
        return dataAccess.getAllDrivers();
    }

    public ArrayList<String> getAllCircuitsNames(){
        return dataAccess.getAllCircuitsNames();
    }
    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName) {return dataAccess.getRaceDatesOfACircuit(circuitName);}
    public ArrayList<Ranking> getARaceRanking(String circuitName, String raceDate){ return dataAccess.getARaceRanking(circuitName,raceDate);}

    public ArrayList<Accident> getAccidentedDrivers(String startDate, String endDate){return dataAccess.getAccidentedDrivers(startDate,endDate);}

}

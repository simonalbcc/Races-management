package Business;

import DataAccess.*;
import Model.*;
import java.util.ArrayList;
import java.util.Date;
import Exception.DriverException;

public class DriverManager {
    private DriverDAO driverAccess;
    private LocalityDAO localityAccess;
    private TeamDAO teamAccess;
    private CircuitDAO circuitAccess;
    private AccidentDAO accidentAccess;
    private RaceDAO raceAccess;

    public DriverManager() {
        driverAccess = new DriverDBAccess();
        localityAccess = new LocalityDBAccess();
        teamAccess = new TeamDBAccess();
        circuitAccess = new CircuitDBAccess();
        accidentAccess = new AccidentDBAccess();
        raceAccess = new RaceDBAccess();
    }

    public void deleteDriver(int driverNumber){ driverAccess.deleteDriver(driverNumber);}

    public void addDriver(Driver driver) throws Exception {
            if(driverAccess.getAllDrivers().contains(driver.getLastNameFirstName())||driverAccess.getAllDrivers().contains(driver.getPhoneNumber()) ){
                throw new DriverException(driver);
            } else {
                driverAccess.addDriver(driver);
            }
    }
    public Integer getNumberLocality(Locality locality){
        return localityAccess.getNumberLocality(locality);
    }
    public void createLocality(Locality locality){
        localityAccess.createLocality(locality);
    }

    public ArrayList<Team> getAllTeams(){
        return teamAccess.getAllTeams();
    }
    public ArrayList<Driver> getAllDrivers(){
        return driverAccess.getAllDrivers();
    }

    public ArrayList<String> getAllCircuitsNames(){
        return circuitAccess.getAllCircuitsNames();
    }
    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName) {return raceAccess.getRaceDatesOfACircuit(circuitName);}
    public ArrayList<Ranking> getARaceRanking(String circuitName, String raceDate){ return raceAccess.getARaceRanking(circuitName,raceDate);}

    public ArrayList<Accident> getAccidentedDrivers(Date startDate, Date endDate){return accidentAccess.getAccidentedDrivers(startDate,endDate);}

}

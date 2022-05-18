package Business;

import DataAccess.*;
import Model.*;
import Exception.DataException;
import java.util.ArrayList;
import java.util.Date;

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

    public void deleteDriver(int driverNumber) throws Exception { driverAccess.deleteDriver(driverNumber);}
    public void addDriver(Driver driver) throws Exception {
        driverAccess.addDriver(driver);
    }
    public void updateDriver(Driver driver) throws Exception{
        driverAccess.updateDriver(driver);
    }
    public void createLocality(Locality locality)throws Exception{
        localityAccess.createLocality(locality);
    }
    public ArrayList<Team> getAllTeams()throws Exception{
        return teamAccess.getAllTeams();
    }
    public ArrayList<Driver> getAllDrivers()throws Exception {
        return driverAccess.getAllDrivers();
    }
    public Driver getADriver(int driverNumber)throws Exception{
        return driverAccess.getADriver(driverNumber);
    }

    public ArrayList<String> getAllCircuitsNames()throws Exception{
        return circuitAccess.getAllCircuitsNames();
    }
    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName) throws Exception {return raceAccess.getRaceDatesOfACircuit(circuitName);}
    public ArrayList<Ranking> getARaceRanking(String circuitName, String raceDate) throws Exception{ return raceAccess.getARaceRanking(circuitName,raceDate);}
    public ArrayList<Accident> getAccidentedDrivers(Date startDate, Date endDate)throws Exception{return accidentAccess.getAccidentedDrivers(startDate,endDate);}
    public Integer getNumberLocality(Locality locality)throws Exception{
        return localityAccess.getNumberLocality(locality);
    }

    public ArrayList<Race> getWinningSponsorsOfACircuit(String circuitName){return raceAccess.getWinningSponsorsOfACircuit(circuitName);}

    public void closeConnection() throws DataException {
        new DBAccess().closeConnection();
    }

}

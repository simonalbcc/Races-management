package DataAccess;

import Model.*;

import java.util.ArrayList;
import java.util.Date;

public interface DAO {

    public void addDriver(Model.Driver driver);
    public Integer getNumberLocality(Locality locality);
    public void createLocality(Locality locality);
    public void updateDriver();
    public void deleteDriver(int driverNumber);
    public ArrayList<Team> getAllTeams();
    public ArrayList<Driver> getAllDrivers();
    public ArrayList<String> getAllCircuitsNames();
    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName);
    public ArrayList<Ranking> getARaceRanking(String circuitName, String raceDate);
    public ArrayList<Accident> getAccidentedDrivers(Date startDate, Date endDate);

}

package DataAccess;

import Model.*;

import java.util.ArrayList;
import java.util.Date;

public interface DAO {

    public Integer addDriver(Model.Driver driver);
    public void updateDriver();
    public void deleteDriver();
    public ArrayList<Team> getAllTeams();
    public ArrayList<Driver> getAllDrivers();
    public ArrayList<String> getAllCircuitsNames();
    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName);
    public ArrayList<Ranking> getARaceRanking(String circuitName, String raceDate);
    public ArrayList<Accident> getAccidentedDrivers(String startDate, String endDate);

}

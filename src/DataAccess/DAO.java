package DataAccess;

import Model.*;

import java.util.ArrayList;
import java.util.Date;

public interface DAO {

    public int addDriver(Model.Driver driver);
    public void updateDriver();
    public void deleteDriver();
    public ArrayList<Team> getAllTeams();
    public ArrayList<Driver> getAllDrivers();
    public Locality checkLocality(Locality locality);
    public ArrayList<String> getAllCircuitsNames();
    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName);


}

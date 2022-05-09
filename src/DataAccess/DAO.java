package DataAccess;

import Model.*;

import java.util.ArrayList;

public interface DAO {

    public int addDriver(Model.Driver driver);
    public void updateDriver();
    public void deleteDriver();
    public ArrayList<Team> getAllTeams();
    public ArrayList<Driver> getAllDrivers();


}

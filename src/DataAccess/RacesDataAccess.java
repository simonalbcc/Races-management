package DataAccess;

import Model.*;

import java.util.ArrayList;

public interface RacesDataAccess {

    public void addDriver();
    public void updateDriver();
    public void showDriver();
    public void deleteDriver();
    public ArrayList<Team> getAllTeams();
    public ArrayList<Driver> getAllDrivers();


}

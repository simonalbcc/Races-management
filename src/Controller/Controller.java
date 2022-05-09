package Controller;

import Business.DriverManager;
import DataAccess.DAO;
import DataAccess.DriverDBAccess;
import Model.Driver;
import Model.Locality;
import Model.Team;

import java.util.ArrayList;

public class Controller {
    private DAO dataAccess;
    private DriverManager driverManager;
    public Controller() {
        dataAccess = new DriverDBAccess();
        driverManager = new DriverManager();
    }

    public void addDriver(Driver driver){
       driverManager.addDriver(driver);
    }
    public ArrayList<Team> getAllTeams(){
        return driverManager.getAllTeams();
    }
    public ArrayList<Driver> getAllDrivers(){
        return driverManager.getAllDrivers();
    }



}

package Business;

import DataAccess.DriverDBAccess;
import DataAccess.DAO;
import Model.*;

import javax.swing.*;
import java.util.ArrayList;

public class DriverManager {
    private DAO dataAccess;

    public DriverManager() {
        dataAccess = new DriverDBAccess();
    }

    public void addDriver(Driver driver){
        dataAccess.addDriver(driver);
    }
    public ArrayList<Team> getAllTeams(){
        return dataAccess.getAllTeams();
    }
    public ArrayList<Driver> getAllDrivers(){
        return dataAccess.getAllDrivers();
    }




}

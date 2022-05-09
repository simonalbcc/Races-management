package Business;

import DataAccess.DriverDBAccess;
import Model.*;

import javax.swing.*;
import java.util.ArrayList;

public class DriverManager {
    private DriverDBAccess dataAccess;

    public DriverManager() {
        dataAccess = new DriverDBAccess();
    }

    public void addDriver(Driver driver){
        JOptionPane.showMessageDialog(null,  dataAccess.addDriver(driver)+" lignes mises à jour");
    }
    public ArrayList<Team> getAllTeams(){
        return dataAccess.getAllTeams();
    }
    public ArrayList<Driver> getAllDrivers(){
        return dataAccess.getAllDrivers();
    }




}

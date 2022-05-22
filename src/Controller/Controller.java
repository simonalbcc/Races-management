package Controller;

import Business.Manager;
import Model.*;
import Exception.DataException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Controller {
    private Manager manager;
    public Controller() throws DataException {
        manager = new Manager();
    }

    public void deleteDriver(Integer driverNumber) throws Exception {
        manager.deleteDriver(driverNumber); }
    public void addDriver(Driver driver) throws Exception {
       manager.addDriver(driver);
    }
    public void addDriverToRanking(Ranking ranking) throws Exception {
        manager.addDriverToRanking(ranking);
    }
    public void updateDriver(Driver driver) throws Exception{
        manager.updateDriver(driver);
    }

    public Integer getNumberLocality(Locality locality)throws Exception{
       return manager.getNumberLocality(locality);
    }
    public HashMap getLocalitiesName() throws DataException{
        return manager.getLocalitiesName();
    }

    public ArrayList<Team> getAllTeams()throws Exception{
        return manager.getAllTeams();
    }

    public ArrayList<Driver> getAllDrivers() throws Exception{
        return manager.getAllDrivers();
    }
    public Driver getADriver(int driverNumber) throws Exception{
        return manager.getADriver(driverNumber);
    }
    public Driver getADriver(String name) throws Exception{
        return manager.getADriver(name);
    }

    public int getCarFromName(String carName) throws Exception{
        return manager.getCarFromName(carName);
    }
    public void addCar(Car car) throws Exception {
        manager.addCar(car);
    }
    public ArrayList<String> getRemainingCarsInARanking(String circuitName, String date ,String teamName) throws Exception{
        return manager.getRemainingCarsInARanking(circuitName, date, teamName);
    }

    public ArrayList<String> getAllCircuitsNames()throws Exception{
        return manager.getAllCircuitsNames();
    }

    public Integer getARaceNumber(String circuitName, String date) throws Exception{
        return manager.getARaceNumber(circuitName,date);
    }

    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName)throws Exception {return manager.getRaceDatesOfACircuit(circuitName);}
    public ArrayList<Ranking> getARaceRanking(String circuitName, String raceDate)throws Exception{return manager.getARaceRanking(circuitName,raceDate);}
    public ArrayList<Integer> getPositionsRemainingInARanking(String circuitName, String raceDate) throws Exception {
        return manager.getPositionsRemainingInARanking(circuitName, raceDate);
    }
    public ArrayList<Accident> getAccidentedDrivers(Date startDate, Date endDate)throws Exception{return manager.getAccidentedDrivers(startDate, endDate);}
    public ArrayList<Race> getWinningSponsorsOfACircuit(String circuitName) throws Exception {return manager.getWinningSponsorsOfACircuit(circuitName);}

    public void closeConnection() throws DataException {
        manager.closeConnection();
    }
}





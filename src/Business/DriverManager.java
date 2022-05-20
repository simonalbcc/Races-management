package Business;

import DataAccess.*;
import Model.*;
import Exception.DataException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class DriverManager {
    private DriverDAO driverAccess;
    private LocalityDAO localityAccess;
    private TeamDAO teamAccess;
    private CircuitDAO circuitAccess;
    private AccidentDAO accidentAccess;
    private RaceDAO raceAccess;
    private CarDAO carAccess;

    public DriverManager() throws DataException {
        driverAccess = new DriverDBAccess();
        localityAccess = new LocalityDBAccess();
        teamAccess = new TeamDBAccess();
        circuitAccess = new CircuitDBAccess();
        accidentAccess = new AccidentDBAccess();
        raceAccess = new RaceDBAccess();
        carAccess = new CarAccess();
    }

    public void deleteDriver(int driverNumber) throws Exception { driverAccess.deleteDriver(driverNumber);}
    public void addDriver(Driver driver) throws Exception {
        driverAccess.addDriver(driver);
    }
    public void addDriverToRanking(Ranking ranking) throws Exception {
        driverAccess.addDriverToRanking(ranking);
    }
    public void updateDriver(Driver driver) throws Exception{
        driverAccess.updateDriver(driver);
    }
    public ArrayList<Driver> getAllDrivers()throws Exception {
        return driverAccess.getAllDrivers();
    }
    public Driver getADriver(int driverNumber)throws Exception{
        return driverAccess.getADriver(driverNumber);
    }
    public Driver getADriver(String name) throws Exception{
        return driverAccess.getADriver(name);
    }
    public ArrayList<Accident> getAccidentedDrivers(Date startDate, Date endDate)throws Exception{return accidentAccess.getAccidentedDrivers(startDate,endDate);}

    public void createLocality(Locality locality)throws Exception{
        localityAccess.createLocality(locality);
    }
    public Integer getNumberLocality(Locality locality)throws Exception{
        return localityAccess.getNumberLocality(locality);
    }

    public ArrayList<Team> getAllTeams()throws Exception{
        return teamAccess.getAllTeams();
    }

    public ArrayList<String> getAllCircuitsNames()throws Exception{
        return circuitAccess.getAllCircuitsNames();
    }

    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName) throws Exception {return raceAccess.getRaceDatesOfACircuit(circuitName);}
    public ArrayList<Ranking> getARaceRanking(String circuitName, String raceDate) throws Exception{ return raceAccess.getARaceRankings(circuitName,raceDate);}
    public ArrayList<Race> getWinningSponsorsOfACircuit(String circuitName) throws Exception {return raceAccess.getWinningSponsorsOfACircuit(circuitName);}

    public ArrayList getAllCarsName(String teamName) throws Exception{
        return carAccess.getAllCarsName(teamName);
    }
    public void addCar(Car car) throws Exception {
        carAccess.addCar(car);
    }

    public ArrayList<Integer> getPositionsRemainingInARanking(String circuitName, String date) throws Exception {
        ArrayList<Integer> positionsTaken = raceAccess.getPositionsRemainingInARanking(circuitName, date);
        ArrayList<Integer> positionsRemaining = new ArrayList<>();
        for (int position = 1; position < 20; position++){
            if(!positionsTaken.contains(position)) {
                positionsRemaining.add(position);
            }
        }
        return positionsRemaining;
    }

    public int getCarFromName(String carName) throws Exception {
        return carAccess.getCarFromName(carName);
    }
    public Integer getARaceNumber(String circuitName, String date) throws Exception {
        return raceAccess.getARaceNumber(circuitName,date);
    }

    public void closeConnection() throws DataException {
        new DBAccess().closeConnection();
    }

    public ArrayList<String> getRemainingCarsInARanking(String circuitName, String date ,String teamName) throws Exception {
        ArrayList<String> allCars = carAccess.getAllCarsName(teamName);
        ArrayList<String> invalidCars = carAccess.getEngagedCars(circuitName,date,teamName);

        for(int iCar = 0; iCar < invalidCars.size(); iCar++){
            allCars.remove(invalidCars.get(iCar));
        }
        return allCars;
    }
   /* public ArrayList<String> getAllDriversInARace(String circuitName,String date) throws Exception{
        ArrayList<String> driversEngaged = driverAccess.getAllDriversNameInARace(circuitName, date);
        ArrayList<Driver> allDriver = driverAccess.getAllDrivers();
        for(int iCar = 0; iCar < driversEngaged.size(); iCar++){
            allDriver.remove(driversEngaged.get(iCar));
        }
        return allDriver;
    } */
}

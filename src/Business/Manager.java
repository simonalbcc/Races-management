//region packages & imports
package Business;
import DataAccess.*;
import Model.*;
import Exception.DataException;
import Exception.NumberCarException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
//endregion

public class Manager {
    private DriverDAO driverAccess;
    private LocalityDAO localityAccess;
    private TeamDAO teamAccess;
    private CircuitDAO circuitAccess;
    private AccidentDAO accidentAccess;
    private RaceDAO raceAccess;
    private CarDAO carAccess;

    public Manager() throws DataException {
        driverAccess = new DriverDBAccess();
        localityAccess = new LocalityDBAccess();
        teamAccess = new TeamDBAccess();
        circuitAccess = new CircuitDBAccess();
        accidentAccess = new AccidentDBAccess();
        raceAccess = new RaceDBAccess();
        carAccess = new CarAccess();
    }


    //region driver
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
    //endregion


    //region locality
    public Integer getNumberLocality(Locality locality)throws Exception{
        return localityAccess.getNumberLocality(locality);
    }
    public HashMap getLocalitiesName() throws Exception {
        return localityAccess.getLocalitiesName();
    }
    public ArrayList<String> getCountries() throws Exception {
        return localityAccess.getCountries();
    }
    //endregion


    //region team
    public ArrayList<Team> getAllTeams()throws Exception{
        return teamAccess.getAllTeams();
    }
    //endregion


    //region circuit
    public ArrayList<String> getAllCircuitsNames()throws Exception{
        return circuitAccess.getAllCircuitsNames();
    }
    public ArrayList<Race> getWinningSponsorsOfACircuit(String circuitName) throws Exception {return raceAccess.getWinningSponsorsOfACircuit(circuitName);}
    //endregion


    //region race
    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName) throws Exception {return raceAccess.getRaceDatesOfACircuit(circuitName);}
    public ArrayList<Ranking> getARaceRanking(String circuitName, String raceDate) throws Exception{ return raceAccess.getARaceRankings(circuitName,raceDate);}
    public Integer getARaceNumber(String circuitName, String date) throws Exception {
        return raceAccess.getARaceNumber(circuitName,date);
    }
    //endregion


    //region car
    public void addCar(Car car) throws Exception {
        if(car.getNumber() != null && car.getNumber() > 0){

        } else {
            throw new NumberCarException();
        }
    }
    public int getCarFromName(String carName) throws Exception {
        return carAccess.getCarFromName(carName);
    }
    //endregion


    //region ranking
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
    public ArrayList<String> getRemainingCarsInARanking(String circuitName, String date ,String teamName) throws Exception {
        ArrayList<String> allCars = carAccess.getAllCarsName(teamName);
        ArrayList<String> invalidCars = carAccess.getEngagedCars(circuitName,date,teamName);

        for(int iCar = 0; iCar < invalidCars.size(); iCar++){
            allCars.remove(invalidCars.get(iCar));
        }
        return allCars;
    }
    //endregion


    //region connection
    public void closeConnection() throws DataException {
        new DBAccess().closeConnection();
    }
    //endregion

}

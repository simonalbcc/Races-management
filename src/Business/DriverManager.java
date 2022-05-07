package Business;

import DataAccess.DriverDBAccess;
import DataAccess.RacesDataAccess;
import Model.Driver;

public class DriverManager {
    private DriverDBAccess dataAccess = new DriverDBAccess();

    public void addDriver(Driver driver){
        dataAccess.addDriver(driver);
    }







}

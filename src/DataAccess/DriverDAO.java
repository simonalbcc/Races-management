package DataAccess;

import Model.Driver;
import java.util.ArrayList;

public interface DriverDAO {

    public ArrayList<Driver> getAllDrivers();
    public void addDriver(Model.Driver driver) throws Exception;
    public void updateDriver()throws Exception;
    public void deleteDriver(int driverNumber)throws Exception;
    public ArrayList<String> getAllDriversNames();

}

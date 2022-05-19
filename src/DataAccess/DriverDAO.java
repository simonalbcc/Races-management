//region packages & imports
package DataAccess;

import Model.Driver;
import java.util.ArrayList;
//endregion

public interface DriverDAO {

    public void addDriver(Model.Driver driver) throws Exception;
    public void deleteDriver(int driverNumber)throws Exception;
    public ArrayList<Driver> getAllDrivers()throws Exception;
    public Driver getADriver(int driverNumber) throws Exception;
    public Driver getADriver(String name) throws Exception;
    public void updateDriver(Driver driver) throws Exception;

    public void addDriverToRanking(Driver driver)throws Exception;
}

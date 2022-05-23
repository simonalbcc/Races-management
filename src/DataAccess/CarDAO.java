//region packages & imports
package DataAccess;

import java.util.ArrayList;
import java.util.Date;

import Model.Car;
//endregion

public interface CarDAO {

    public ArrayList getAllCarsName(String teamName) throws Exception;
    public void addCar(Car car)throws Exception;
    public int getCarFromName(String carName) throws Exception;
    public ArrayList<String> getEngagedCars(String circuitName, Date date, String teamName) throws Exception;

}

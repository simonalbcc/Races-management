package DataAccess;

import java.util.ArrayList;
import Model.Car;

public interface CarDAO {

    public ArrayList getAllCarsName(String teamName) throws Exception;
    public void addCar(Car car)throws Exception;

}

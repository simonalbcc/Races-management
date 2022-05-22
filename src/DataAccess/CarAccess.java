//region packages & imports
package DataAccess;

import java.sql.*;
import Exception.AddCarException;
import Exception.CarException;
import Exception.DataException;
import Model.Car;
import java.util.ArrayList;
//endregion

public class CarAccess implements CarDAO{
    private Connection connection;
    public CarAccess()throws DataException {
        connection = SingletonConnexion.getInstance();
    }


    @Override
    public ArrayList getAllCarsName(String teamName) throws CarException {
        ArrayList<String> carsNameAndNumber = new ArrayList<>();
        try{

            String sql = "select name from Car where membership = ? ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,teamName);

            ResultSet data = statement.executeQuery();

            while(data.next()){
                carsNameAndNumber.add(data.getString(1));
            }

        } catch (SQLException exception){
            throw new CarException();
        }
        return carsNameAndNumber;
    }

    public int getCarFromName(String carName) throws CarException {
            int car;
            try{

                String sql = "select number from Car where name = ? ";

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1,carName);

                ResultSet data = statement.executeQuery();

                data.next();
                car = data.getInt(1);

            } catch (SQLException exception){
                throw new CarException();
            }
            return car;
        }

    public ArrayList<String> getEngagedCars(String circuitName, String date, String teamName) throws CarException {
        ArrayList<String> engagedCarsName = new ArrayList<>();
        try{
            String sql = "select car.name\n" +
                    "from Ranking ranking\n" +
                    "inner join Car car on ranking.car = car.number\n" +
                    "inner join Race race on ranking.race = race.serial_number\n" +
                    "inner join Driver driver on ranking.driver = driver.number\n" +
                    "where race.circuit = ? and race.date = ? and driver.team = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,circuitName);
            statement.setString(2,date);
            statement.setString(3,teamName);

            ResultSet data = statement.executeQuery();

            while(data.next()){
                engagedCarsName.add(data.getString(1));
            }

        } catch (SQLException exception) {
            throw new CarException();
        }
        return engagedCarsName;
    }

    @Override
    public void addCar(Car car) throws AddCarException {
        try{
            String sql = "insert into Car\n" +
                    "values(?,?,?,?,?,?);";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,car.getNumber());
            statement.setDouble(2,car.getAverageConsumption());
            statement.setInt(3,car.getPower());
            if(car.getImprovedFrom() == null){
                statement.setNull(4, Types.NULL);
            }else{
                statement.setInt(4,car.getImprovedFrom().getNumber());
            }
            statement.setString(5,car.getMembership().getName());
            statement.setString(6,car.getName());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new AddCarException();
        }
    }

}
// vérifier si DataException c'est ok
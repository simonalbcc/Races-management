package DataAccess;

import java.sql.*;

import Exception.AddCarException;
import Exception.DataException;
import Model.Car;

import java.util.ArrayList;


public class CarAccess implements CarDAO{
    private Connection connection;
    public CarAccess()throws DataException {
        connection = SingletonConnexion.getInstance();
    }

    @Override
    public ArrayList getAllCarsName(String teamName) throws DataException {
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
            throw new DataException(exception); // à changer
        }
        return carsNameAndNumber;
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
            throw new AddCarException(exception);
        }
    }
}

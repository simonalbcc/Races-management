package DataAccess;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Exception.CarException;
import Exception.DataException;

import java.sql.SQLException;
import java.util.ArrayList;


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

}

//region packages & imports
package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import Exception.DataException;
import Exception.CountryException;
import Exception.LocalityException;
import Model.Locality;
//endregion

public class LocalityDBAccess implements LocalityDAO{
    private Connection connection;
    public LocalityDBAccess()throws DataException {
        connection = SingletonConnexion.getInstance();
    }


    public Integer getNumberLocality(Locality locality)throws LocalityException{
        Integer number;
        try{
            String sql = "select number from Locality where city_name = ? and postal_code = ? and country = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, locality.getCity());
            statement.setInt(2, locality.getPostalCode());
            statement.setString(3, locality.getCountry());

            ResultSet data = statement.executeQuery();
            data.next();
            number = data.getInt("number");

        } catch (SQLException exception){
            throw new LocalityException();
        }
        return number;
    }

    public HashMap getLocalitiesName() throws LocalityException {
        HashMap<Integer, String> localities = new HashMap<>();
        localities.put(0, "Séléctionner...");
        try{
            String sql = "select postal_code, city_name from Locality";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet data = statement.executeQuery();
            while (data.next()){
                localities.put(data.getInt(1), data.getString(2));
            }

        } catch (SQLException exception){
            throw new LocalityException();
        }
        return localities;
    }

    public ArrayList<String> getCountries() throws CountryException {
        ArrayList<String> countries = new ArrayList<>();
        try{
            String sql = "select distinct country from Locality";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet data = statement.executeQuery();
            while (data.next()){
                countries.add(data.getString(1));
            }

        } catch (SQLException exception){
            throw new CountryException();
        }
        return countries;
    }

}

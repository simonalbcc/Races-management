package DataAccess;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Exception.DataException;
import Exception.LocalityException;
import Model.Locality;

public class LocalityDBAccess implements LocalityDAO{
    public Integer getNumberLocality(Locality locality)throws LocalityException{
        Integer number;
        try{
            System.out.println(locality.getCountry());
            System.out.println(locality.getCity());
            System.out.println(locality.getPostalCode());
            String sql = "select number from Locality where city_name = ? and postal_code = ? and country = ?";
            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            statement.setString(1, locality.getCity());
            statement.setInt(2, locality.getPostalCode());
            statement.setString(3, locality.getCountry());

            ResultSet data = statement.executeQuery();
            data.next();
            System.out.println(data.getInt(1));
            number = data.getInt("number");

        } catch (SQLException | DataException exception){
            throw new LocalityException(exception);
        }
        return number;
    }
    public void createLocality(Locality locality) throws LocalityException {
        try{
            String sql = "insert into Locality (city_name, postal_code, country) values (?,?,?)";
            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            statement.setString(1, locality.getCity());
            statement.setInt(2, locality.getPostalCode());
            statement.setString(3, locality.getCountry());

            statement.executeUpdate();

        } catch(Exception exception){
            throw new LocalityException(exception);
        }
    }

}

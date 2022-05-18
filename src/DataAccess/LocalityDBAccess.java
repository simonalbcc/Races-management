package DataAccess;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Exception.LocalityException;
import Exception.DataBaseException;
import Model.Locality;

public class LocalityDBAccess implements LocalityDAO{
    public Integer getNumberLocality(Locality locality)throws DataBaseException{
        Integer number = null;
        try{
            String sql = "select number from Locality where city_name = '"+locality.getCity()+"' and postal_code = "+locality.getPostalCode()+" and country = '"+locality.getCountry()+"'";
            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            ResultSet data = statement.executeQuery();
            data.next();

            number = data.getInt(1);

        } catch (SQLException exception){
            throw new DataBaseException(exception);
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

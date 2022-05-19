//region packages & imports
package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Exception.DataException;
import Exception.CircuitException;
//endregion

public class CircuitDBAccess implements CircuitDAO {
    private Connection connection;
    public CircuitDBAccess()throws DataException {
        connection = SingletonConnexion.getInstance();
    }
    public ArrayList<String> getAllCircuitsNames() throws CircuitException {
        ArrayList<String> circuits = new ArrayList<String>();
        try{
            String circuit;

            String sql = "select name from Circuit";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet data = statement.executeQuery();

            while(data.next()){
                circuit = data.getString(1);
                circuits.add(circuit);
            }

        } catch (SQLException exception){
            throw new CircuitException(exception); // vérifier + changer
        }
        return circuits;
    }

}

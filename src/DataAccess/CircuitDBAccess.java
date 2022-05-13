package DataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Exception.CircuitException;

public class CircuitDBAccess implements CircuitDAO {
    public ArrayList<String> getAllCircuitsNames() throws CircuitException {
        ArrayList<String> circuits = new ArrayList<String>();
        try{
            String circuit;

            String sql = "select name from Circuit";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);

            ResultSet data = statement.executeQuery();

            while(data.next()){
                circuit = data.getString(1);
                circuits.add(circuit);
            }

        } catch (SQLException exception){
            throw new CircuitException(exception);
        }
        return circuits;
    }

}
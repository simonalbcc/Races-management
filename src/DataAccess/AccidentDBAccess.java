//region packages & imports
package DataAccess;

import Model.Accident;
import Model.Driver;
import Model.Locality;
import Model.Team;
import Exception.DataException;
import Exception.AccidentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
//endregion

public class AccidentDBAccess implements AccidentDAO{
    private Connection connection;
    public AccidentDBAccess() throws DataException {
        connection = SingletonConnexion.getInstance();
    }


    @Override
    public ArrayList<Accident> getAccidentedDrivers(Date startDate, Date endDate) throws AccidentException {
        ArrayList<Accident> accidents = new ArrayList<Accident>();
        try{

            String sql = "select accident.date, driver.last_name_first_name, driver.street_and_number, locality.city_name, driver.team\n" +
                    "from Accident accident\n" +
                    "inner join Driver driver on accident.driver = driver.number\n" +
                    "inner join Locality locality on driver.home = locality.number\n" +
                    "where accident.date between ? and ?\n" +
                    "order by date";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1,new java.sql.Date(startDate.getTime()));
            statement.setDate(2,new java.sql.Date(endDate.getTime()));

            ResultSet data = statement.executeQuery();

            while(data.next()){
                Locality locality = new Locality(null,null,data.getString("locality.city_name"),null);
                Team team = new Team(data.getString("driver.team"));
                Driver driver = new Driver(null,data.getString("driver.last_name_first_name"),null,data.getString("driver.street_and_number"),null,
                        team, null, null, locality);
                accidents.add(new Accident(data.getDate("accident.date"), driver));
            }
        } catch (SQLException exception){
            throw new AccidentException();
        }
        return accidents;
    }

}

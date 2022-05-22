//region packages & imports
package DataAccess;

import Model.Team;
import Exception.DataException;
import Exception.TeamException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//endregion

public class TeamDBAccess implements TeamDAO{
    private Connection connection;

    public TeamDBAccess()throws DataException {
        connection = SingletonConnexion.getInstance();
    }


    public ArrayList<Team> getAllTeams() throws TeamException {
        ArrayList<Team> teams = new  ArrayList<Team>();
        try{
            String sql = "select * from Team";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()){
                teams.add(new Team(data.getString(1), data.getString(2)));
            }


        } catch (SQLException exception){
            throw new TeamException();
        }
        return teams;
    }

}

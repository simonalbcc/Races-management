package DataAccess;

import Model.Team;
import Exception.TeamException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeamDBAccess implements TeamDAO{
    public ArrayList<Team> getAllTeams() throws TeamException {
        ArrayList<Team> teams = new  ArrayList<Team>();
        try{
            String sql = "select * from Team";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()){
                teams.add(new Team(data.getString(1), data.getString(2)));
            }


        } catch (SQLException exception){
            throw new TeamException(exception);
        }
        return teams;
    }

    public ArrayList<String> getAllTeamsNames()throws TeamException{
        ArrayList<String> teamsNames = new  ArrayList<String>();
        try{
            String sql = "select name from Team";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()){
                teamsNames.add(data.getString(1));
            }


        } catch (SQLException exception){
            throw new TeamException(exception);
        }
        return teamsNames;
    }
}

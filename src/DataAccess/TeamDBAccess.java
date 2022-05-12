package DataAccess;

import Model.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeamDBAccess implements TeamDAO{
    public ArrayList<Team> getAllTeams(){
        ArrayList<Team> teams = new  ArrayList<Team>();
        try{
            String sql = "select * from Team";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()){
                teams.add(new Team(data.getString(1), data.getString(2)));
            }


        } catch (SQLException exception){

        }
        return teams;
    }
}

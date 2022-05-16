package DataAccess;

import Model.Team;

import java.util.ArrayList;

public interface TeamDAO {

    public ArrayList<Team> getAllTeams()throws Exception;
    public ArrayList<String> getAllTeamsNames()throws Exception;


}

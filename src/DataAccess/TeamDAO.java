//region packages & imports
package DataAccess;

import Model.Team;

import java.util.ArrayList;
//endregion

public interface TeamDAO {

    public ArrayList<Team> getAllTeams()throws Exception;

}

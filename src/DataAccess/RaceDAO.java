package DataAccess;

import Model.Race;
import Model.Ranking;

import java.util.ArrayList;
import java.util.Date;

public interface RaceDAO {
    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName)throws Exception;
    public ArrayList<Ranking> getARaceRankings(String circuitName, String raceDate)throws Exception;
    public ArrayList<Integer> getPositionsRemainingInARanking(int numRace)throws Exception;
    public Integer getARaceNumber(String circuitName, Date date)throws Exception;
    public ArrayList<Race> getWinningSponsorsOfACircuit(String circuitName) throws Exception;
}

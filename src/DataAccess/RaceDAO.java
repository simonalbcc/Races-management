package DataAccess;

import Model.Ranking;

import java.util.ArrayList;
import java.util.Date;

public interface RaceDAO {
    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName);
    public ArrayList<Ranking> getARaceRanking(String circuitName, String raceDate);
}

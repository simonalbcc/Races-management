package DataAccess;

import Model.*;
import Exception.RaceException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class RaceDBAccess implements RaceDAO{
    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName) throws RaceException {
        ArrayList<Date> dates = new ArrayList<Date>();
        try{

            String sql = "select date from Race where circuit = ? ";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);
            statement.setString(1,circuitName);

            ResultSet data = statement.executeQuery();

            while(data.next()){
                dates.add(data.getDate(1));
            }

        } catch (SQLException exception){
            throw new RaceException(exception);
        }
        return dates;
    }
    public ArrayList<Ranking> getARaceRanking(String circuitName, String raceDate){
        ArrayList<Ranking> rankings = new ArrayList<Ranking>();
        try{
            String sql = "select ranking.position, car.number, car.power, driver.last_name_first_name, ranking.record\n" +
                    "from Ranking ranking\n" +
                    "inner join Car car on ranking.car = car.number\n" +
                    "inner join Driver driver on ranking.driver = driver.number\n" +
                    "inner join Race race on ranking.race = race.serial_number\n" +
                    "where race.circuit = ? and race.date = ?\n" +
                    "order by position;";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);
            statement.setString(1,circuitName);
            statement.setString(2,raceDate);

            ResultSet data = statement.executeQuery();

            while(data.next()){
                rankings.add(new Ranking(new Car(data.getInt("car.number"),data.getDouble("car.power")),
                        null,data.getInt("ranking.position"),null,null,data.getDouble("ranking.record"),
                        new Driver(data.getString("driver.last_name_first_name"))));
            }

        } catch (SQLException exception){
            exception.printStackTrace(); // à changer
        }
        return rankings;
    }
    public ArrayList<Race> getWinningSponsorsOfACircuit(String circuitName){
        ArrayList<Race> races = new ArrayList<Race>();
        try{
            String sql = "select race.date, team.name, sponsor.company\n" +
                         "from Race race inner join Ranking ranking on race.serial_number = ranking.race\n" +
                         "inner join Car car on ranking.car = car.number\n" +
                         "inner join Team team on car.membership = team.name\n" +
                         "inner join Sponsor sponsor on team.name = sponsor.team\n" +
                         "where ranking.position = 1 and race.circuit = ?;";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);
            statement.setString(1,circuitName);

            ResultSet data = statement.executeQuery();

            while(data.next()){
                races.add(new Race(data.getDate(1),
                          new Ranking(new Car(new Team(data.getString(2), new Company((data.getString(3))))))));
            }

        } catch (SQLException exception){
            exception.printStackTrace(); // à changer
        }
        return races;
    }

}

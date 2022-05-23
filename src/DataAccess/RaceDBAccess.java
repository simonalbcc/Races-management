//region packages & imports
package DataAccess;

import Model.*;
import Exception.RaceException;
import Exception.DataException;
import Exception.NumberCarException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
//endregion

public class RaceDBAccess implements RaceDAO{
    private Connection connection;
    public RaceDBAccess() throws DataException {
        connection = SingletonConnexion.getInstance();
    }


    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName) throws RaceException {
        ArrayList<Date> dates = new ArrayList<>();
        try{

            String sql = "select date from Race where circuit = ? ";

            PreparedStatement statement = SingletonConnexion.getInstance().prepareStatement(sql);
            statement.setString(1,circuitName);

            ResultSet data = statement.executeQuery();

            while(data.next()){
                dates.add(data.getDate(1));
            }

        } catch (Exception exception){
            throw new RaceException();
        }
        return dates;
    }

    public ArrayList<Ranking> getARaceRankings(String circuitName, Date raceDate) throws RaceException, NumberCarException {
        ArrayList<Ranking> rankings = new ArrayList<>();
        try{
            String sql = "select ranking.position, car.number, car.power, driver.last_name_first_name, ranking.record\n" +
                    "from Ranking ranking\n" +
                    "inner join Car car on ranking.car = car.number\n" +
                    "inner join Driver driver on ranking.driver = driver.number\n" +
                    "inner join Race race on ranking.race = race.serial_number\n" +
                    "where race.circuit = ? and race.date = ?\n" +
                    "order by position;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,circuitName);
            statement.setDate(2,new java.sql.Date(raceDate.getTime()));

            ResultSet data = statement.executeQuery();

            while(data.next()){
                rankings.add(new Ranking(new Car(data.getInt("car.number"),data.getInt("car.power")),
                        null,data.getInt("ranking.position"),null,null,data.getDouble("ranking.record"),
                        new Driver(data.getString("driver.last_name_first_name"))));
            }

        } catch (SQLException exception){
            throw new RaceException();
        } catch (NumberCarException exception) {
            throw new NumberCarException();
        }
        return rankings;
    }

    public ArrayList<Race> getWinningSponsorsOfACircuit(String circuitName) throws RaceException{
        ArrayList<Race> races = new ArrayList<>();
        try{
            String sql = "select race.date, team.name, sponsor.company\n" +
                         "from Race race inner join Ranking ranking on race.serial_number = ranking.race\n" +
                         "inner join Car car on ranking.car = car.number\n" +
                         "inner join Team team on car.membership = team.name\n" +
                         "inner join Sponsor sponsor on team.name = sponsor.team\n" +
                         "where ranking.position = 1 and race.circuit = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,circuitName);

            ResultSet data = statement.executeQuery();

            while(data.next()){
                Company company = new Company((data.getString(3)));
                Team team = new Team(data.getString(2), company);
                Car car = new Car(team);
                Ranking ranking = new Ranking(car);
                Race race = new Race(data.getDate(1), ranking);

                races.add(race);
            }

        } catch (SQLException exception){
            throw new RaceException();
        }
        return races;
    }

    public ArrayList<Integer> getPositionsRemainingInARanking(String circuitName, Date date)throws RaceException {
        ArrayList<Integer> positions = new ArrayList<>();

        try {
            String sql = "select position from Ranking where race = ? order by position ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, getARaceNumber(circuitName, date));

            ResultSet data = statement.executeQuery();

            while (data.next()) {
                positions.add(data.getInt(1));
            }

        } catch (SQLException exception) {
            throw new RaceException();
        }
        return positions;
    }

    public Integer getARaceNumber(String circuitName, Date date) throws RaceException{
        Integer number = null;
        try{
            String sql = "select serial_number from Race where circuit = ? and date = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,circuitName);
            statement.setDate(2, new java.sql.Date(date.getTime()));

            ResultSet data = statement.executeQuery();
            data.next();
            number = data.getInt(1);

        } catch (SQLException exception){
            throw new RaceException();
        }
        return number;
    }

}

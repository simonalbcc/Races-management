package DataAccess;

import Model.*;
import Exception.RaceException;
import Exception.DataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
            throw new RaceException(exception);
        }
        return dates;
    }
    public ArrayList<Ranking> getARaceRankings(String circuitName, String raceDate) throws RaceException {
        ArrayList<Ranking> rankings = new ArrayList<Ranking>();
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
            statement.setString(2,raceDate);

            ResultSet data = statement.executeQuery();

            while(data.next()){
                rankings.add(new Ranking(new Car(data.getInt("car.number"),data.getDouble("car.power")),
                        null,data.getInt("ranking.position"),null,null,data.getDouble("ranking.record"),
                        new Driver(data.getString("driver.last_name_first_name"))));
            }

        } catch (SQLException exception){
            throw new RaceException(exception); // vérifier
        }
        return rankings;
    }
    public ArrayList<Race> getWinningSponsorsOfACircuit(String circuitName) throws RaceException{
        ArrayList<Race> races = new ArrayList<Race>();
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
                races.add(new Race(data.getDate(1),
                          new Ranking(new Car(new Team(data.getString(2), new Company((data.getString(3))))))));
            }

        } catch (SQLException exception){
            throw new RaceException(exception); // vérifier
        }
        return races;
    }
    public ArrayList<Integer> getPositionsRemainingInARanking(int numRace)throws RaceException{
        ArrayList<Integer> positions = new ArrayList<>();
        for(Integer position = 1; position <= 20 ;position++){ // optimiser
            positions.add(position);
        }
        try{
            String sql = "select position from Ranking where race = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,numRace);

            ResultSet data = statement.executeQuery();

            while(data.next()){
                int position = data.getInt(1)-1;
                positions.remove(position);
            }

        } catch (SQLException exception){
            throw new RaceException(exception); // vérifier
        }
        return positions;
    }

    public Integer getARaceNumber(String circuitName, Date date) throws RaceException{
        Integer number = null;
        try{
            String sql = "select serial_number from Race where circuit = ? and date = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,circuitName);
            statement.setDate(2, (java.sql.Date) date);

            ResultSet data = statement.executeQuery();
            data.next();
            number = data.getInt(1);

        } catch (SQLException exception){
            throw new RaceException(exception); // vérifier
        }
        return number;
    }

}
